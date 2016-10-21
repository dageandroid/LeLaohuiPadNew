package dq.lelaohui.com.lelaohuipad.base;

import android.os.Bundle;
import android.os.PersistableBundle;

import dq.lelaohui.com.nettylibrary.activity.BaseActivity;
import dq.lelaohui.com.nettylibrary.socket.NetManager;

/**
 * Created by ThinkPad on 2016/10/14.
 */

public abstract  class LeLaoHuiBaseActivity extends BaseActivity implements NetManager.NetStatueCallBack,IControlerCallBack{
    public abstract  IControler getControler();

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        IControler  controler=getControler();
        controler.setContext(this);
        setDataCallBack(controler);
        controler.setIControlerCallBack(this);
    }
}
