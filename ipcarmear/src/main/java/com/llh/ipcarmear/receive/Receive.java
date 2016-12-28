package com.llh.ipcarmear.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.llh.ipcarmear.service.HelpService;

/**
 * Created by ZTF on 2016/12/27.
 * 摄像头报警广播
 */

public class Receive extends BroadcastReceiver {
    public static final String ACTION="com.angel.Android.MUSIC";

    @Override
    public void onReceive(Context context, Intent intent) {
        intent =new Intent(context, HelpService.class);
        intent.setAction(ACTION);
        context.startService(intent);
        abortBroadcast();
    }
}
