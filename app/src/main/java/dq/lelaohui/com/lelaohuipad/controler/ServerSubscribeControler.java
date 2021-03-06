package dq.lelaohui.com.lelaohuipad.controler;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import dq.lelaohui.com.lelaohuipad.LeLaohuiApp;
import dq.lelaohui.com.lelaohuipad.base.LaoHuiBaseControler;
import dq.lelaohui.com.lelaohuipad.bean.FilterSubscribeCate;
import dq.lelaohui.com.lelaohuipad.bean.FilterSubscribeData;
import dq.lelaohui.com.lelaohuipad.bean.MySerSubescribeCate;
import dq.lelaohui.com.lelaohuipad.bean.MySerSubescribeData;
import dq.lelaohui.com.lelaohuipad.bean.SerSubescribeCate;
import dq.lelaohui.com.lelaohuipad.bean.SerSubescribeData;
import dq.lelaohui.com.lelaohuipad.bean.ServerPersonCate;
import dq.lelaohui.com.lelaohuipad.bean.ServerPersonData;
import dq.lelaohui.com.lelaohuipad.util.ServerSubscribeRequestParam;
import dq.lelaohui.com.nettylibrary.socket.RequestParam;
import dq.lelaohui.com.nettylibrary.util.ServiceNetContant;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.manager.BaseDaoOperator;

/**
 * Created by ZTF on 2016/11/25.
 * 服务预约控制
 */

