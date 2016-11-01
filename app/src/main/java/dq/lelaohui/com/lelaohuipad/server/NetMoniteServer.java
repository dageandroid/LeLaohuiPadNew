package dq.lelaohui.com.lelaohuipad.server;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import dq.lelaohui.com.lelaohuipad.ILaoHuiAidlInterface;
import dq.lelaohui.com.lelaohuipad.util.ServerUtil;

public class NetMoniteServer extends Service {
    private static final String TAG="NetMoniteServer";
    public NetMoniteServer() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ServerUtil.bindNetServer(this,mServiceConnection);
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return sutb;
    }
    private ILaoHuiAidlInterface.Stub sutb=new ILaoHuiAidlInterface.Stub(){

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    };
    private ServiceConnection mServiceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, getClass().getSimpleName()+ "onServiceConnected: ");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            ServerUtil.bindNetServer(getApplicationContext(),mServiceConnection);
            Log.i(TAG, getClass().getSimpleName()+"onServiceDisconnected: ");
        }
    };

}
