package dq.lelaohui.com.lelaohuipad.controler;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import java.util.List;

import dq.lelaohui.com.lelaohuipad.LeLaohuiApp;
import dq.lelaohui.com.lelaohuipad.base.BaseOrderInfoControler;
import dq.lelaohui.com.lelaohuipad.base.LaoHuiBaseControler;
import dq.lelaohui.com.lelaohuipad.bean.BaseOrderCate;
import dq.lelaohui.com.lelaohuipad.bean.DefeatedCate;
import dq.lelaohui.com.lelaohuipad.bean.SerOrderInfoData;
import dq.lelaohui.com.lelaohuipad.bean.SerOrderInfoFinish;
import dq.lelaohui.com.lelaohuipad.bean.SerOrderInfoFinishCate;
import dq.lelaohui.com.lelaohuipad.bean.ServerOrderPayment;
import dq.lelaohui.com.lelaohuipad.bean.ServerOrderPaymentCate;
import dq.lelaohui.com.lelaohuipad.bean.UserAddressBean;
import dq.lelaohui.com.lelaohuipad.bean.UserAddressData;
import dq.lelaohui.com.lelaohuipad.util.ServerRequestParam;
import dq.lelaohui.com.nettylibrary.socket.RequestParam;
import dq.lelaohui.com.nettylibrary.util.ServiceNetContant;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.manager.BaseDaoOperator;

import static android.content.ContentValues.TAG;

/**
 * Created by ZTF on 2016/11/13.
 */

public class SerOrderInfoControler extends BaseOrderInfoControler {
    public static final String SUCCESS_CODE = "200";
    public static final String DEFEATED_CODE="202";
    private ServerRequestParam requestParam;
    private static SerOrderInfoControler serverControler = null;

    private SerOrderInfoControler() {
        requestParam = new ServerRequestParam();
    }

    public static SerOrderInfoControler getControler() {
        if (serverControler == null) {
            synchronized (SerOrderInfoControler.class) {
                if (serverControler == null) {
                    serverControler = new SerOrderInfoControler();
                }
            }
        }
        return serverControler;
    }


    protected boolean isOrderSuccInfoResponse(String action) {
        return ServiceNetContant.ServiceResponseAction.GET_SERVER_ORDER_SUCC_INFO_RESPONSE.equals(action);
    }

    protected boolean isUploadOrderPaymentResponse(String action) {
        return ServiceNetContant.ServiceResponseAction.UPLOAD_SERVER_ORDER_PAYMENY.equals(action);
    }

    protected boolean isSaveOrderInfo(String action) {
        return ServiceNetContant.ServiceResponseAction.SAVE_MOBILE_ORDER_INFO.equals(action);
    }

    protected boolean isAddressResponse(String action) {
        return ServiceNetContant.ServiceResponseAction.QUERY_USER_ADDRESS_RESPONSE.equals(action);
    }

    protected void UploadOrderPayMent(Bundle responseData) {
        String body = getResponseBody(responseData);
        Log.i(TAG,"serverOrderPayment =="+body.toString());
        DefeatedCate defeatedCate= (DefeatedCate)getJsonToObject(body, DefeatedCate.class);
        if(defeatedCate.getCode().equals(SUCCESS_CODE)){
            defeatedCate.getObj();
            orderInfoBack();
        }else if(defeatedCate.getCode().equals(DEFEATED_CODE)){
            orderInfoBack();
        }
}

    /**
     * 订单支付完成返回信息
     */
  public void   orderInfoBack(){
         Bundle bundle = new Bundle();
         bundle.putString("action", ServiceNetContant.ServiceResponseAction.UPLOAD_SERVER_ORDER_PAYMENY);
         getIControlerCallBack().result(bundle);
     }
    /**
     * 获取用户地址
     */
    public void doUserAddressInfo() {
        LeLaohuiApp app = (LeLaohuiApp) getContext();
        if (app == null) {
            throw new RuntimeException(" app is null exception");
        }
        RequestParam requestParam1 = requestParam.doUserAddressInfo();

        app.reqData(requestParam1);
    }

