package dq.lelaohui.com.nettylibrary.reciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import dq.lelaohui.com.nettylibrary.port.NetDataCallBack;

public class NetResponseReceiver extends BroadcastReceiver {
    private NetDataCallBack callBack;
    private String tag="LlhResponseHandler";

    public NetResponseReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        if(callBack==null){
            Log.i(tag,"onReceive  callBack is null");
            return;
        }
        if(intent==null){
            Log.i(tag,"onReceive  intent is null");
            return;
        }
        callBack.doBusses(intent.getExtras());
    }

    public void setCallBack(NetDataCallBack callBack) {
        this.callBack=callBack;
    }
}
