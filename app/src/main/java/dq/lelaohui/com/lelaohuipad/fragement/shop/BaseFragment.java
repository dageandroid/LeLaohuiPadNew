package dq.lelaohui.com.lelaohuipad.fragement.shop;

import android.support.v4.app.Fragment;

import dq.lelaohui.com.lelaohuipad.port.IControlerCallBack;
import dq.lelaohui.com.lelaohuipad.base.LaoHuiBaseControler;

/**
 * Created by ThinkPad on 2016/10/18.
 */

public abstract class BaseFragment extends Fragment implements IControlerCallBack {
    private LaoHuiBaseControler controler;
    public LaoHuiBaseControler getControler() {
        return controler;
    }

    public void setControler(LaoHuiBaseControler controler) {
        this.controler = controler;
    }


}
