package dq.lelaohui.com.nettylibrary.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import dq.lelaohui.com.nettylibrary.NetAidlInterface;

public class NetAppService extends Service {
    private NetOperator netOperator=new NetOperator();
    public NetAppService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return netOperator.asBinder();
    }

    /**
     * 进程间通信
     */
    public class NetOperator extends NetAidlInterface.Stub{

        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public void init(String id, String port) throws RemoteException {

        }

        @Override
        public void connect() throws RemoteException {

        }

        @Override
        public void regeist(String message) throws RemoteException {

        }

        @Override
        public void unregeist(String message) throws RemoteException {

        }
    }
}
