package dq.lelaohui.com.lelaohuipad.fragement.shop;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dq.lelaohui.com.lelaohuipad.R;
import dq.lelaohui.com.lelaohuipad.port.IControler;
import dq.lelaohui.com.lelaohuipad.port.IControlerCallBack;
import dq.lelaohui.com.lelaohuipad.base.LaoHuiBaseControler;
import dq.lelaohui.com.nettylibrary.port.NetDataCallBack;
import dq.lelaohui.com.nettylibrary.reciver.NetResponseReceiver;
import dq.lelaohui.com.nettylibrary.util.NetContant;

/**
 * Created by ThinkPad on 2016/10/18.
 */

public abstract class BaseFragment extends Fragment implements IControlerCallBack {

    public NetResponseReceiver getResponseReciver() {
        return responseReciver;
    }

    private NetResponseReceiver responseReciver;
    private LocalBroadcastManager broadManager;

    public void setDataCallBack(@NonNull NetDataCallBack dataCallBack) {
        this.dataCallBack = dataCallBack;
    }

    NetDataCallBack dataCallBack;
    private void regeistNetBroaCarst()     {
        broadManager=LocalBroadcastManager.getInstance(this.getContext());
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(NetContant.NET_ACTION.RESP_ACTION);
        responseReciver=new NetResponseReceiver();
        if( this.dataCallBack==null){
            throw new RuntimeException(getClass().getSimpleName()+" dataCallBack is null");
        }
        responseReciver.setCallBack( this.dataCallBack);
        broadManager.registerReceiver(responseReciver,intentFilter);
    }
    private void  unRegedistBrocadCast(){
        broadManager.unregisterReceiver(responseReciver);
    }

    @Override
    public void onResume() {
        super.onResume();
        regeistNetBroaCarst() ;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unRegedistBrocadCast();
    }
}
