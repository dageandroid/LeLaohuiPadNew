package dq.lelaohui.com.lelaohuipad.util;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;

import dq.lelaohui.com.lelaohuipad.server.NetService;

/**
 * Created by ThinkPad on 2016/10/26.
 */

public class ServerUtil {
    public static final String NETSERVICE_ACTION="com.lelaohui.pad.NetService";
    public static final String NETSERVICE_MONIT_ACTION="com.lelaohui.pad.NetMoniteServer";
    public static final String FLAG_KEY="netkey";
    private static final String PACKAGE="dq.lelaohui.com.lelaohuipad";
    /**
     * 重新建立socket连接
     */
    public static final int RECONNECT_FLAG=1;
    /**
     * 重启
     */
    public static final int RESTART_TAG=0;
    /**aidl通信工具
     * @param context
     * @param mServiceConnection
     */
    public static  void bindMonitServer(Context context, ServiceConnection mServiceConnection){
        Intent intent = new Intent();
        intent.setAction(NETSERVICE_MONIT_ACTION);
        intent.setPackage(PACKAGE);
        intent.putExtra(FLAG_KEY,RECONNECT_FLAG);
        context.bindService(intent,mServiceConnection,Context.BIND_AUTO_CREATE);
    }
    public static  void bindNetServer(Context context, ServiceConnection mServiceConnection){
        Intent intent = new Intent();
        intent.setAction(NETSERVICE_ACTION);
        intent.setPackage(PACKAGE);
        intent.putExtra(FLAG_KEY,RECONNECT_FLAG);
        context.bindService(intent,mServiceConnection,Context.BIND_AUTO_CREATE);
    }
    public static void startNetServer(Context context,Class<?extends Service> server){
        Intent intent=new Intent();
        intent.setClass(context,server);
        context.startService(intent);
    }
}
