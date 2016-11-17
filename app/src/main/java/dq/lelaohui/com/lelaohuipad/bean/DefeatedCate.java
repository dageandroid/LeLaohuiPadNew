package dq.lelaohui.com.lelaohuipad.bean;

/**
 * Created by ZTF on 2016/11/17.
 */

public class DefeatedCate {

    /**
     * code : 202
     * msg : 您的可用余额不足
     * obj : 100020161117084052743
     */

    private String code;
    private String msg;
    private String obj;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "DefeatedCate{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", obj='" + obj + '\'' +
                '}';
    }
}
