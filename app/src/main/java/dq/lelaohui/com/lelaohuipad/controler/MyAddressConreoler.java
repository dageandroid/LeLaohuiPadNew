package dq.lelaohui.com.lelaohuipad.controler;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dq.lelaohui.com.lelaohuipad.LeLaohuiApp;
import dq.lelaohui.com.lelaohuipad.base.LaoHuiBaseControler;
import dq.lelaohui.com.lelaohuipad.bean.UserAddressBean;
import dq.lelaohui.com.lelaohuipad.bean.UserAddressData;
import dq.lelaohui.com.lelaohuipad.util.AddressRequerstParam;
import dq.lelaohui.com.nettylibrary.socket.RequestParam;
import dq.lelaohui.com.nettylibrary.util.ServiceNetContant;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.manager.BaseDaoOperator;

/**
 * Created by ZTF on 2016/11/17.
 * 用戶地址信息相关
 */

public class MyAddressConreoler  extends LaoHuiBaseControler {
  public  static MyAddressConreoler myAddressConreoler=null;

    public static MyAddressConreoler getControler() {
        if (myAddressConreoler == null) {
            synchronized (SerOrderInfoControler.class) {
                if (myAddressConreoler == null) {
                    myAddressConreoler = new MyAddressConreoler();
                }
            }
        }
        return myAddressConreoler;
    }
    AddressRequerstParam  addressRequerstParam;

    public  MyAddressConreoler(){
      addressRequerstParam=new AddressRequerstParam();
    }
    @Override
    public void doBusses(Bundle responseData) {

        if (responseData == null) {
            log(getClass().getName() + " doBusses 数据异常");
            return;
        }
        String action = getResponseAction(responseData);
        if (TextUtils.isEmpty(action)) {
            log("解析数据异常，异常原因：action is null");
        }
        if (ServiceNetContant.ServiceResponseAction.QUERY_USER_ADDRESS_RESPONSE.equals(action)) {
            String body = getResponseBody(responseData);
            UserAddressBean addressCate = (UserAddressBean) getJsonToObject(body, UserAddressBean.class);
            log("doBusses: " + responseData);
            if (addressCate.getCode() == 0) {
                if (getIControlerCallBack() != null) {//解析数据成功，通知UI界面
                    List<UserAddressData> data = addressCate.getData();
                    if (data != null && data.size() > 0) {
                        Bundle bundle = new Bundle();
                        bundle.putString("action", ServiceNetContant.ServiceResponseAction.QUERY_USER_ADDRESS_RESPONSE);
                        bundle.putParcelableArrayList("userAddress", (ArrayList<? extends Parcelable>) data);
                        getIControlerCallBack().result(bundle);
                    }

                }
            }
        }else if(ServiceNetContant.ServiceResponseAction.DELETE_USER_ADDRESS_RESPONSE.equals(action)){
            String body = getResponseBody(responseData);
            log("doBusses: " + responseData);
            UserAddressBean addressCate = (UserAddressBean) getJsonToObject(body, UserAddressBean.class);
            if(addressCate.getCode()==0){
                Bundle bundle = new Bundle();
                bundle.putString("action", ServiceNetContant.ServiceResponseAction.DELETE_USER_ADDRESS_RESPONSE);
                getIControlerCallBack().result(bundle);
            }else{
                Log.i("add address","删除地址失败。。。");
            }
        }else if(ServiceNetContant.ServiceResponseAction.ADD_USER_ADDRESS_RESPONSE.equals(action)){
            String body = getResponseBody(responseData);
            log("doBusses: " + responseData);
            UserAddressBean addressCate = (UserAddressBean) getJsonToObject(body, UserAddressBean.class);
            if(addressCate.getCode()==0){
                Bundle bundle = new Bundle();
                bundle.putString("action", ServiceNetContant.ServiceResponseAction.ADD_USER_ADDRESS_RESPONSE);
                getIControlerCallBack().result(bundle);
            }else{
                Log.i("add address","添加地址失败。。。");
            }
        }

    }

    /**
     * 查询用户地址信息
     * @param userId
     * @param centerId
     */
    public void queryUserAddressInfo(String userId,int centerId) {

        LeLaohuiApp app = (LeLaohuiApp) getContext();
        if (app == null) {
            throw new RuntimeException(" app is null exception");
        }
        RequestParam requestParam = addressRequerstParam.queryUserAddress(userId,centerId);
        app.reqData(requestParam);
    }

    /**
     * 删除地址
     * @param userId  用户Id
     * @param centerId  用户机构Id
     * @param addressId 用户地址Id
     */
    public void deleteUserAddress(String userId,int centerId,int addressId) {

        LeLaohuiApp app = (LeLaohuiApp) getContext();
        if (app == null) {
            throw new RuntimeException(" app is null exception");
        }
        RequestParam requestParam = addressRequerstParam.deleteUserAddress(userId,centerId,addressId);
        app.reqData(requestParam);
    }

    /**
     * 编辑地址
     * @param userId
     * @param centerId
     * @param addressId
     */
    public void editDefaultAddress(String userId,int centerId,int addressId) {
        LeLaohuiApp app = (LeLaohuiApp) getContext();
        if (app == null) {
            throw new RuntimeException(" app is null exception");
        }
        RequestParam requestParam = addressRequerstParam.deleteUserAddress(userId,centerId,addressId);
        app.reqData(requestParam);
    }

    /**
     * 添加用户地址
     * @param userNameStr
     * @param addressContent
     * @param phone
     */
    public void addUserAddress(String customerId,int centerId,int addressId,
                               String userNameStr,String addressContent,String phone) {
        LeLaohuiApp app = (LeLaohuiApp) getContext();
        if (app == null) {
            throw new RuntimeException(" app is null exception");
        }
        RequestParam requestParam = addressRequerstParam.addUserAddress(customerId,centerId,
                addressId,userNameStr,addressContent,phone);
        app.reqData(requestParam);
    }

    @Override
    public BaseDaoOperator getBaseDaoOperator(String version) {
        return null;
    }

    @Override
    public BaseDaoOperator getBaseDaoOperator() {
        return null;
    }

}
