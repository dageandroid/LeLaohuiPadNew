package dq.lelaohui.com.lelaohuipad.util;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import dq.lelaohui.com.lelaohuipad.bean.SerOrderInfoData;
import dq.lelaohui.com.nettylibrary.socket.RequestParam;
import dq.lelaohui.com.nettylibrary.util.NetContant;
import dq.lelaohui.com.nettylibrary.util.Protocol_KEY;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.SerInitProPack;

/**
 * Created by ThinkPad on 2016/10/25.
 */

public class ServerRequestParam {

    /**
     * 顶级菜单
     */
    public static final int CATE_LEVEl_PARENT=0;
    /**
     * 上传购物车CATE_LEVEl
     */
    public static final String CATE_LEVEl_STR="1";
    /**
     * 二级菜单
     */
    @Deprecated
    public static final int CATE_LEVEl_CHILD=1;
    /**
     * 服务包
     */
    public static final int PACK_SERVER_TYPE=1;
    /**
     * 不是服务包
     */
    public static final int NO_PACK_SERVER_TYPE=0;
    /**
     * 显示空菜单
     */
    public static final int IS_EMPTY_SHOW=0;
    /**
     * 不显示空菜单
     */
    public static final int IS_EMPTY_NOT_SHOW=1;

    public static final int PACKSTATUS=6;
    /**
     * 获取服务数据请求的cateGory
     */
    private static final String CATEGORY = "lelaohui_server";
    private static final String CATEGORY_USER = "lelaohui";
    public static final String USEDATA ="query.service";
    private SysVar var=null;
    public ServerRequestParam(){
        var=SysVar.getInstance();
    }

    /**
     * 获取数据的用户相关信息
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
     * 获取服务一级类别
     * 获取二级服务类别
     */
    public RequestParam doQueryServerCategory(){
        RequestParam requestParam=getRequestParam();
        requestParam.addHeader(Protocol_KEY.ACTION, NetContant.ServiceAction.QUERY_SERVICE_CATEGORY);
        requestParam.addBody(Protocol_KEY.IS_SERVER_REQ,true);

        Bundle parmBundle=new Bundle();
        setOrgBundleParm(parmBundle);
        parmBundle.putInt(Protocol_KEY.IS_EMPTY_SHOW,IS_EMPTY_NOT_SHOW);
        parmBundle.putInt(Protocol_KEY.CATE_LEVEL,CATE_LEVEl_PARENT);
        parmBundle.putInt(Protocol_KEY.ISPACK,NO_PACK_SERVER_TYPE);
        parmBundle.putInt(Protocol_KEY.PACK_STATUS,PACKSTATUS);
        requestParam.addBody(Protocol_KEY.PRO_CATE_SERVICE,parmBundle);
        return requestParam;
    }

    /**
     * 获取二级服务类别方法
     * @param cateIdL
     * @param isPackInt
     * @param cateLevelInt
     * @return
     */
    public RequestParam doQueryServerCategory(long  cateIdL,int isPackInt,int cateLevelInt){
        RequestParam requestParam=getRequestParam();
        requestParam.addHeader(Protocol_KEY.ACTION, NetContant.ServiceAction.QUERY_SERVICE_CATEGORY);
        requestParam.addBody(Protocol_KEY.IS_SERVER_REQ,true);

        Bundle parmBundle=new Bundle();
        setOrgBundleParm(parmBundle);
        parmBundle.putLong(Protocol_KEY.PARENT_ID,cateIdL);
        parmBundle.putInt(Protocol_KEY.CATE_LEVEL,cateLevelInt);
        parmBundle.putInt(Protocol_KEY.ISPACK,isPackInt);
        parmBundle.putInt(Protocol_KEY.IS_EMPTY_SHOW,IS_EMPTY_NOT_SHOW);
        parmBundle.putInt(Protocol_KEY.PACK_STATUS,PACKSTATUS);
        requestParam.addBody(Protocol_KEY.PRO_CATE_SERVICE,parmBundle);
        return requestParam;
    }
    /**
     * 获取类别服务项内容
     * @param cateIdL  二级级类别cateId
     * @param isPackInt  二级类别isPack
     */
    public RequestParam doQueryServerCategory(long  cateIdL,int isPackInt){
        Bundle parmBundle=new Bundle();
        setOrgBundleParm(parmBundle);
        if (isPackInt==1){
            parmBundle.putLong(Protocol_KEY.SERVICE_CATE_ID,cateIdL);
        }else{
            parmBundle.putLong(Protocol_KEY.DETAIL_CATE_ID,cateIdL);
        }
        return doQueryServerCategory(NetContant.ServiceAction.QUERY_SERVICE_CATEGORYS,Protocol_KEY.SER_PRO_PACKAGE,parmBundle);
    }

    /**
     * @param interfaceNameStr  接口名
     * @param cateKeyStr  发送数据与后台对接的Key
     * @param parmBundle 发送的相关参数信息
     */
    public RequestParam  doQueryServerCategory(String interfaceNameStr,String cateKeyStr, Bundle parmBundle){
        {
            RequestParam requestParam=getRequestParam();
            requestParam.addHeader(Protocol_KEY.ACTION,interfaceNameStr);
            requestParam.addBody(Protocol_KEY.IS_SERVER_REQ,true);
            requestParam.addBody(cateKeyStr,parmBundle);
            return requestParam;
        }
    }

