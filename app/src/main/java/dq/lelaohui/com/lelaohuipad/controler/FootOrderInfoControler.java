package dq.lelaohui.com.lelaohuipad.controler;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dq.lelaohui.com.lelaohuipad.LeLaohuiApp;
import dq.lelaohui.com.lelaohuipad.base.BaseOrderInfoControler;
import dq.lelaohui.com.lelaohuipad.bean.BaseOrderCate;
import dq.lelaohui.com.lelaohuipad.bean.FoodTradeNoCate;
import dq.lelaohui.com.lelaohuipad.bean.FoodTradeNoData;
import dq.lelaohui.com.lelaohuipad.bean.SubShopFoodBean;
import dq.lelaohui.com.lelaohuipad.util.FootRequestUtil;
import dq.lelaohui.com.lelaohuipad.util.ServerRequestParam;
import dq.lelaohui.com.nettylibrary.socket.RequestParam;
import dq.lelaohui.com.nettylibrary.util.NetContant;
import dq.lelaohui.com.nettylibrary.util.Protocol_KEY;
import dq.lelaohui.com.nettylibrary.util.ServiceNetContant;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.manager.BaseDaoOperator;

import static android.content.ContentValues.TAG;

/**
 * Created by ZTF on 2016/12/9.
 */

public class FootOrderInfoControler extends BaseOrderInfoControler {
    private static FootOrderInfoControler serverControler = null;
    private String TAG=getClass().getSimpleName();
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
        SubShopFoodBean subShopFoodBean =  (SubShopFoodBean)serverOrderList;
        if (subShopFoodBean!=null){
            int isDistr= subShopFoodBean.getIsDistr();
            int payType=subShopFoodBean.getPayType();
            int totalMoney=subShopFoodBean.getTotalMoney();
            String addressStr=subShopFoodBean.getAddressStr();
            String phone=subShopFoodBean.getPhone();
            String  isScope=subShopFoodBean.getIsScope();
            int mealtime=subShopFoodBean.getMealtime();
            String userIdStr=subShopFoodBean.getUserIdStr();
            String buyUserId=subShopFoodBean.getBuyUserId();
            ArrayList<Bundle> cofirmOrderData=subShopFoodBean.getCofirmOrderData();
            confirmFoodOrder( isDistr, payType, totalMoney, addressStr, phone,
                      isScope,  mealtime,  userIdStr, buyUserId,cofirmOrderData);
        }
    }
    /**
     * 提交餐品信息
     */
    private void confirmFoodOrder(int isDistr,int payType,int totalMoney,String addressStr,String phone,
                                 String  isScope, int mealtime, String userIdStr,String buyUserId, ArrayList<Bundle> cofirmOrderData){
        LeLaohuiApp app= (LeLaohuiApp) getContext();
        if(app==null){
            throw  new RuntimeException(" app is null exception");
        }
        FootRequestUtil requestUtil=new FootRequestUtil(String.valueOf(getCenterId()),String.valueOf(getOrgId()),String.valueOf(getOrgType()));
        RequestParam requestParam =  requestUtil.getRequestParamNoOrgId(isScope, userIdStr, NetContant.ServiceAction.FOOD_ORDER_CONFIRM);
        requestParam.addBody(Protocol_KEY.IS_DISTR,isDistr);
        requestParam.addBody(Protocol_KEY.BUY_USER_ID,buyUserId);
        requestParam.addBody(Protocol_KEY.PAY_TYPE,payType);
        requestParam.addBody(Protocol_KEY.TOTAL_MONEY,totalMoney);
        requestParam.addBody(Protocol_KEY.OLD_MAN_ID,buyUserId);
        requestParam.addBody(Protocol_KEY.ORDER_DATA,cofirmOrderData);
        requestParam.addBody(Protocol_KEY.CUSTOMER_ID,buyUserId);
        requestParam.addBody(Protocol_KEY.ADDRESS,addressStr);
        requestParam.addBody(Protocol_KEY.PHONE,phone);
        requestParam.addBody(Protocol_KEY.MEALTIME,mealtime);
        requestParam.addBody(Protocol_KEY.ISSCOPE,Integer.parseInt(isScope));
        app.reqData(requestParam);
    }
    @Override
    public void doServerOrderPayment(String outTradeNo, String payAmt, String payType) {
        Log.i(TAG, "doServerOrderPayment: "+outTradeNo);
        foodPaymentOrder(outTradeNo,getSysVar().getUserName(),Double.parseDouble(payAmt),getSysVar().getUserId());
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

    public void foodPaymentOrder(String  outTradeNo,String userName,double totalFee,String  oldManId){
        LeLaohuiApp app= (LeLaohuiApp) getContext();
        if(app==null){
            throw  new RuntimeException(" app is null exception");
        }
        FootRequestUtil requestUtil=new FootRequestUtil(String.valueOf(getCenterId()),String.valueOf(getOrgId()),String.valueOf(getOrgType()));
        RequestParam requestParam =requestUtil.getRequestParam(NetContant.ServiceAction.FOOD_ORDER_PAYMEN);
        requestParam.addBody(Protocol_KEY.USERID,oldManId);
        requestParam.addBody(Protocol_KEY.REAL_NAME,userName);
        requestParam.addBody(Protocol_KEY.ORDER_NO,outTradeNo);
        requestParam.addBody(Protocol_KEY.PAY_AMT,totalFee);
        app.reqData(requestParam);
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
        }else if (ServiceNetContant.ServiceResponseAction.FOOD_ORDER_CONFIRM_RESPONSE.equals(action)){
            String body=getResponseBody(responseData);
            Log.i(TAG,"body=="+body);
            FoodTradeNoCate foodInfoCate=(FoodTradeNoCate)getJsonToObject(body, FoodTradeNoCate.class);
            if (foodInfoCate.getCode() == 0) {
                if (getIControlerCallBack() != null) {//解析数据成功，通知UI界面
                    List<FoodTradeNoData> data = foodInfoCate.getData();
                    if (data != null && data.size() > 0) {
                        Bundle bundle = new Bundle();
                        bundle.putString(CONTROLER_ACTION, ServiceNetContant.ServiceResponseAction.FOOD_ORDER_CONFIRM_RESPONSE);
                        bundle.putParcelableArrayList("orderPayment", (ArrayList) data);
                        getIControlerCallBack().result(bundle);
                    }
                }
            }else{
//                doBassesResultMag(foodInfoCate.getMsg());
//                Log.i(TAG,"data is null");
            }
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
