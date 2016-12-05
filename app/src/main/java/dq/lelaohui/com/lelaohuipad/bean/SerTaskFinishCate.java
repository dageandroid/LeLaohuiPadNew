package dq.lelaohui.com.lelaohuipad.bean;

import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;

import java.util.List;

import dq.lelaohui.com.lelaohuipad.util.JsonUtil;

/**
 * Created by ZTF on 2016/12/3.
 * 服务员完成任务
 */

public class SerTaskFinishCate  {

    /**
     * code : 200
     * msg : 成功
     * obj : [{"serTransId":415,"stockDetailId":7599,"serviceId":"乐老汇冶金养老中心|107","serviceName":"中心头颈椎按摩","customerId":101002000002000197,"customerName":"吕清风","customerPhone":"17310535372","orgName":"乐老汇冶金养老中心","orgTypeId":3,"orgId":1002,"supplierTypeId":3,"supplierId":1002,"proWaitersNum":1,"serEndTime":"Nov 2, 2016 10:55:00 AM","serStartTime":"Nov 2, 2016 10:55:00 AM","transStatus":"6","addTime":"Nov 2, 2016 10:54:52 AM","orderPerson":"吕清风","orderPersonId":101002000002000197,"waitersName":"乐腾飞","isEval":1}]
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
    public   List<SerTaskFinishData> getData(){
        if(TextUtils.isEmpty(obj)){
            return null;
        }
        List<SerTaskFinishData> infoData= (  List<SerTaskFinishData>) JsonUtil.getInstance().jsonToObject(obj, new TypeToken<List<SerTaskFinishData>>(){}.getType(),false);
        return infoData;
    }

}
