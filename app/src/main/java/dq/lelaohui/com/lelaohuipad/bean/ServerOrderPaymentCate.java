package dq.lelaohui.com.lelaohuipad.bean;

import android.text.TextUtils;

import dq.lelaohui.com.lelaohuipad.util.JsonUtil;

/**
 * Created by ZTF on 2016/11/15.
 */

public class ServerOrderPaymentCate {
    @Override
    public String toString() {
        return "ServerOrderPaymentCate{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", obj='" + obj + '\'' +
                '}';
    }

    /**
     * code : 200
     * msg : 成功
     * obj : {"amountPayable":38,"orderCode":"100020161115011822584","orderId":274,"payStyle":1}
     */

    private String code;
    private String msg;
    private String obj;

    public ServerOrderPayment getData() {
        if (TextUtils.isEmpty(obj)) {
            return null;
        }
        ServerOrderPayment orderPayment = null;
        orderPayment = (ServerOrderPayment) JsonUtil.getInstance().jsonToObject(obj, ServerOrderPayment.class, false);
        return orderPayment;
    }

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

}
