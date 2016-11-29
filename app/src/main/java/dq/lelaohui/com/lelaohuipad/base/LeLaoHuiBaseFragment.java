package dq.lelaohui.com.lelaohuipad.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dq.lelaohui.com.lelaohuipad.fragement.shop.BaseFragment;
import dq.lelaohui.com.lelaohuipad.port.IControler;

/**
 * Created by ZTF on 2016/11/28.
 */

public abstract class LeLaoHuiBaseFragment extends BaseFragment {
    public abstract IControler getControler();
    public static final String IC_CONTROLER_ACTION="key";
    public static final String IC_CONTROLER_DATA="DATA";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doBusses();
        View view = inflater.inflate(getLayoutID(), container, false);
        IControler controler=getControler();
        controler.setContext(this.getContext());
        setDataCallBack(controler);
        controler.setIControlerCallBack(this);
        initView(view);
        return view;
    }


    @Override
    public void result(Bundle bundle) {

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
    protected abstract View initView( View view);
    protected abstract int getLayoutID();
    protected abstract void doBusses();
}
