package dq.lelaohui.com.lelaohuipad.base;


import android.os.Bundle;
import android.text.TextUtils;

import java.util.List;

import dq.lelaohui.com.lelaohuipad.base.LaoHuiBaseControler;
import dq.lelaohui.com.lelaohuipad.bean.BaseOrderCate;
import dq.lelaohui.com.lelaohuipad.bean.SerOrderInfoData;
import dq.lelaohui.com.lelaohuipad.bean.UserAddressBean;
import dq.lelaohui.com.lelaohuipad.bean.UserAddressData;
import dq.lelaohui.com.nettylibrary.util.ServiceNetContant;

/**
 * Created by ZTF on 2016/12/9.
 */

public abstract class BaseOrderInfoControler extends LaoHuiBaseControler {
    /**
     * 请求地址
     */
    public abstract void doUserAddressInfo();

    /**提交购物车里的数据
     * @param serverOrderList
     */
    public abstract void uploadShoppingData(BaseOrderCate serverOrderList);
    public abstract void doServerOrderPayment(String outTradeNo,String payAmt,String payType);
    public abstract void doParserSubmit(Bundle responseData);
    public abstract void doPayInfoResult(Bundle responseData);
    protected abstract boolean isOrderSuccInfoResponse(String action);

    protected abstract boolean isUploadOrderPaymentResponse(String action) ;

    protected abstract boolean isSaveOrderInfo(String action) ;

    protected abstract boolean isAddressResponse(String action) ;
    protected abstract void UploadOrderPayMent(Bundle responseData);
    /**
     * 查询订单返回接口
     * @param orderInfo
     */
    public abstract void getMyServerOrderInfo(Bundle orderInfo);
    protected void parserAddress(Bundle responseData) {
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
        }
    }


}