    /**
     * 封装请求下单请求服务参数
     * 具体参数，需要根据业务来修改此方法
     * @param cartBeanList
     * @return
     */
    public RequestParam doConfirmOrderInfo( List<SerInitProPack> cartBeanList){
        String dataJson= JsonUtil.getInstance().ObjectTojson(cartBeanList,true);
        Log.i("doConfirmOrderInfo", "doConfirmOrderInfo: "+dataJson.toString());
        RequestParam requestParam=getRequestParam();
        requestParam.addHeader(Protocol_KEY.ACTION,NetContant.ServiceAction.UPLOAD_SHOPPING_CAR_DATA);
        requestParam.addHeader(Protocol_KEY.USERDATA, "query.service.menu");
        requestParam.addBody(Protocol_KEY.IS_SERVER_REQ,true);
        requestParam.addBody(Protocol_KEY.USERID,var.getUserId());
        requestParam.addBody(Protocol_KEY.ORG_ID,var.getOrgId());
        requestParam.addBody(Protocol_KEY.ORG_TYPE_ID,var.getOrgType());
        requestParam.addBody(Protocol_KEY.CATE_LEVEL,CATE_LEVEl_STR);
        try {requestParam.addBody(Protocol_KEY.PACK_SER_ORDER_INFO_DETAIL_LIST, URLEncoder.encode(dataJson,"UTF8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return requestParam;
    }
    public RequestParam getRequestParam(){
        RequestParam requestParam=new RequestParam();
        requestParam.addHeader(Protocol_KEY.CATEGORY,CATEGORY);
        requestParam.addHeader(Protocol_KEY.USERDATA, USEDATA);
        return requestParam;
    }
    public RequestParam getRequestParamLLH(){
        RequestParam requestParam=new RequestParam();
        requestParam.addHeader(Protocol_KEY.CATEGORY,CATEGORY_USER);
        requestParam.addHeader(Protocol_KEY.USERDATA, USEDATA);
        return requestParam;
    }
    /**
     * 获取用户地址信息
     */
    public RequestParam   doUserAddressInfo(){
        RequestParam requestParam=getRequestParamLLH();
        requestParam.addHeader(Protocol_KEY.ACTION,NetContant.ServiceAction.QUERY_USER_ADDRESS);
        requestParam.addHeader(Protocol_KEY.USERDATA, "query.user.address");
        requestParam.addBody(Protocol_KEY.IS_SERVER_REQ,false);
        requestParam.addBody(Protocol_KEY.USERID,var.getUserId());
        requestParam.addBody(Protocol_KEY.CENTERID,var.getCenterId());
        return requestParam;
    }
    /***
     *
     * 服务支付接口 (需要测试)
     */
    public RequestParam   doServerOrderPayment(String outTradeNo,String payAmt,String payType){
        RequestParam requestParam=getRequestParam();
        requestParam.addHeader(Protocol_KEY.ACTION,NetContant.ServiceAction.UPLOAD_SERVER_ORDER_PAYMENY);
        requestParam.addHeader(Protocol_KEY.USERDATA, "serverOrderPayment");
        requestParam.addBody(Protocol_KEY.IS_SERVER_REQ,true);
        requestParam.addBody(Protocol_KEY.USERID,var.getUserId());
        requestParam.addBody(Protocol_KEY.CUSTOMER_ID,var.getUserId());
        requestParam.addBody(Protocol_KEY.REAL_NAME,var.getUserName());
        requestParam.addBody(Protocol_KEY.ORDER_NO,outTradeNo);
        requestParam.addBody(Protocol_KEY.ORG_ID,String.valueOf(var.getOrgId()));
        requestParam.addBody(Protocol_KEY.ORG_TYPE,String.valueOf(var.getOrgType()));
        requestParam.addBody(Protocol_KEY.ORG_NAME,var.getOrgName());
        requestParam.addBody(Protocol_KEY.PAY_AMT,payAmt);
        requestParam.addBody(Protocol_KEY.PAY_TYPE,payType);
        return requestParam;
    }
    /**
     * 获取订单接口
     */
    public RequestParam    getMyServerOrderInfo(String orderCode){
        Bundle parmBundle=new Bundle();
        parmBundle.putString(Protocol_KEY.ORDER_CODE,orderCode);
        RequestParam requestParam=getRequestParam();
        requestParam.addHeader(Protocol_KEY.ACTION,NetContant.ServiceAction.GET_SERVER_ORDER_SUCC_INFO);
        requestParam.addHeader(Protocol_KEY.USERDATA, "getSerOrderInfos");
        requestParam.addBody(Protocol_KEY.IS_SERVER_REQ,true);
        requestParam.addBody(Protocol_KEY.SER_ORDER_INFO,parmBundle);
        return  requestParam;
    }
    /**
     * 提交订单接口
     */
    public RequestParam uploadShoppingData(SerOrderInfoData serverOrderList){
        RequestParam requestParam=getRequestParam();
        requestParam.addHeader(Protocol_KEY.ACTION,NetContant.ServiceAction.UPLOAD_USER_ORDERINFO);
        requestParam.addHeader(Protocol_KEY.USERDATA, "upload.orderinfo");
        requestParam.addBody(Protocol_KEY.IS_SERVER_REQ,true);
        requestParam.addBody(Protocol_KEY.SER_ORDER_STORE_KEY,new Gson().toJson(serverOrderList));
        return requestParam;
    }

    /**
     * 生成随机流水号
     * @return
     */
    public static int randomValue() {
        java.util.Random random = new java.util.Random();
        int result = random.nextInt(100);
        return result + 1;
    }

    /**
     * 转成utf-8
     * @param dataJson
     * @return
     */
    public String chackUTF8(String dataJson){
        try {
            String  chackUT= URLEncoder.encode(dataJson,"UTF8");
            return chackUT;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return dataJson;
    }
}
