package dq.lelaohui.com.lelaohuipad.bean;

import android.text.TextUtils;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import dq.lelaohui.com.lelaohuipad.util.JsonUtil;

/**
 * Created by ZTF on 2016/11/29.
 */

public class SerSubescribeCate {


    /**
     * code : 200
     * msg : 成功
     * obj : [{"orderId":316,"stockList":[{"packageName":"足疗套餐","serStockDetailList":[{"serStockDetailId":7634,"serviceName":"足疗","ruleStr":"每天1次","totalNum":15,"currentNum":15,"packageStatus":0,"execDates":"","execNumDay":1,"packDetailId":229,"isEnable":1}]}]},{"orderId":317,"stockList":[{"packageName":"足疗套餐","serStockDetailList":[{"serStockDetailId":7633,"serviceName":"足疗","ruleStr":"每天1次","totalNum":15,"currentNum":15,"packageStatus":0,"execDates":"","execNumDay":1,"packDetailId":229,"isEnable":1}]}]},{"orderId":318,"stockList":[{"packageName":"测试套餐包","serStockDetailList":[{"serStockDetailId":7631,"serviceName":"足疗","ruleStr":"每天1次","totalNum":30,"currentNum":30,"packageStatus":0,"execDates":"","execNumDay":1,"packDetailId":227,"isEnable":1},{"serStockDetailId":7632,"serviceName":"针灸","ruleStr":"每天2次","totalNum":60,"currentNum":60,"packageStatus":0,"execDates":"","execNumDay":2,"packDetailId":228,"isEnable":1}]}]},{"orderId":319,"stockList":[{"packageName":"测试套餐包","serStockDetailList":[{"serStockDetailId":7629,"serviceName":"足疗","ruleStr":"每天1次","totalNum":30,"currentNum":30,"packageStatus":0,"execDates":"","execNumDay":1,"packDetailId":227,"isEnable":1},{"serStockDetailId":7630,"serviceName":"针灸","ruleStr":"每天2次","totalNum":60,"currentNum":60,"packageStatus":0,"execDates":"","execNumDay":2,"packDetailId":228,"isEnable":1}]}]}]
     */
    @Expose
    private String code;
    @Expose
    private String msg;
    @Expose
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
    public   List<SerSubescribeData> getData(){
        if(TextUtils.isEmpty(obj)){
            return null;
        }
        List<SerSubescribeData> infoData= (  List<SerSubescribeData>) JsonUtil.getInstance().jsonToObject(obj, new TypeToken<List<SerSubescribeData>>(){}.getType(),false);
        return infoData;
    }
}
