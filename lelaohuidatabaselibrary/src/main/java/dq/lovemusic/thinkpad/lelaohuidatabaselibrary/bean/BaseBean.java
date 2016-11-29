package dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean;

import com.google.gson.annotations.Expose;

import org.greenrobot.greendao.annotation.Unique;

/**
 * Created by ThinkPad on 2016/10/24.
 */

public class BaseBean {

    public void setUnineqKey(String unineqKey) {
        this.unineqKey = unineqKey;
    }
    @Unique
    @Expose(serialize = false,deserialize = false)
    private String unineqKey;
    public  String getUnineqKey(){
        return "";
    }
}
