package dq.lelaohui.com.lelaohuipad.base;

import android.app.Activity;
import android.content.Context;

import dq.lelaohui.com.nettylibrary.port.NetDataCallBack;
import dq.lelaohui.com.nettylibrary.socket.NetManager;

/**
 * Created by ThinkPad on 2016/10/14.
 */

public interface IControler extends NetDataCallBack {
    public void setContext(Context context);
    public Context getContext();
    public void gotoPage(Class<?extends Activity> activity);
    public void gotoPage(String action);
    public void setIControlerCallBack(IControlerCallBack controlerCallBack);
}
