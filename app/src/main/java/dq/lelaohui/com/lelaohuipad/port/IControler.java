package dq.lelaohui.com.lelaohuipad.port;

import android.app.Activity;
import android.content.Context;

import dq.lelaohui.com.lelaohuipad.port.IControlerCallBack;
import dq.lelaohui.com.nettylibrary.port.NetDataCallBack;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.manager.BaseDaoOperator;

/**
 * Created by ThinkPad on 2016/10/14.
 */

public interface IControler extends NetDataCallBack {
    public void setContext(Context context);
    public Context getContext();
    public void gotoPage(Class<?extends Activity> activity);
    public void gotoPage(String action);
    public void setIControlerCallBack(IControlerCallBack controlerCallBack);
    public IControlerCallBack getIControlerCallBack();
    public BaseDaoOperator getBaseDaoOperator();

}
