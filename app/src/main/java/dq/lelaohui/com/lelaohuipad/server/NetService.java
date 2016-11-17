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
import dq.lelaohui.com.nettylibrary.socket.NetManager;


public class NetService extends Service {
    private static final String TAG="NetService";

    public static final String IP = "www.lelaohui.com.cn";//.5
    //public static final String IP = "192.168.11.189";//本机
//    public static final String IP = "111.204.236.14";//.3
    public static final String PORT = "60090";
    private static final String CATEGORY = "lelaohui";

    private ILaoHuiAidlInterface.Stub sutb=new ILaoHuiAidlInterface.Stub(){

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    };
    public NetService() {
    }
   private NetManager netManager=NetManager.getInstance();
    @Override
    public void onCreate() {
        super.onCreate();
        netManager.setContext(this);
        netManager.setIpAndPort(IP,PORT);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent==null||ServerUtil.RESTART_TAG==intent.getIntExtra(ServerUtil.FLAG_KEY,0)){
            ServerUtil.bindMonitServer(getApplicationContext(),mServiceConnection);
        }
        netManager.startNet();
        Log.i(TAG, "onStartCommand: "+getClass().getSimpleName());
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
      return sutb;
    }
    private ServiceConnection mServiceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            ServerUtil.bindMonitServer(getApplicationContext(),mServiceConnection);
        }
    };

    @Override
    public void onDestroy() {
        if(netManager!=null){
            netManager.stopNet();
        }
    }
}
