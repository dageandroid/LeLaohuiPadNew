package dq.lelaohui.com.lelaohuipad.bean;

import android.text.TextUtils;

import dq.lelaohui.com.lelaohuipad.util.JsonUtil;

/**
 * Created by ZTF on 2016/11/13.
 */

public class SerOrderInfoCate  {

    /**
     * code : 200
     * msg : 成功
     * obj : {"serOrderInfo":{"orderCode":"100020161113110112761","amountPayable":300.0,"payStatus":0},"serOrderInfoDetailBeanList":[{"serOrderInfoDetail":{"orderCode":"110020161113110112761","levelId":1,"price":300.0,"serNum":1,"pictureUrl":"/LelaoHuiWebApp/folder/xiujiao.jpg","orgTypeId":3,"orgId":1001,"orgName":"乐老汇罗庄养老中心"}}]}
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
    public SerOrderInfoData getData(){
        if(TextUtils.isEmpty(obj)){
            return null;
        }
        SerOrderInfoData infoData= (SerOrderInfoData) JsonUtil.getInstance().jsonToObject(obj,SerOrderInfoData.class,false);
        return infoData;
    }
}
