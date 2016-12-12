package dq.lelaohui.com.lelaohuipad.controler;

import android.os.Bundle;
import android.text.TextUtils;

import dq.lelaohui.com.lelaohuipad.LeLaohuiApp;
import dq.lelaohui.com.lelaohuipad.base.BaseOrderInfoControler;
import dq.lelaohui.com.lelaohuipad.bean.BaseOrderCate;
import dq.lelaohui.com.lelaohuipad.util.ServerRequestParam;
import dq.lelaohui.com.nettylibrary.socket.RequestParam;
import dq.lelaohui.com.nettylibrary.util.ServiceNetContant;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.manager.BaseDaoOperator;

/**
 * Created by ZTF on 2016/12/9.
 */

public class FootOrderInfoControler extends BaseOrderInfoControler {
    private static FootOrderInfoControler serverControler = null;

    private FootOrderInfoControler() {

    }

    public static FootOrderInfoControler getControler() {
        if (serverControler == null) {
            synchronized (SerOrderInfoControler.class) {
                if (serverControler == null) {
                    serverControler = new FootOrderInfoControler();
                }
            }
        }
        return serverControler;
    }
    @Override
    public void doUserAddressInfo() {
        LeLaohuiApp app = (LeLaohuiApp) getContext();
        if (app == null) {
            throw new RuntimeException(" app is null exception");
        }
        RequestParam requestParam1 = new ServerRequestParam().doUserAddressInfo();
        app.reqData(requestParam1);
    }

    @Override
    public void uploadShoppingData(BaseOrderCate serverOrderList) {

    }

    @Override
    public void doServerOrderPayment(String outTradeNo, String payAmt, String payType) {

    }

    @Override
    public void doParserSubmit(Bundle responseData) {

    }

    @Override
    public void doPayInfoResult(Bundle responseData) {

    }

    @Override
    protected boolean isOrderSuccInfoResponse(String action) {
        return false;
    }

    @Override
    protected boolean isUploadOrderPaymentResponse(String action) {
        return false;
    }

    @Override
    protected boolean isSaveOrderInfo(String action) {
        return false;
    }

    @Override
    protected boolean isAddressResponse(String action) {
        return ServiceNetContant.ServiceResponseAction.QUERY_USER_ADDRESS_RESPONSE.equals(action);
    }

    @Override
    protected void UploadOrderPayMent(Bundle responseData) {

    }

    @Override
    public void getMyServerOrderInfo(Bundle orderInfo) {

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
        if (isAddressResponse(action)) {
            parserAddress(responseData);
        } else if (isSaveOrderInfo(action)) {
            doPayInfoResult(responseData);
        }else if (isUploadOrderPaymentResponse(action)){
            UploadOrderPayMent(responseData);
        }else if(isOrderSuccInfoResponse(action)){
            doParserSubmit(responseData);
        }else{
            FootterControler.getControler().setContext(getContext());
            FootterControler.getControler().setIControlerCallBack(getIControlerCallBack());
            FootterControler.getControler().doBusses(responseData);
        }
    }

    @Override
    public BaseDaoOperator getBaseDaoOperator() {
        return null;
    }
}
