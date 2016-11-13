package dq.lelaohui.com.lelaohuipad.bean;

import com.google.gson.annotations.Expose;

import java.util.List;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.UserInfo;

/**
 * Created by ZTF on 2016/11/13.
 */

public class UserAddressBean {
    @Expose
    private int code;
    @Expose
    private String msg;
    @Expose
    private List<UserAddressData> data;

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

    public List<UserAddressData> getData() {
        return data;
    }

    public void setData(List<UserAddressData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LogonBena{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
