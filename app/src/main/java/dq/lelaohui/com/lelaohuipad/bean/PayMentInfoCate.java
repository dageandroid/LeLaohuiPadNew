package dq.lelaohui.com.lelaohuipad.bean;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by ZTF on 2016/12/9.
 */

public class PayMentInfoCate {
    @Expose
    private int code;
    @Expose
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    @Override
    public String toString() {
        return "PayMentInfoCate{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
