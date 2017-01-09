package dq.lelaohui.com.lelaohuipad.bean;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by ZTF on 2017/1/6.
 */

public class MyOldManInfoCate {
    @Expose
    private int code;
    @Expose
    private String msg;
    @Expose
    private List<MyOldManInfo> data;

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

    public List<MyOldManInfo> getData() {
        return data;
    }

    public void setData(List<MyOldManInfo> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MyOldManInfo{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
