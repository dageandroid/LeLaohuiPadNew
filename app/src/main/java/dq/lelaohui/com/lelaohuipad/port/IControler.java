package dq.lelaohui.com.lelaohuipad.port;

import android.app.Activity;
import android.content.Context;

import dq.lelaohui.com.nettylibrary.port.NetDataCallBack;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.manager.BaseDaoOperator;

/**
 * Created by ThinkPad on 2016/10/14.
 */

public interface IControler extends NetDataCallBack {
    void setContext(Context context);
    Context getContext();
    void gotoPage(Class<? extends Activity> activity);
    void gotoPage(String action);
    void setIControlerCallBack(IControlerCallBack controlerCallBack);
    IControlerCallBack getIControlerCallBack();
    BaseDaoOperator getBaseDaoOperator();

}