    @Override
    public void uploadShoppingData(BaseOrderCate serverOrderList) {
        SerOrderInfoData serverOrderListData= (SerOrderInfoData) serverOrderList;
        uploadShoppingData(serverOrderListData);
    }

    /**
     * 确认下单接口
     * @param serverOrderList
     */
    public void uploadShoppingData(SerOrderInfoData serverOrderList) {

        LeLaohuiApp app = (LeLaohuiApp) getContext();
        if (app == null) {
            throw new RuntimeException(" app is null exception");
        }
        RequestParam requestParam1 = requestParam.uploadShoppingData(serverOrderList);
        app.reqData(requestParam1);
    }

    /**
     * 上传支付接口
     * @param outTradeNo 订单号
     * @param payAmt 支付金额
     * @param payType 支付方式
     */
    public void doServerOrderPayment(String outTradeNo,String payAmt,String payType){
        LeLaohuiApp app = (LeLaohuiApp) getContext();
        if (app == null) {
            throw new RuntimeException(" app is null exception");
        }
        RequestParam requestParam1 = requestParam.doServerOrderPayment(outTradeNo,payAmt,payType);
        app.reqData(requestParam1);
    }

    @Override
    public void doParserSubmit(Bundle responseData) {
        String body = getResponseBody(responseData);
        Log.i(TAG,"GET_SERVER_ORDER_SUCC_INFO_RESPONSE =="+body.toString());
        SerOrderInfoFinishCate orderInfoFinishCate=(SerOrderInfoFinishCate)getJsonToObject(body,SerOrderInfoFinishCate.class);
        if(orderInfoFinishCate.getCode().equals(SUCCESS_CODE)){
            List<SerOrderInfoFinish>  orderInfoFinish=orderInfoFinishCate.getData();
            if(orderInfoFinish!=null&&orderInfoFinish.size()>0){
                SerOrderInfoFinish data=orderInfoFinish.get(0);
                Bundle bundle = new Bundle();
                bundle.putString("action", ServiceNetContant.ServiceResponseAction.GET_SERVER_ORDER_SUCC_INFO_RESPONSE);
                bundle.putParcelable("orderInfoFinish", data);
                getIControlerCallBack().result(bundle);
            }

        }
    }

    @Override
    public void doPayInfoResult(Bundle responseData) {
        String body = getResponseBody(responseData);
        ServerOrderPaymentCate orderPaymentCate = (ServerOrderPaymentCate) getJsonToObject(body, ServerOrderPaymentCate.class);
        if (orderPaymentCate.getCode().equals(SUCCESS_CODE)) {
            ServerOrderPayment orderPayment = orderPaymentCate.getData();
            Log.i(TAG, "doBusses: orderPayment==" + orderPayment.getAmountPayable());
            Bundle bundle = new Bundle();
            bundle.putString("action", ServiceNetContant.ServiceResponseAction.SAVE_MOBILE_ORDER_INFO);
            bundle.putParcelable("orderPayment", orderPayment);
            getIControlerCallBack().result(bundle);
        }
    }


    @Override
    public void getMyServerOrderInfo(Bundle orderInfo) {
        getMyServerOrderInfo(orderInfo.getString("orderInfo"));
    }

    /**
     * 查询订单返回接口
     * @param orderInfo
     */
    public void getMyServerOrderInfo(String orderInfo){
        LeLaohuiApp app = (LeLaohuiApp) getContext();
        if (app == null) {
            throw new RuntimeException(" app is null exception");
        }
        RequestParam requestParam1 = requestParam.getMyServerOrderInfo(orderInfo);
        app.reqData(requestParam1);
    }

    @Override
    public BaseDaoOperator getBaseDaoOperator() {
        return null;
    }
}
