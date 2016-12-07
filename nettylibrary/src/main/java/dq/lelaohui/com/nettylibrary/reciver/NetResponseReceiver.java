package dq.lelaohui.com.nettylibrary.reciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import dq.lelaohui.com.nettylibrary.port.NetDataCallBack;
import dq.lelaohui.com.nettylibrary.port.NetResponIntercept;

public class NetResponseReceiver extends BroadcastReceiver {
    private NetDataCallBack callBack;
    private String tag="LlhResponseHandler";

    public NetResponIntercept getNetResponIntercept() {
        return netResponIntercept;
    }

    public void setNetResponIntercept(NetResponIntercept netResponIntercept) {
        this.netResponIntercept = netResponIntercept;
    }

    private NetResponIntercept netResponIntercept;
    public NetResponseReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(callBack==null){
            Log.i(tag,"onReceive  callBack is null");
            return;
        }
        if(intent==null){
            Log.i(tag,"onReceive  intent is null");
            return;
        }
        Bundle bundle=intent.getExtras();
        if(netResponIntercept==null||!netResponIntercept.intercept(bundle)){
            callBack.doBusses(intent.getExtras());

        }

    }

    public void setCallBack(NetDataCallBack callBack) {
        this.callBack=callBack;
    }
}
