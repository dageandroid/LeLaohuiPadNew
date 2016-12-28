package dq.lelaohui.com.lelaohuipad.fragement.shop.dataprovider;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import dq.lelaohui.com.lelaohuipad.base.LaoHuiBaseControler;
import dq.lelaohui.com.lelaohuipad.bean.SerInitProPackBean;
import dq.lelaohui.com.lelaohuipad.bean.SerOrderInfoCate;
import dq.lelaohui.com.lelaohuipad.bean.SerOrderInfoData;
import dq.lelaohui.com.lelaohuipad.bean.ServerMenuCate;
import dq.lelaohui.com.lelaohuipad.controler.ServerControler;
import dq.lelaohui.com.lelaohuipad.controler.ServerMenuControler;
import dq.lelaohui.com.lelaohuipad.util.JsonUtil;
import dq.lelaohui.com.lelaohuipad.util.SysVar;
import dq.lelaohui.com.nettylibrary.socket.LlhResponseHandler;
import dq.lelaohui.com.nettylibrary.socket.NetManager;
import dq.lelaohui.com.nettylibrary.util.ServiceNetContant;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.BaseBean;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.ProCateMenuService;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.SerInitProPack;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8;

import static dq.lelaohui.com.lelaohuipad.controler.ServerMenuControler.SUCCESS_CODE;

/**
 * Created by ThinkPad on 2016/12/14.
 */

public class ServerMenuDataManager extends DataManager {
    private final ReentrantLock reentrantLock;
    private final ReentrantLock mainServerLock;
    private final SysVar var;
    private Vector<String> snVector=new Vector<>();
    private ConcurrentHashMap<String,String> snQue=new ConcurrentHashMap<>();
    public void setFc(LaoHuiBaseControler fc) {
        this.fc = fc;
    }

    public void setProgressBarListener(NetManager.ProgressBarListener progressBarListener) {
        this.progressBarListener = progressBarListener;
    }

    private LaoHuiBaseControler fc;
    private NetManager.ProgressBarListener progressBarListener;
    private volatile  boolean isStart=true;
    private static final String TAG="ServerMenuDataManager";
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
    public static final String CACHE_RIGTH_KEY="right_detial";
    /**
     * 服务二级左侧菜单
     */
    public static final int LEFT_MENUM =1;
    /**
     * 服务二级右侧详细列表
     */
    public static final int SERVER_DETAILE_PAGE=3;

    public static final String MAIN_SERVER_CACHE_KEY="maiServerkey";
    /****
     *  long  cateIdL;
     int isPackInt;
     int cateLevelInt;
     */
    public static final String CATE_ID_KEY="cateIdL";
    public static final String PACK_IS_KEY="isPackInt";
    public static final String CATE_LEVEL_KEY="cateLevelInt";
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
                                    String status=task.get();
                                    if(ERRO.equals(status)){
                                        if(progressBarListener!=null){
                                            progressBarListener.hideProgress();
                                        }
                                        return;

                                    }
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
    public void reqData(int flag,Bundle req,boolean isRfesh) throws InterruptedException {
        if (this.progressBarListener != null) {
            this.progressBarListener.showProgress();
        }
        switch (flag){
            case LEFT_MENUM:
                long  cateIdL=req.getLong(CATE_ID_KEY);
                int isPackInt=req.getInt(PACK_IS_KEY);
                int cateLevelInt=req.getInt(CATE_LEVEL_KEY);
                doServerMenu(isRfesh,cateIdL,isPackInt,cateLevelInt);
                break;
            case SERVER_DETAILE_PAGE:
                doServerCateDetail(isRfesh,req);
                break;
        }
    }
    public static final String CACHE_CATEID_KEY="cache_cateId_key";
    private void doServerCateDetail(boolean isRfesh,Bundle req) throws InterruptedException {
        if(req==null){
            return ;
        }
        ServerMenuControler serverControler = (ServerMenuControler) fc;
        long  cateIdL=req.getLong(CATE_ID_KEY);
        int isPackInt=req.getInt(PACK_IS_KEY);
        long beginTime=System.currentTimeMillis();
        String key=CACHE_RIGTH_KEY+"_"+cateIdL;
        if(isRfesh){
            addServerCateTask(req, serverControler, beginTime, key);
            return ;
        }
        Cursor cursor=serverControler.getSerInitProCursor((int)cateIdL);
        if(cursor==null||cursor.getCount()==0){
            addServerCateTask(req, serverControler, beginTime, key);
        }else if(cursor!=null&&cursor.getCount()>0){
            if(dataListener!=null){
                dataListener.dataChanager(String.valueOf(SERVER_DETAILE_PAGE));
            }
        }

    }

