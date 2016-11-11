package dq.lelaohui.com.lelaohuipad.bean;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.BaseBean;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.SerInitProPack;

/**
 * Created by ZTF on 2016/11/11.
 */

public class ServerCartBean extends ShoppingCarListBean {
    public ServerCartBean(String proName, double proPrice, int proNum, int proId) {
        super(proName, proPrice, proNum, proId);
    }
    public ServerCartBean(SerInitProPack serInitProPack) {
       super(serInitProPack);
    }

    @Override
    public BaseBean getBean() {
        SerInitProPack sp= (SerInitProPack) super.getBean();
        return sp;
    }
}
