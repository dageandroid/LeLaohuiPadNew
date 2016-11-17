package dq.lelaohui.com.lelaohuipad.controler;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.reflect.TypeToken;

import java.util.List;

import dq.lelaohui.com.lelaohuipad.LeLaohuiApp;
import dq.lelaohui.com.lelaohuipad.base.LaoHuiBaseControler;
import dq.lelaohui.com.lelaohuipad.bean.DefeatedCate;
import dq.lelaohui.com.lelaohuipad.bean.SerOrderInfoData;
import dq.lelaohui.com.lelaohuipad.bean.SerOrderInfoFinish;
import dq.lelaohui.com.lelaohuipad.bean.SerOrderInfoFinishCate;
import dq.lelaohui.com.lelaohuipad.bean.ServerCate;
import dq.lelaohui.com.lelaohuipad.bean.ServerOrderPayment;
import dq.lelaohui.com.lelaohuipad.bean.ServerOrderPaymentCate;
import dq.lelaohui.com.lelaohuipad.bean.UserAddressBean;
import dq.lelaohui.com.lelaohuipad.bean.UserAddressData;
import dq.lelaohui.com.lelaohuipad.dao.ProCateServiceDaoOperator;
import dq.lelaohui.com.lelaohuipad.dao.ProMenumServiceDaoOperator;
import dq.lelaohui.com.lelaohuipad.util.ServerRequestParam;
import dq.lelaohui.com.nettylibrary.socket.RequestParam;
import dq.lelaohui.com.nettylibrary.util.ServiceNetContant;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.ProCateService;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.manager.BaseDaoOperator;

import static android.content.ContentValues.TAG;

/**
 * Created by ZTF on 2016/11/13.
 */

public class SerOrderInfoControler extends LaoHuiBaseControler {
    public static final String SUCCESS_CODE = "200";
    public static final String DEFEATED_CODE="202";
    private static SerOrderInfoControler serverControler = null;
    private ServerRequestParam requestParam;

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
                        UserAddressData data1 = data.get(0);
                        Bundle bundle = new Bundle();
                        bundle.putString("action", ServiceNetContant.ServiceResponseAction.QUERY_USER_ADDRESS_RESPONSE);
                        bundle.putParcelable("userAddress", data1);
                        getIControlerCallBack().result(bundle);
                    }

                }
            }
        } else if (ServiceNetContant.ServiceResponseAction.SAVE_MOBILE_ORDER_INFO.equals(action)) {
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
        }else if (ServiceNetContant.ServiceResponseAction.UPLOAD_SERVER_ORDER_PAYMENY.equals(action)){
            String body = getResponseBody(responseData);
            Log.i(TAG,"serverOrderPayment =="+body.toString());
            DefeatedCate defeatedCate= (DefeatedCate)getJsonToObject(body, DefeatedCate.class);
            if(defeatedCate.getCode().equals(SUCCESS_CODE)){
                defeatedCate.getObj();
                orderInfoBack();
            }else if(defeatedCate.getCode().equals(DEFEATED_CODE)){
                orderInfoBack();
            }

        }else if(ServiceNetContant.ServiceResponseAction.GET_SERVER_ORDER_SUCC_INFO_RESPONSE.equals(action)){
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

            }else if(orderInfoFinishCate.getCode().equals(DEFEATED_CODE)){
            }
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
    public BaseDaoOperator getBaseDaoOperator(String version) {
        return null;
    }

    @Override
    public BaseDaoOperator getBaseDaoOperator() {

        return getBaseDaoOperator(null);
    }
}