    private void addServerCateTask(Bundle req, final ServerMenuControler serverControler, long beginTime, String key) throws InterruptedException {
        if(progressBarListener!=null){
            progressBarListener.showProgress();
        }
        long  cateIdL=req.getLong(CATE_ID_KEY);

        Future<String> task=getServerRigthFuture( serverControler,  req);
        queue.put(task);
    }

    /**
     * 数据获取
     * @param isRfesh
     * @param cateIdL
     * @param isPackInt
     * @param cateLevelInt
     */
    private void doServerMenu(boolean isRfesh,long  cateIdL,int isPackInt,int cateLevelInt) throws InterruptedException {
        ServerMenuControler serverControler = (ServerMenuControler) fc;
        if(isRfesh) {
            deleteCache(serverControler);
            addMainTaskToQuee(serverControler,cateIdL,isPackInt,cateLevelInt);
            return;
        }
        //从数据库中获取左侧服务类别相关数据
        Cursor cursor = null;
        if (cateIdL == -1) {
            cursor = serverControler.getQueryCateCursor();
        } else {
            cursor = serverControler.getQueryTwoCursor(cateIdL);
        }
        //判断数据是否有数据库获取还是服务器请求获取
        if(cursor==null||cursor.getCount()==0){
            addMainTaskToQuee(serverControler,cateIdL,isPackInt,cateLevelInt);
        }else if(cursor!=null&&cursor.getCount()!=0){
            if (dataListener != null) {
                dataListener.dataChanager(null);
            }
        }
    }

    private void deleteCache(ServerMenuControler serverControler) {
        serverControler.getBaseDaoOperator().delete(new BaseBean() {
            @Override
            public String getUnineqKey() {
                return super.getUnineqKey();
            }
        });

        serverControler.getBaseDaoOperator( ServerMenuControler.GET_SER_INIT_PROPACK_DATA).delete(new BaseBean() {
            @Override
            public String getUnineqKey() {
                return super.getUnineqKey();
            }
        });
    }

