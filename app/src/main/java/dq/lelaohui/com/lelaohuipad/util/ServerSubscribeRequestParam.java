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

    /**
     * 获取库存参数
     */
    private static final String GET_STOCK_DETAIL_BY_USER ="getStockDetailByUser";

    /**
     * 根据当前时间查询库存信息
     */
    private static final String GET_DETAIL_BY_USER_AND_DATE ="getDetailByUserAndDate";

    public ServerSubscribeRequestParam(){
        var=SysVar.getInstance();
    }

    private RequestParam getRequestParam(){
        RequestParam requestParam=new RequestParam();
        requestParam.addHeader(Protocol_KEY.CATEGORY,CATEGORY);
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
}
