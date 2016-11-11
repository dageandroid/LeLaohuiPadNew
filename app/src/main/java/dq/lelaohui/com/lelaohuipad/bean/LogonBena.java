package dq.lelaohui.com.lelaohuipad.bean;

import com.google.gson.annotations.Expose;

import java.util.List;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.UserInfo;

/**
 * Created by ThinkPad on 2016/10/13.
 */

public class LogonBena {

    /**
     * code : 0
     * msg : 成功
     * data : [{"centerId":1001,"centerName":"乐老汇罗庄养老中心","userId":"10100100000200096","realName":"李铁","orgId":1001,"orgType":3,"orgName":"乐老汇罗庄养老中心","cardNo":0,"telephone":"13533088879","balance":8550,"bindCustomerStatus":"0","serSignRecordId":"67","signStatus":"1","signCustomerId":"10100100000200096","groupIdList":[{"groupId":32},{"groupId":30}],"helpPhone":"","servicePhone":""}]
     */
    @Expose
    private int code;
    @Expose
    private String msg;
    /**
     * centerId : 1001
     * centerName : 乐老汇罗庄养老中心
     * userId : 10100100000200096
     * realName : 李铁
     * orgId : 1001
     * orgType : 3
     * orgName : 乐老汇罗庄养老中心
     * cardNo : 0
     * telephone : 13533088879
     * balance : 8550
     * bindCustomerStatus : 0
     * serSignRecordId : 67
     * signStatus : 1
     * signCustomerId : 10100100000200096
     * groupIdList : [{"groupId":32},{"groupId":30}]
     * helpPhone :
     * servicePhone :
     */
    @Expose
    private List<UserInfo> data;

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

    public List<UserInfo> getData() {
        return data;
    }

    public void setData(List<UserInfo> data) {
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
