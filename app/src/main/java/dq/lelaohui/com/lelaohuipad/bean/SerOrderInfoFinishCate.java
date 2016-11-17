package dq.lelaohui.com.lelaohuipad.bean;

import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;

import java.util.List;

import dq.lelaohui.com.lelaohuipad.util.JsonUtil;

/**
 * Created by ZTF on 2016/11/17.
 */

public class SerOrderInfoFinishCate {


    /**
     * code : 200
     * msg : 成功
     * obj : [{"orderId":302,"orderCode":"100020161117090448180","amountPayable":38.0,"payStatus":0,"payStyle":1,"orderStatus":0,"contactAddress":"张学友(13788888888):地球亚洲中国","customerId":101000000002000001,"customerName":"张学友","customerPhone":"13788888888","orderPersionId":101000000002000001,"orderPersionName":"test","orderPersionTime":"Nov 17, 2016 9:04:54 AM","isDelete":0,"updTime":"Nov 17, 2016 9:04:54 AM","addTime":"Nov 17, 2016 9:04:54 AM","orgId":1001,"orgTypeId":3,"supplierId":1001,"supplierName":"test","supplierTypeId":3,"stockStatus":0}]
     */
    public   List<SerOrderInfoFinish> getData(){
        if(TextUtils.isEmpty(obj)){
            return null;
        }
        List<SerOrderInfoFinish> infoData= (  List<SerOrderInfoFinish>) JsonUtil.getInstance().jsonToObject(obj, new TypeToken<List<SerOrderInfoFinish>>(){}.getType(),false);
        return infoData;
    }
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
}
