package dq.lelaohui.com.lelaohuipad.util;

import android.util.Log;

import dq.lelaohui.com.nettylibrary.socket.RequestParam;
import dq.lelaohui.com.nettylibrary.util.NetContant;
import dq.lelaohui.com.nettylibrary.util.Protocol_KEY;

/**
 * Created by ZTF on 2016/11/17.
 */

public class AddressRequerstParam {

    /**
     * 获取数据请求的cateGory
     */
    private static final String CATEGORY = "lelaohui";
    public static final String USEDATA ="query.user.address";
    private SysVar var=null;
    public AddressRequerstParam(){
        var=SysVar.getInstance();
    }

    public RequestParam getRequestParamLLH(){
        RequestParam requestParam=new RequestParam();
        requestParam.addHeader(Protocol_KEY.CATEGORY,CATEGORY);
        requestParam.addHeader(Protocol_KEY.USERDATA, USEDATA);
        return requestParam;
    }

    /**
     * 查询用户地址信息
     * @param customerId
     * @param centerId
     * @return
     */
    public RequestParam queryUserAddress(String customerId,int centerId){
        RequestParam requestParam=getRequestParamLLH();
        requestParam.addHeader(Protocol_KEY.ACTION, NetContant.ServiceAction.GET_USER_ADDRESS_INFO);
        requestParam.addHeader(Protocol_KEY.USERDATA, "query.user.address");
        requestParam.addBody(Protocol_KEY.IS_SERVER_REQ,false);
        requestParam.addBody(Protocol_KEY.USERID,customerId);
        requestParam.addBody(Protocol_KEY.CENTERID,centerId);
        return requestParam;
    }

    /**
     * 删除用户地址信息
     * @param customerId
     * @param centerId
     * @param addressId
     * @return
     */
    public RequestParam deleteUserAddress(String customerId,int centerId,int addressId){
        RequestParam requestParam=getRequestParamLLH();
        requestParam.addHeader(Protocol_KEY.ACTION, NetContant.ServiceAction.DELETE_USER_ADDRESS_INFO);
        requestParam.addHeader(Protocol_KEY.USERDATA, "query.user.address");
        requestParam.addBody(Protocol_KEY.IS_SERVER_REQ,false);
        requestParam.addBody(Protocol_KEY.USERID,customerId);
        requestParam.addBody(Protocol_KEY.CENTERID,centerId);
        requestParam.addBody(Protocol_KEY.ADDRESS_ID,addressId);
        return requestParam;
    }

    /**
     * 添加或编辑用户地址信息
     * @param customerId
     * @param centerId
     * @param addressId
     * @return
     */
    public RequestParam addUserAddress(String customerId,int centerId,int addressId,
            String userNameStr,String addressContent,String phone){
        RequestParam requestParam=getRequestParamLLH();
        requestParam.addHeader(Protocol_KEY.ACTION, NetContant.ServiceAction.EDIT_USER_ADDRESS_INFO);
        requestParam.addHeader(Protocol_KEY.USERDATA, "query.user.address");
        requestParam.addBody(Protocol_KEY.IS_SERVER_REQ,false);
        requestParam.addBody(Protocol_KEY.USERID,customerId);
        requestParam.addBody(Protocol_KEY.CENTERID,centerId);
        requestParam.addBody(Protocol_KEY.USERNAME,userNameStr);
        requestParam.addBody(Protocol_KEY.ADDRESS_ID,addressId);
        requestParam.addBody(Protocol_KEY.ADDRESS_ID,phone);
        requestParam.addBody(Protocol_KEY.PHONE,phone);
        return requestParam;
    }
}
