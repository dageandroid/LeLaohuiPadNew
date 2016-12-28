package dq.lelaohui.com.lelaohuipad.controler;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dq.lelaohui.com.lelaohuipad.LeLaohuiApp;
import dq.lelaohui.com.lelaohuipad.base.LaoHuiBaseControler;
import dq.lelaohui.com.lelaohuipad.bean.MyDeviceInfo;
import dq.lelaohui.com.lelaohuipad.bean.MyDeviceInfoCate;
import dq.lelaohui.com.lelaohuipad.util.AddressRequerstParam;
import dq.lelaohui.com.lelaohuipad.util.MyDeviceInfoRequerstParam;
import dq.lelaohui.com.nettylibrary.socket.RequestParam;
import dq.lelaohui.com.nettylibrary.util.ServiceNetContant;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.manager.BaseDaoOperator;

import static android.content.ContentValues.TAG;

/**
 * Created by ZTF on 2016/10/26.
 */

public class MainControler extends LaoHuiBaseControler {
    private static MainControler mainControler=null;
    public static MainControler getControler(){
        if(mainControler==null){
            synchronized (MainControler.class){
                if(mainControler==null){
                    mainControler=new MainControler();
                }
            }
        }
        return  mainControler;
    }

    private MyDeviceInfoRequerstParam myDeviceInfoRequerstParam;
    public  MainControler(){
        myDeviceInfoRequerstParam=new MyDeviceInfoRequerstParam();
    }
    @Override
    public BaseDaoOperator getBaseDaoOperator() {
        return null;
    }

    @Override
    public void doBusses(Bundle responseData) {
        if(responseData==null){
            log(getClass().getName()+" doBusses 数据异常");
            return ;
        }
        String action=getResponseAction(responseData);
        if(TextUtils.isEmpty(action)){
            log("解析数据异常，异常原因：action is null");
        }
        if(ServiceNetContant.ServiceResponseAction.GET_DEVICE_STATUS_INFOS_RESONSE.equals(action)){
            String body=getResponseBody(responseData);
            Log.i(TAG,"body=="+body);
            MyDeviceInfoCate deviceInfoCate=(MyDeviceInfoCate)getJsonToObject(body, MyDeviceInfoCate.class);
            if (deviceInfoCate.getCode() == 0) {
                if (getIControlerCallBack() != null) {//解析数据成功，通知UI界面
                    List<MyDeviceInfo> data = deviceInfoCate.getData();
                    if (data != null && data.size() > 0) {
                        Bundle bundle = new Bundle();
                        bundle.putString("action", ServiceNetContant.ServiceResponseAction.GET_DEVICE_STATUS_INFOS_RESONSE);
                        bundle.putParcelableArrayList("userDevice", (ArrayList<? extends Parcelable>) data);
                        getIControlerCallBack().result(bundle);
                    }
                }
            } else {
            Log.i(TAG,"data is null");
        }
    }
    }


    /**
     * 查询用户地址信息
     * @param userId
     * @param centerId
     */
    public void queryUserDeviceInfo(String userId,int centerId) {
        LeLaohuiApp app = (LeLaohuiApp) getContext();
        if (app == null) {
            throw new RuntimeException(" app is null exception");
        }
        RequestParam requestParam = myDeviceInfoRequerstParam.queryUserDeviceInfo(userId,centerId,getSysVar().getOrgId(),getSysVar().getOrgType());
        app.reqData(requestParam);
    }

}
