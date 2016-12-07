package dq.lelaohui.com.lelaohuipad.controler;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dq.lelaohui.com.lelaohuipad.LeLaohuiApp;
import dq.lelaohui.com.lelaohuipad.LeLaohuiMainActivity;
import dq.lelaohui.com.lelaohuipad.base.LaoHuiBaseControler;
import dq.lelaohui.com.lelaohuipad.bean.FilterSubscribeCate;
import dq.lelaohui.com.lelaohuipad.bean.FilterSubscribeData;
import dq.lelaohui.com.lelaohuipad.bean.LogonBena;
import dq.lelaohui.com.lelaohuipad.bean.MySerSubescribeCate;
import dq.lelaohui.com.lelaohuipad.bean.MySerSubescribeData;
import dq.lelaohui.com.lelaohuipad.bean.SerSubescribeCate;
import dq.lelaohui.com.lelaohuipad.bean.SerSubescribeData;
import dq.lelaohui.com.lelaohuipad.bean.SerTaskFinishCate;
import dq.lelaohui.com.lelaohuipad.bean.SerTaskFinishData;
import dq.lelaohui.com.lelaohuipad.bean.ServerPersonCate;
import dq.lelaohui.com.lelaohuipad.bean.ServerPersonData;
import dq.lelaohui.com.lelaohuipad.util.ServerRequestParam;
import dq.lelaohui.com.lelaohuipad.util.ServerSubscribeRequestParam;
import dq.lelaohui.com.nettylibrary.socket.LlhResponseHandler;
import dq.lelaohui.com.nettylibrary.socket.RequestParam;
import dq.lelaohui.com.nettylibrary.util.NetContant;
import dq.lelaohui.com.nettylibrary.util.ServiceNetContant;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.manager.BaseDaoOperator;

/**
 * Created by ZTF on 2016/12/5.
 * 服务预约控制
 */

public class MyServerTaskControler extends LaoHuiBaseControler {
    public static final String SUCCESS_CODE="200";
    private static MyServerTaskControler  myServerTaskControler=null;
    private ServerSubscribeRequestParam requestParam;
    private MyServerTaskControler(){
        requestParam=new ServerSubscribeRequestParam();
    }
    public static MyServerTaskControler getControler(){
        if(myServerTaskControler==null){
            synchronized (ServerMenuControler.class){
                if(myServerTaskControler==null){
                    myServerTaskControler=new MyServerTaskControler();
                }
            }
        }
        return myServerTaskControler;
    }

    @Override
    public void doBusses(Bundle responseData) {
        {
            if (responseData == null) {
                log(getClass().getName() + " doBusses 数据异常");
                return;
            }
            String action = getResponseAction(responseData);
            if (TextUtils.isEmpty(action)) {
                log("解析数据异常，异常原因：action is null");
            }

         if (action.equals(ServiceNetContant.ServiceResponseAction.GET_SERVER_DETAIL_BY_INFO_RESPONSE)) {
                String body = getResponseBody(responseData);
                log("doBusses: " + responseData);
                FilterSubscribeCate filterSubscribeCate = (FilterSubscribeCate) getJsonToObject(body, FilterSubscribeCate.class);
                if (filterSubscribeCate.getCode().equals(SUCCESS_CODE)) {
                    if (getIControlerCallBack() != null) {//解析数据成功，通知UI界面
                        List<FilterSubscribeData> filterSubscribeDataList = filterSubscribeCate.getData();
                        if (filterSubscribeDataList != null && filterSubscribeDataList.size() > 0) {
                            Bundle bundle = new Bundle();
                            bundle.putString("action", ServiceNetContant.ServiceResponseAction.GET_SERVER_DETAIL_BY_INFO_RESPONSE);
                            bundle.putParcelableArrayList("filterSubscribeDataList", (ArrayList<? extends Parcelable>) filterSubscribeDataList);
                            getIControlerCallBack().result(bundle);
                        }
                    }
                } else {
                    log("data is null");
                }
            }  else if (action.equals(ServiceNetContant.ServiceResponseAction.SEARCH_APPOINTMENT_FOR_APP_RESPONSE)) {
                String body = getResponseBody(responseData);
                SerTaskFinishCate serTaskFinishCate = (SerTaskFinishCate) getJsonToObject(body, SerTaskFinishCate.class);
                if (serTaskFinishCate.getCode().equals(SUCCESS_CODE)) {
                    {
                        if (getIControlerCallBack() != null) {
                            List<SerTaskFinishData> data = serTaskFinishCate.getData();
                            if (data != null && data.size() > 0) {
                                Bundle bundle = new Bundle();
                                bundle.putString("action", ServiceNetContant.ServiceResponseAction.SEARCH_APPOINTMENT_FOR_APP_RESPONSE);
                                bundle.putParcelableArrayList("serTaskFinish", (ArrayList<? extends Parcelable>) data);
                                getIControlerCallBack().result(bundle);
                            }
                        }
                    }
                }else{
                    log("data is null");
                }
            }
        }
    }



    /**
     * 获取预约任务完成的数据
     * @param customerId
     * @param startTime
     * @param stopTime
     * @param transStatus
     */
    public void   doStockDetailByUser(String customerId,String startTime,String stopTime,String transStatus){
        LeLaohuiApp app= (LeLaohuiApp) getContext();
        if(app==null){
            throw  new RuntimeException(" app is null exception");
        }
        RequestParam requestParam1=requestParam.doStockDetailByUser(customerId,startTime,stopTime,transStatus);
        app.reqData(requestParam1);
    }
    /**
     * 获取所有服务员相关信息
     * @param orgId
     * @param orgType
     * @param serviceItemId
     */
    public void   doQueryServerPersonInfo(long orgId,long orgType,long serviceItemId){
        LeLaohuiApp app= (LeLaohuiApp) getContext();
        if(app==null){
            throw  new RuntimeException(" app is null exception");
        }
        RequestParam requestParam1=requestParam.doQueryServerPersonInfo(orgId,orgType,serviceItemId);
        app.reqData(requestParam1);
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
