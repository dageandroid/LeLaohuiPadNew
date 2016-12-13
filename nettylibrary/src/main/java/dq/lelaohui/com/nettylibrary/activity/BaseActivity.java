package dq.lelaohui.com.nettylibrary.activity;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import dq.lelaohui.com.nettylibrary.port.NetDataCallBack;
import dq.lelaohui.com.nettylibrary.port.NetResponIntercept;
import dq.lelaohui.com.nettylibrary.reciver.NetResponseReceiver;
import dq.lelaohui.com.nettylibrary.socket.NetManager;
import dq.lelaohui.com.nettylibrary.util.NetContant;

public abstract class BaseActivity extends AppCompatActivity implements NetManager.ProgressBarListener{
  private  String TAG=getClass().getSimpleName();
    public NetResponseReceiver getResponseReciver() {
        return responseReciver;
    }

    private NetResponseReceiver responseReciver;
    private LocalBroadcastManager broadManager;

    public NetResponIntercept getNetResponIntercept() {
        return netResponIntercept;
    }

    public void setNetResponIntercept(NetResponIntercept netResponIntercept) {
        this.netResponIntercept = netResponIntercept;
    }

    private NetResponIntercept netResponIntercept;
    public void setDataCallBack(@NonNull NetDataCallBack dataCallBack) {
        this.dataCallBack = dataCallBack;
    }

    NetDataCallBack dataCallBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());

    }
    private void regeistNetBroaCarst()     {
         broadManager=LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(NetContant.NET_ACTION.RESP_ACTION);
         responseReciver=new NetResponseReceiver();
        if( this.dataCallBack==null){
            throw new RuntimeException(getClass().getSimpleName()+" dataCallBack is null");
        }
        responseReciver.setCallBack( this.dataCallBack);
        if (this.netResponIntercept != null) {//数据拦截器
            responseReciver.setNetResponIntercept(this.netResponIntercept);
        }
        broadManager.registerReceiver(responseReciver,intentFilter);
    }
    private void  unRegedistBrocadCast(){
         broadManager.unregisterReceiver(responseReciver);
    }
    protected abstract int getLayoutID();

    @Override
    protected void onResume() {
        super.onResume();
        regeistNetBroaCarst() ;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (this.netResponIntercept != null) {//数据拦截器
            responseReciver.setNetResponIntercept(null);
        }
        unRegedistBrocadCast();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i(TAG, "onDestroy:__________________ ");
    }
}