    private void addMainTaskToQuee(ServerMenuControler serverControler, long  cateIdL, int isPackInt, int cateLevelInt) throws InterruptedException {
        if(progressBarListener!=null){
            progressBarListener.showProgress();
        }
        Log.i(TAG,"cateIdL=="+cateIdL+"isPackInt=="+isPackInt);
        Future<String> task= getServerLeftCateFuture(serverControler,  cateIdL, isPackInt, cateLevelInt);
        queue.put(task);
    }
    protected Future<String> getServerRigthFuture(final ServerMenuControler serverControler, final Bundle req){
        Future<String> task= (Future<String>) addTask(new Callable<String>() {
            @Override
            public String call() throws Exception {


                try{
                    long  cateIdL=req.getLong(CATE_ID_KEY);
                    int isPackInt=req.getInt(PACK_IS_KEY);
              String sn=      serverControler.doQueryServerCategory(cateIdL,isPackInt);
                    reentrantLock.lock();
                    ReqSnQuee.add(sn);
                    wati.await(30*1000,TimeUnit.MICROSECONDS);
                    return String.valueOf(SERVER_DETAILE_PAGE);
                }catch (Exception e){
                    return ERRO;
                }finally {
                    reentrantLock.unlock();
                }

            }
        });

        return task;
    }
    private static final String ERRO="error";
    protected Future<String> getServerLeftCateFuture(final ServerMenuControler serverControler, final long  cateIdL, final int isPackInt, final int cateLevelInt) {
        return (Future<String>)  addTask(new Callable() {
            @Override
            public String call() throws Exception {

                try{
                    Log.i(TAG,"cateIdL=="+cateIdL+"isPackInt=="+isPackInt);
                   String sn= serverControler.doQueryServerCategory(cateIdL,isPackInt,cateLevelInt);
                    ReqSnQuee.add(sn);
                    mainServerLock.lock();
                    mainServerWati.await(30*1000, TimeUnit.MILLISECONDS);
                    return  String.valueOf(LEFT_MENUM);
                }catch (Exception e ){
                    e.printStackTrace();
                    return  ERRO;
                }finally {
                    mainServerLock.unlock();
                }
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
        String SN=responseData.getString(LlhResponseHandler.Respon_Key.SN);

        //数据返回解析
        ServerMenuControler serverControler= (ServerMenuControler) fc;
        if(ServiceNetContant.ServiceResponseAction.GETSERPROCATEJSONLIST_RESPONSE.equals(action)){
            if(ReqSnQuee.isEmpty()||! ReqSnQuee.hasSN(SN)){
                return true;
            }
            ReqSnQuee.removeSn(SN);
            String body=fc.getResponseBody(responseData);

            ServerMenuCate serverCate= (ServerMenuCate) serverControler.getJsonToObject(body,ServerMenuCate.class);
            if(serverCate.getCode().equals(ServerControler.SUCCESS_CODE)){
                if(fc.getIControlerCallBack()!=null){//解析数据成功，通知UI界面
                    mainServerLock.lock();
                    try{
                        List<ProCateMenuService> data= (List<ProCateMenuService>) JsonUtil.getInstance().jsonToObject(serverCate.getObj(),new TypeToken< List<ProCateMenuService> >(){}.getType(),false);
                        serverControler. setDataList(data);
                        mainServerWati.signal();
                    }finally {
                        mainServerLock.unlock();
                    }
                }
            }else{

            }
        }else  if (ServiceNetContant.ServiceResponseAction.QUERY_SERVICE_CATEGORYSJSONLIST_RESPONSE.equals(action)){
            SerInitProPackBean serverCate = serverControler.getBodySerInitProPackResponse(responseData);
            if(serverCate.getCode().equals(SUCCESS_CODE)){
                if(ReqSnQuee.isEmpty()||! ReqSnQuee.hasSN(SN)){
                    return true;
                }
                ReqSnQuee.removeSn(SN);
                List<SerInitProPack> serInitProPacksData= (List<SerInitProPack> )serverControler.getJsonToObject(serverCate.getObj(),new TypeToken< List<SerInitProPack> >(){}.getType(),true);
                serverControler.setSerInitProPackData(serInitProPacksData);
                reentrantLock.lock();
                try{
                    wati.signal();
                }finally {
                    reentrantLock.unlock();
                }
            }
        }else if ((ServiceNetContant.ServiceResponseAction.CAL_ORDER_MONEY.equals(action))){
//            SerOrderInfoCate serOrderInfoCate=serverControler.getBodySerOrderInfoResponse(responseData);
//            if(serOrderInfoCate.getCode().equals(SUCCESS_CODE)){
//                if(getIControlerCallBack()!=null){//解析数据成功，通知UI界面
//                    SerOrderInfoData infoData= serOrderInfoCate.getData();
                serverControler.setSerOrderInfoData(responseData);
                    Log.i("","doBusses serOrderInfo="+responseData.toString());
//                    Bundle bundle=new Bundle();
//                    bundle.putString("action",ServiceNetContant.ServiceResponseAction.CAL_ORDER_MONEY);
//                    bundle.putParcelable("serOrderInfo",infoData);
//                    getIControlerCallBack().result(bundle);
//                }
//            }
        }
        return true;
    }
    private static ServerMenuDataManager sdmanager=new ServerMenuDataManager();
    private ServerMenuDataManager(){
        mThread.start();
        var= SysVar.getInstance();
        reentrantLock=new ReentrantLock();
        mainServerLock=new ReentrantLock();
        mainServerWati=mainServerLock.newCondition();
        wati=reentrantLock.newCondition();
    }
    public static ServerMenuDataManager getInstance(LaoHuiBaseControler fct, NetManager.ProgressBarListener mProgressBarListener){
        sdmanager.fc = fct;
        sdmanager.progressBarListener = mProgressBarListener;

    return sdmanager;
    }

}
