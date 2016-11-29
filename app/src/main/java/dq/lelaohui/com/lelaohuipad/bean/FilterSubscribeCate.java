package dq.lelaohui.com.lelaohuipad.bean;

import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;

import java.util.List;

import dq.lelaohui.com.lelaohuipad.util.JsonUtil;

/**
 * Created by ZTF on 2016/11/29.
 */

public class FilterSubscribeCate {

    /**
     * code : 200
     * msg : 成功
     * obj : [{"serStockDetailId":7634,"serviceName":"足疗","ruleStr":"每天1次","currentNum":15,"execNumDay":1,"isEnable":1}]
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

    public   List<FilterSubscribeData> getData(){
        if(TextUtils.isEmpty(obj)){
            return null;
        }
        List<FilterSubscribeData> infoData= (  List<FilterSubscribeData>) JsonUtil.getInstance().jsonToObject(obj, new TypeToken<List<FilterSubscribeData>>(){}.getType(),false);
        return infoData;
    }
}
