package dq.lelaohui.com.lelaohuipad.fragement.shop.dataprovider;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import dq.lelaohui.com.lelaohuipad.base.LaoHuiBaseControler;
import dq.lelaohui.com.lelaohuipad.bean.ServerCate;
import dq.lelaohui.com.lelaohuipad.controler.ServerControler;
import dq.lelaohui.com.lelaohuipad.util.JsonUtil;
import dq.lelaohui.com.lelaohuipad.util.SysVar;
import dq.lelaohui.com.nettylibrary.socket.NetManager;
import dq.lelaohui.com.nettylibrary.util.ServiceNetContant;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.BaseBean;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.ProCateService;

/**
 * Created by ThinkPad on 2016/12/14.
 */

public class ServerDataManager extends DataManager {
    private final ReentrantLock reentrantLock;
    private final ReentrantLock mainServerLock;
    private final SysVar var;
    private LaoHuiBaseControler fc;
   private NetManager.ProgressBarListener progressBarListener;
    private volatile  boolean isStart=true;

    public FootDataListener getDataListener() {
        return dataListener;
    }

    public void setDataListener(FootDataListener dataListener) {
        this.dataListener = dataListener;
    }

    private FootDataListener dataListener;
    private ArrayBlockingQueue<Future<String>> queue=new ArrayBlockingQueue<Future<String>>(30);
    private Condition wati=null;
    private Condition mainServerWati=null;
    /**
     * 服务一级页面
     */
    public static final int ONEPAGE=0;
    /**
     * 服务二级左侧菜单
     */
    public static final int TWOPAGE=1;
    /**
     * 服务二级右侧详细列表
     */
    public static final int SERVER_DETAILE_PAGE=3;

    public static final String MAIN_SERVER_CACHE_KEY="maiServerkey";
    private long cache_time=10*60*1000;
    private  Thread mThread=new Thread(){
        @Override
        public void run() {
            while(isStart){
                Future<String> task= null;
                try {
                    task = queue.take();
                    if(task!=null){
                        if(task.isDone()){
                            if( dataListener!=null){
                                try {
                                    dataListener.dataChanager(task.get());
                                    if(queue.isEmpty()){
                                        if(progressBarListener!=null){
                                            progressBarListener.hideProgress();
                                        }
                                    }
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                } catch (ExecutionException e) {
                                    e.printStackTrace();
                                }
                            }

                        }else if(!task.isCancelled()){
                            queue.offer(task);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    public void reqData(int flag,Bundle req,boolean isRfesh){
        switch (flag){
            case  ONEPAGE:
                doMainServer(isRfesh);
                break;
        }
    }

    private void doMainServer(boolean isRfesh) {
        ServerControler serverControler = (ServerControler) fc;
        String temp = null;
        if(isRfesh){
            deleteCache(serverControler);
            addMainTaskToQuee(serverControler);
            return;
        }
        Cursor cursor = serverControler.getQueryFirstCursor();
        synchronized (this){
            if(cursor==null||cursor.getCount()==0){

                addMainTaskToQuee(serverControler);
            }else if(cursor!=null&&cursor.getCount()!=0){
                long visittime=System.currentTimeMillis();

                long lastVistiTime=var.getLongTime(MAIN_SERVER_CACHE_KEY);
                if((visittime-lastVistiTime)>cache_time){
                    deleteCache(serverControler);
                    addMainTaskToQuee(serverControler);
                }else{
                    if (dataListener != null) {
                        dataListener.dataChanager(null);
                    }
                }

            }
        }
    }

    private void deleteCache(ServerControler serverControler) {
        serverControler.getBaseDaoOperator().delete(new BaseBean() {
            @Override
            public String getUnineqKey() {
                return super.getUnineqKey();
            }
        });
    }

    private void addMainTaskToQuee(ServerControler serverControler) {
        if(progressBarListener!=null){
            progressBarListener.showProgress();
        }
        Future<String> task=getMainServerFuture(serverControler);
        queue.add(task);
        var.setLongTime(MAIN_SERVER_CACHE_KEY,System.currentTimeMillis());
    }

    protected Future<String> getMainServerFuture( final ServerControler serverControler) {
        return (Future<String>)  addTask(new Callable() {
            @Override
            public String call() throws Exception {
                mainServerLock.lock();
                try{
                    serverControler.doQueryServerCategory();
                    mainServerWati.await(30*1000, TimeUnit.MILLISECONDS);
                    return  "0";
                }catch (Exception e ){
                    e.printStackTrace();
                }finally {
                    mainServerLock.unlock();
                }
                return null;
            }
        });
    }
    @Override
    public boolean intercept(Bundle responseData) {

        if(responseData==null){

            return false ;
        }
        String action=fc.getResponseAction(responseData);
        if(TextUtils.isEmpty(action)){
            return false;
        }
        ServerControler serverControler= (ServerControler) fc;
        if(ServiceNetContant.ServiceResponseAction.GETSERPROCATEJSONLIST_RESPONSE.equals(action)){
            String body=fc.getResponseBody(responseData);
            ServerCate serverCate= (ServerCate) serverControler.getJsonToObject(body,ServerCate.class);
            if(serverCate.getCode().equals(ServerControler.SUCCESS_CODE)){
                if(fc.getIControlerCallBack()!=null){//解析数据成功，通知UI界面
                    mainServerLock.lock();
                   try{

                       List<ProCateService> data= (List<ProCateService>) JsonUtil.getInstance().jsonToObject(serverCate.getObj(),new TypeToken< List<ProCateService> >(){}.getType(),false);
//                    List<ProCateService> data= (List<ProCateService>) serverControler.getJsonToObject(,);
                       serverControler. setDataList(data);
                       mainServerWati.signal();
//                    Bundle bundle=new Bundle();
//                   fc. getIControlerCallBack().result(bundle);

                   }finally {
                       mainServerLock.unlock();
                   }
                }
            }else{

            }
        }
        return true;
    }

    public ServerDataManager(LaoHuiBaseControler fc, NetManager.ProgressBarListener progressBarListener) {
        this.fc = fc;
        this.progressBarListener = progressBarListener;
        reentrantLock=new ReentrantLock();
        mainServerLock=new ReentrantLock();
        mainServerWati=mainServerLock.newCondition();
        wati=reentrantLock.newCondition();
        mThread.start();
        var= SysVar.getInstance();
    }
}
