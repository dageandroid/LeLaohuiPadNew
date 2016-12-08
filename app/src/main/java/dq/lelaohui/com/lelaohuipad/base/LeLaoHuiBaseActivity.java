package dq.lelaohui.com.lelaohuipad.base;

import android.os.Bundle;

import dq.lelaohui.com.lelaohuipad.LeLaohuiApp;
import dq.lelaohui.com.lelaohuipad.port.IControler;
import dq.lelaohui.com.lelaohuipad.port.IControlerCallBack;
import dq.lelaohui.com.nettylibrary.activity.BaseActivity;
import dq.lelaohui.com.nettylibrary.socket.NetManager;

/**
 * Created by ThinkPad on 2016/10/14.
 */

public abstract  class LeLaoHuiBaseActivity extends BaseActivity implements NetManager.NetStatueCallBack,IControlerCallBack {
    public abstract  IControler getControler();
    public static final String IC_CONTROLER_ACTION="key";
    public static final String IC_CONTROLER_DATA="DATA";
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        IControler controler=getControler();
        controler.setContext(this);
        setDataCallBack(controler);
        controler.setIControlerCallBack(this);
        ((LeLaohuiApp) getApplication()).setProgressBarListener(this);
    }
    protected String getIcControlerAction(Bundle bundle){
        if(bundle==null){
            throw new RuntimeException("参数异常....Bundle is null");
        }
        return bundle.getString(IC_CONTROLER_ACTION);
    }
    protected Object getData(Bundle bundle){
        if(bundle==null){
            throw new RuntimeException("参数异常....Bundle is null");
        }
        return bundle.get(IC_CONTROLER_DATA);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if(getControler().getBaseDaoOperator()!=null){
//            getControler().getBaseDaoOperator().close();
//        }
    }
}
