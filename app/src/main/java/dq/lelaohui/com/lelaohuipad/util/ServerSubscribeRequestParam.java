package dq.lelaohui.com.lelaohuipad.util;

import android.os.Bundle;
import android.util.Log;

import dq.lelaohui.com.nettylibrary.socket.RequestParam;
import dq.lelaohui.com.nettylibrary.util.NetContant;
import dq.lelaohui.com.nettylibrary.util.Protocol_KEY;

/**
 * Created by ZTF on 2016/11/23.
 * 服务预约相关方法
 */

public class ServerSubscribeRequestParam {
    private SysVar var=null;
    /**
     * 获取服务数据请求的cateGory
     */
    private static final String CATEGORY = "lelaohui_server";
    private static final String CATEGORY_USER = "lelaohui";
    private static final String USEDATA ="query.subscribe.service";
    private static final String SER_STOCK_DETAIL_ID ="serStockDetailId";
    //服务员类型
    private static final int USER_TYPE_INT=3;
    /**
     * 获取库存参数
     */
    private static final String GET_STOCK_DETAIL_BY_USER ="getStockDetailByUser";
    public static final String SEARCH_APPOINTMENT_FOR_APP = "searchAppointmentForApp";

    /**
     * 根据当前时间查询库存信息
     */
    private static final String GET_DETAIL_BY_USER_AND_DATE ="getDetailByUserAndDate";
    /**
     * 提交服务预约Item
     */
    private static final String SERVICE_ITEM_ID ="serviceItemId";

    /**
     * 提交预约操作
     */
    private static final String UPDATE_APPOINTMENT_STATUS_FOR_APP ="updateAppointmentStatusForApp";
    public ServerSubscribeRequestParam(){
        var=SysVar.getInstance();
    }

    private RequestParam getRequestParam(){
        RequestParam requestParam=new RequestParam();
        requestParam.addHeader(Protocol_KEY.CATEGORY,CATEGORY);
        requestParam.addHeader(Protocol_KEY.USERDATA, USEDATA);
        return requestParam;
    }
    private RequestParam getRequestParamLLH(){
        RequestParam requestParam=new RequestParam();
        requestParam.addHeader(Protocol_KEY.CATEGORY,CATEGORY_USER);
        requestParam.addHeader(Protocol_KEY.USERDATA, USEDATA);
        return requestParam;
    }
    /**
     * 查询库存信息
     * @return
     */
    public RequestParam doQueryServerSetatlInfo(String customerId){
        RequestParam requestParam=getRequestParam();
        requestParam.addHeader(Protocol_KEY.ACTION, NetContant.ServiceAction.GET_STOCK_DETAIL_BY_USER);
        requestParam.addBody(Protocol_KEY.IS_SERVER_REQ,true);

        Bundle parmBundle=new Bundle();
        setOrgBundleParm(parmBundle);
        parmBundle.putString(Protocol_KEY.CUSTOMER_ID,customerId);
        requestParam.addBody(GET_STOCK_DETAIL_BY_USER,parmBundle);
        Log.i("","parmBundle==="+parmBundle.toString());
        return requestParam;
    }

