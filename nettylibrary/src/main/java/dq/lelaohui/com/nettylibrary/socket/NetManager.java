package dq.lelaohui.com.nettylibrary.socket;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import com.sun.commontransfer.adroid.TransferClientNetworkImpl;

import dq.lelaohui.com.nettylibrary.port.ReqParam;
import dq.lelaohui.com.nettylibrary.service.NetIntentService;
import dq.lelaohui.com.nettylibrary.util.NetUtil;

/**
 * Created by ThinkPad on 2016/10/12.
 */
public class NetManager {
    private String tag="LlhResponseHandler";
    private static NetManager ourInstance = new NetManager();
    private Context context;
    private ProgressManager progressManager=null;
    private ProgressBarListener progressBarListener=null;
    public static NetManager getInstance() {
        return ourInstance;
    }
    private NetStatueCallBack statusCallBack;



    private NetManager() {
        progressManager=new ProgressManager();
    }
    public void setStatusCallBack(NetStatueCallBack statusCallBack) {
        this.statusCallBack = statusCallBack;
    }




    public void startNet(){
        if(context==null){
            throw new RuntimeException("context is null.....");
        }
      boolean netAailable=  NetUtil.isNetworkConnected(context);
        Log.i(tag,"netAailable:"+netAailable);
        if(netAailable){
            TransferClientNetworkImpl.getInstance().start(context);
        }else{
            if(statusCallBack!=null){
                statusCallBack.usable();
            }else{
                Toast.makeText(context, "当前网络不可用，请检查您的网络", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void reqData(ReqParam parm){
        reqData( parm,true);
    }
    public void reqData(ReqParam parm,boolean isShowProgress){
        if(context==null){
            throw new RuntimeException("context is null");
        }
        if(isShowProgress){
            if(progressBarListener!=null){
                progressBarListener.showProgress();
            }
            if(progressManager!=null){
                progressManager.addProgress(parm.getCurrenSN(),progressBarListener);
            }
        }
        Intent intent=new Intent(context, NetIntentService.class);
        intent.putExtra(NetIntentService.KEY, parm);
        context.startService(intent);
    }
    public void hideProgressBar(String sn){
        if(progressManager!=null) {
            progressManager.removeProgress(sn);
        }
    }
    public void setProgressBarListener(ProgressBarListener progressBarListener) {
        this.progressBarListener = progressBarListener;
    }
    public void setIpAndPort(String ip,String port){
        TransferClientNetworkImpl.getInstance().initWith(ip, port,LlhResponseHandler.getInstance());
        Log.i(tag,"iP=="+ip);
    }
    public void setContext(Context context){
        this.context=context;
        LlhResponseHandler.getInstance().setContext(this.context);
    }
    public void stopNet(){
        TransferClientNetworkImpl.getInstance().stop();
    }
    /**
     * 网络状态回调接口
     *
     * */
    public interface  NetStatueCallBack{
        void usable();
    }

    /**********
     * 进度条接口
     */
    public interface  ProgressBarListener{
        void showProgress();
        void hideProgress();
    }
    class ProgressManager{
        private String SN;
        private ProgressBarListener progressBarListener;

        private ProgressManager() {
        }
        public void addProgress(String sn,ProgressBarListener progressBarListener){
            this.SN=sn;
            ProgressBarListener oldprogressBarListener=this.progressBarListener;
            if (oldprogressBarListener != null) {
                oldprogressBarListener.hideProgress();
            }
            this.progressBarListener=progressBarListener;
        }
        public void removeProgress(String sn){
            if(this.SN.equals(sn)){
                if(this.progressBarListener!=null){
                    this.progressBarListener.hideProgress();
                }
            }
        }
    }
}
