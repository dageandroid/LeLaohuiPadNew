package dq.lelaohui.com.lelaohuipad.bean;

import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;

import java.util.List;

import dq.lelaohui.com.lelaohuipad.util.JsonUtil;

/**
 * Created by ZTF on 2016/12/3.
 */

public class MySerSubescribeCate  {
    /**
     * code : 200
     * msg : 成功
     * obj : [{"serTransId":428,"stockDetailId":7702,"serviceId":"乐老汇冶金养老中心|105","serviceName":"中心刮痧","customerId":101002000002000197,"customerName":"吕清风","customerPhone":"17310535372","orgName":"乐老汇冶金养老中心","orgTypeId":3,"orgId":1002,"proWaitersNum":1,"serEndTime":"Nov 29, 2016 11:00:00 AM","serStartTime":"Nov 29, 2016 9:30:00 AM","transStatus":"2","addTime":"Nov 28, 2016 9:39:17 PM","waitersName":"康体部","packDetailId":223,"isEval":0}]
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

    public List<MySerSubescribeData> getData(){
        if(TextUtils.isEmpty(obj)){
            return null;
        }
        List<MySerSubescribeData> infoData= (  List<MySerSubescribeData>) JsonUtil.getInstance().jsonToObject(obj, new TypeToken<List<MySerSubescribeData>>(){}.getType(),false);
        return infoData;
    }
}