    public RequestParam doQueryServerPersonInfo(long orgId,long orgType,long serviceItemId){
        RequestParam requestParam=getRequestParamLLH();
        requestParam.addHeader(Protocol_KEY.ACTION, NetContant.ServiceAction.GET_SERVER_INFOS);
        requestParam.addBody(Protocol_KEY.IS_SERVER_REQ,false);
        Bundle parmBundle=new Bundle();
        setOrgBundleParm(parmBundle);
        requestParam.addBody(Protocol_KEY.ORG_ID,orgId);
        requestParam.addBody(Protocol_KEY.ORG_TYPE,orgType);
        requestParam.addBody(Protocol_KEY.USER_TYPE,USER_TYPE_INT);
        requestParam.addBody(SERVICE_ITEM_ID,serviceItemId);
        return requestParam;
    }
    /**
     * 根据时间查询当前用户的库存信息
     * @param customerId
     * @param endTime
     * @param serStockDetailId
     * @return
     */
    public RequestParam   doQueryServerSetatlData(String customerId, String endTime, long serStockDetailId){
        RequestParam requestParam=getRequestParam();
        requestParam.addHeader(Protocol_KEY.ACTION, NetContant.ServiceAction.GET_SERVER_DETAIL_BY_INFO);
        requestParam.addBody(Protocol_KEY.IS_SERVER_REQ,true);

        Bundle parmBundle=new Bundle();
        setOrgBundleParm(parmBundle);
        parmBundle.putString(Protocol_KEY.CUSTOMER_ID,customerId);
        parmBundle.putString(Protocol_KEY.END_TIME,endTime);
        parmBundle.putLong(SER_STOCK_DETAIL_ID,serStockDetailId);
        requestParam.addBody(GET_DETAIL_BY_USER_AND_DATE,parmBundle);
        return requestParam;
    }



/**
 * 提交用户预约信息
 */

//public RequestParam   doUploadServer(String yearData,String remarkStr,long serStockDetailId,List<OrderBean> orderBeanList){
//    RequestParam requestParam=getRequestParam();
//    requestParam.addHeader(Protocol_KEY.ACTION, NetContant.ServiceAction.CONFIRM_ORDER_SERVER_APP);
//    requestParam.addBody(Protocol_KEY.IS_SERVER_REQ,true);
//
//    Bundle parmBundle=new Bundle();
//    setOrgBundleParm(parmBundle);
//    parmBundle.putString(Protocol_KEY.CUSTOMER_ID,customerId);
//    parmBundle.putString(Protocol_KEY.END_TIME,endTime);
//    parmBundle.putLong(SER_STOCK_DETAIL_ID,serStockDetailId);
//    requestParam.addBody(GET_DETAIL_BY_USER_AND_DATE,parmBundle);
//    return requestParam;
//}
/**查询用户已经预约的信息
 *
 */


    /**
     * 获取数据的用户库存信息
     * @param parmBundle
     */
    private void setOrgBundleParm(Bundle parmBundle) {
        if(var.getOrgType()==3){
            parmBundle.putString(Protocol_KEY.PACKORG_ID,String.valueOf(var.getOrgId()));
            parmBundle.putString(Protocol_KEY.PACKORG_TYPE_ID,String.valueOf(var.getOrgType()));
        }else{
            parmBundle.putString(Protocol_KEY.SUPPLIERID,String.valueOf(var.getOrgId()));
            parmBundle.putString(Protocol_KEY.SUPPLIER_TYPE_ID,String.valueOf(var.getOrgType()));
            parmBundle.putString(Protocol_KEY.PACK_SUPPLIER_ID,String.valueOf(var.getCenterId()));
            parmBundle.putString(Protocol_KEY.PACK_SUPPLIER_TYPE_ID,String.valueOf(var.getCenterType()));
        }
    }

    /**
     * 查询用户预约记录
     * @param customerId
     * @return
     */
    public RequestParam   doQueryStockDetailByUser(String customerId){
        RequestParam requestParam=getRequestParam();
        requestParam.addHeader(Protocol_KEY.ACTION, NetContant.ServiceAction.SEARCH_APPOINTMENT_FOR_APP);
        requestParam.addBody(Protocol_KEY.IS_SERVER_REQ,true);
        requestParam.addBody(Protocol_KEY.CUSTOMER_ID,customerId);
        return requestParam;
    }
    /**
     * 查询用户预约记录
     * @param serTransId
     * @param transStatus
     * @return
     */
    public RequestParam   doUploadReSend(long serTransId,String transStatus){
        RequestParam requestParam=getRequestParam();
        requestParam.addHeader(Protocol_KEY.ACTION, NetContant.ServiceAction.SEARCH_APPOINTMENT_FOR_APP);
        requestParam.addBody(Protocol_KEY.IS_SERVER_REQ,true);
        Bundle parmBundle=new Bundle();
        parmBundle.putLong("serTransId",serTransId);
        parmBundle.putString("transStatus",transStatus);
        requestParam.addBody(UPDATE_APPOINTMENT_STATUS_FOR_APP,parmBundle);
        return requestParam;
    }
    /**
     * 预约查询用户已完成任务
     */
    public RequestParam   doStockDetailByUser(String customerId,String startTime,String stopTime,String transStatus){
        RequestParam requestParam=getRequestParam();
        requestParam.addHeader(Protocol_KEY.ACTION, NetContant.ServiceAction.SEARCH_APPOINTMENT_FOR_APP);
        requestParam.addBody(Protocol_KEY.IS_SERVER_REQ,true);
        Bundle parmBundle=new Bundle();
        parmBundle.putString("customerId",customerId);
        parmBundle.putString("startDateStr",startTime);
        parmBundle.putString("endDateStr",stopTime);
        parmBundle.putString("transStatus",transStatus);
        requestParam.addBody(SEARCH_APPOINTMENT_FOR_APP,parmBundle);
        return requestParam;
    }


}
