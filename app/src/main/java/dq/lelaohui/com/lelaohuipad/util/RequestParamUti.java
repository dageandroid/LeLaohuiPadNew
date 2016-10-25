package dq.lelaohui.com.lelaohuipad.util;

import android.content.Context;

import dq.lelaohui.com.nettylibrary.socket.RequestParam;
import dq.lelaohui.com.nettylibrary.util.NetContant;
import dq.lelaohui.com.nettylibrary.util.Protocol_KEY;

/**
 * Created by ThinkPad on 2016/10/25.
 */

public class RequestParamUti {
    private SysVar var=null;
    public RequestParamUti( SysVar var){
        this.var=var;
    }

    /**登入请求
     * @param username 用户名
     * @param pwd 密码
     * @return
     */
    public RequestParam getLogonParam(String username,String pwd){
        RequestParam requestParam=new RequestParam();
        requestParam.addHeader(Protocol_KEY.ACTION, NetContant.ServiceAction.LOGON);
        requestParam.addBody(Protocol_KEY.USERNAME,username);
        requestParam.addBody(Protocol_KEY.PWD,pwd);
        requestParam.addBody(Protocol_KEY.LOGINTYPE,1);
        requestParam.addBody(Protocol_KEY.USER_TYPE,5);
        return requestParam;
    }

}
