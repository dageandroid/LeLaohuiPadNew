package dq.lelaohui.com.lelaohuipad.port;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.SerInitProPack;

/**
 * Created by ZTF on 2016/11/9.
 */

public interface OrderServerInterface {
    public  void callBack();

    public void refreshShppingList(SerInitProPack serInitProPack);

    public  void refreshServerList();

}