public class ServerSubscribeControler  extends LaoHuiBaseControler {
    public static final String SUCCESS_CODE="200";
    private static ServerSubscribeControler serverSubscribeControler=null;
    private ServerSubscribeRequestParam requestParam;
    public final static  String MY_SUBSCRIBE_CENTER="mySubscribeCenter";
    private ServerSubscribeControler(){
        requestParam=new ServerSubscribeRequestParam();
    }
    public static ServerSubscribeControler getControler(){
        if(serverSubscribeControler==null){
            synchronized (ServerMenuControler.class){
                if(serverSubscribeControler==null){
                    serverSubscribeControler=new ServerSubscribeControler();
                }
            }
        }
        return serverSubscribeControler;
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

            if (action.equals(ServiceNetContant.ServiceResponseAction.GET_STOCK_DETAIL_BY_USER_RESPONSE)) {
                String body = getResponseBody(responseData);
                log("doBusses: " + responseData);
                SerSubescribeCate serSubescribeCate = (SerSubescribeCate) getJsonToObject(body, SerSubescribeCate.class);
                if (serSubescribeCate.getCode().equals(SUCCESS_CODE)) {
                    if (getIControlerCallBack() != null) {//解析数据成功，通知UI界面
                        List<SerSubescribeData> serSubescribeDataList = serSubescribeCate.getData();
                        log("serSubescribeDataList==" + serSubescribeDataList.size());
                        if (serSubescribeDataList != null && serSubescribeDataList.size() > 0) {
                            Bundle bundle = new Bundle();
                            bundle.putString("action", ServiceNetContant.ServiceResponseAction.GET_STOCK_DETAIL_BY_USER_RESPONSE);
                            bundle.putParcelableArrayList("serSubescribeDataList", (ArrayList<? extends Parcelable>) serSubescribeDataList);
                            getIControlerCallBack().result(bundle);
                        }
                    }
                } else {
                    log("data is null");
                }
            } else if (action.equals(ServiceNetContant.ServiceResponseAction.GET_SERVER_DETAIL_BY_INFO_RESPONSE)) {
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
            } else if (action.equals(ServiceNetContant.ServiceResponseAction.GET_SERVER_INFOS_RESPONSE)) {
                String body = getResponseBody(responseData);
                log("doBusses: " + responseData);
                ServerPersonCate serverPersonCate = (ServerPersonCate) getJsonToObject(body, ServerPersonCate.class);
                if (serverPersonCate.getCode() == 0) {
                    {
                        if (getIControlerCallBack() != null) {//解析数据成功，通知UI界面
                            List<ServerPersonData> data = serverPersonCate.getData();
                            if (data != null && data.size() > 0) {
                                Bundle bundle = new Bundle();
                                bundle.putString("action", ServiceNetContant.ServiceResponseAction.GET_SERVER_INFOS_RESPONSE);
                                bundle.putParcelableArrayList("serverPerson", (ArrayList<? extends Parcelable>) data);
                                getIControlerCallBack().result(bundle);
                            }
                        }
                    }
                } else {
                    log("data is null");
                }
            } else if (action.equals(ServiceNetContant.ServiceResponseAction.SEARCH_APPOINTMENT_FOR_APP_RESPONSE)) {
                String body = getResponseBody(responseData);
                log("doBusses: " + responseData);
                MySerSubescribeCate serTaskFinishCate = (MySerSubescribeCate) getJsonToObject(body, MySerSubescribeCate.class);
                if (serTaskFinishCate.getCode().equals(SUCCESS_CODE)) {
                    {
                        if (getIControlerCallBack() != null) {
                            List<MySerSubescribeData> data = serTaskFinishCate.getData();
                            if (data != null && data.size() > 0) {
                                Bundle bundle = new Bundle();
                                bundle.putString("action", ServiceNetContant.ServiceResponseAction.SEARCH_APPOINTMENT_FOR_APP_RESPONSE);
                                bundle.putParcelableArrayList("serTaskFinish", (ArrayList<? extends Parcelable>) data);
                                getIControlerCallBack().result(bundle);
                            }
                        }else{
                            Bundle bundle = new Bundle();
                            bundle.putString("action",MY_SUBSCRIBE_CENTER);
                            getIControlerCallBack().result(bundle);
                        }
                    }
                }else{
                    log("data is null");
                }
            } else if (action.equals(ServiceNetContant.ServiceResponseAction.SEARCH_APPOINTMENT_FOR_APP_RESPONSE)) {
                String body = getResponseBody(responseData);
                MySerSubescribeCate mySerSubescribeCate = (MySerSubescribeCate) getJsonToObject(body, MySerSubescribeCate.class);
                if (mySerSubescribeCate.getCode().equals(SUCCESS_CODE)) {
                    {
                        if (getIControlerCallBack() != null) {
                            List<MySerSubescribeData> data = mySerSubescribeCate.getData();
                            if (data != null && data.size() > 0) {
                                Bundle bundle = new Bundle();
                                bundle.putString("action", ServiceNetContant.ServiceResponseAction.SEARCH_APPOINTMENT_FOR_APP_RESPONSE);
                                bundle.putParcelableArrayList("mySerSubescribe", (ArrayList<? extends Parcelable>) data);
                                getIControlerCallBack().result(bundle);
                            }
                        }
                    }
                }else{
                    log("data is null");
                }
            }else if (action.equals(ServiceNetContant.ServiceResponseAction.CONFIRM_ORDER_SERVER_APP_RESONSE)){
                String body = getResponseBody(responseData);
                log("doBusses: " + responseData);
                FilterSubscribeCate filterSubscribeCate = (FilterSubscribeCate) getJsonToObject(body, FilterSubscribeCate.class);
                if (filterSubscribeCate.getCode().equals(SUCCESS_CODE)) {
                    setBundleData(filterSubscribeCate);
                } else {
                    setBundleData(filterSubscribeCate);
                    log("data is null");
                }
            }
        }
    }

    private void setBundleData(FilterSubscribeCate filterSubscribeCate) {
        if (getIControlerCallBack() != null) {//解析数据成功，通知UI界面
            Bundle bundle = new Bundle();
            bundle.putString("action", ServiceNetContant.ServiceResponseAction.CONFIRM_ORDER_SERVER_APP_RESONSE);
            bundle.putString("message",filterSubscribeCate.getMsg());
            getIControlerCallBack().result(bundle);
        }
    }

    /**
     * 查询用户库存信息
     * @param customerId
     */
    public void doQueryServerSetatlInfo(String customerId){
        LeLaohuiApp app= (LeLaohuiApp) getContext();
        if(app==null){
            throw  new RuntimeException(" app is null exception");
        }
        RequestParam requestParam1=requestParam.doQueryServerSetatlInfo(customerId);
        app.reqData(requestParam1);
    }
    /**
     * 查询用户已经预约过的数据
     * @param customerId
     */
    public void doQueryStockDetailByUser(String customerId){
        LeLaohuiApp app= (LeLaohuiApp) getContext();
        if(app==null){
            throw  new RuntimeException(" app is null exception");
        }
        RequestParam requestParam1=requestParam.doQueryStockDetailByUser(customerId);
        app.reqData(requestParam1);
    }
    /**
     * 查询指定时间内的库存信息
     * @param customerId
     * @param endTime
     * @param serStockDetailId
     */
    public void   doQueryServerSetatlData(String customerId,String endTime,long serStockDetailId){
        LeLaohuiApp app= (LeLaohuiApp) getContext();
        if(app==null){
            throw  new RuntimeException(" app is null exception");
        }
        RequestParam requestParam1=requestParam.doQueryServerSetatlData(customerId,endTime,serStockDetailId);
        app.reqData(requestParam1);
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
     * 我的预约执行
     * @param serTransId
     * @param transStatus
     */
    public void   doUploadReSend(long serTransId,String transStatus){
        LeLaohuiApp app= (LeLaohuiApp) getContext();
        if(app==null){
            throw  new RuntimeException(" app is null exception");
        }
        RequestParam requestParam1=requestParam.doUploadReSend(serTransId,transStatus);
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
    public BaseDaoOperator getBaseDaoOperator(String version) {
        return null;
    }

    @Override
    public BaseDaoOperator getBaseDaoOperator() {
        return null;
    }

}
