package dq.lelaohui.com.lelaohuipad.controler;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dq.lelaohui.com.lelaohuipad.LeLaohuiApp;
import dq.lelaohui.com.lelaohuipad.base.LaoHuiBaseControler;
import dq.lelaohui.com.lelaohuipad.bean.FoodInfoCate;
import dq.lelaohui.com.lelaohuipad.bean.FoodOrderCate;
import dq.lelaohui.com.lelaohuipad.bean.FoodOrederData;
import dq.lelaohui.com.lelaohuipad.bean.FoodTradeNoCate;
import dq.lelaohui.com.lelaohuipad.bean.FoodTradeNoData;
import dq.lelaohui.com.lelaohuipad.bean.PayMentInfoCate;
import dq.lelaohui.com.lelaohuipad.dao.ProFoodInfoDaoOperator;
import dq.lelaohui.com.lelaohuipad.fragement.shop.dataprovider.FootDataManager;
import dq.lelaohui.com.lelaohuipad.util.FootRequestUtil;
import dq.lelaohui.com.nettylibrary.socket.RequestParam;
import dq.lelaohui.com.nettylibrary.util.NetContant;
import dq.lelaohui.com.nettylibrary.util.Protocol_KEY;
import dq.lelaohui.com.nettylibrary.util.ServiceNetContant;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.FoodInfoData;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.manager.BaseDaoOperator;

/**
 * Created by ThinkPad on 2016/10/18.
 */

public class FootterControler extends LaoHuiBaseControler {
    public FootDataManager getFootDataManager() {
        return footDataManager;
    }

    private FootDataManager footDataManager;
    private String TAG = getClass().getSimpleName();

    private  static FootterControler controler=null;

    private FootterControler(){

    }
    public static FootterControler getControler(){
        if(controler==null){
            synchronized (FootterControler.class){
                if(controler==null){
                    controler=new FootterControler();
                }
            }
        }
        return  controler;
    }

    public void init(){
        getFoodInfo("0");
    }
    @Override
    public void doBusses(Bundle responseData) {
        if(responseData==null){
            log(getClass().getName()+" doBusses 数据异常");
            return ;
        }
        String action=getResponseAction(responseData);
        if(TextUtils.isEmpty(action)){
            log("解析数据异常，异常原因：action is null");
        }
        if(ServiceNetContant.ServiceResponseAction.QUERY_FOOD_INFO_RESPONSE.equals(action)){
            String body=getResponseBody(responseData);
            Log.i(TAG,"body=="+body);
            FoodInfoCate foodInfoCate=(FoodInfoCate)getJsonToObject(body, FoodInfoCate.class);
            if (foodInfoCate.getCode() == 0) {
                if (getIControlerCallBack() != null) {//解析数据成功，通知UI界面
                    List<FoodInfoData> data = foodInfoCate.getData();
                    if (data != null && data.size() > 0) {
//                        instertData(data);
                        Bundle bundle = new Bundle();
                        bundle.putString(CONTROLER_ACTION, ServiceNetContant.ServiceResponseAction.QUERY_FOOD_INFO_RESPONSE);
                        bundle.putSerializable("foodInfo", (ArrayList) data);
                        getIControlerCallBack().result(bundle);
                    }
                }
            }else{
                Log.i(TAG,"data is null");
            }
            }else if (ServiceNetContant.ServiceResponseAction.CONFIRM_FOOD_ORDER_RESPONSE.equals(action)){
            String body=getResponseBody(responseData);
            Log.i(TAG,"body=="+body);
            FoodOrderCate foodInfoCate=(FoodOrderCate)getJsonToObject(body, FoodOrderCate.class);
            if (foodInfoCate.getCode() == 0) {
                if (getIControlerCallBack() != null) {//解析数据成功，通知UI界面
                    List<FoodOrederData> data = foodInfoCate.getData();
                    if (data != null && data.size() > 0) {
                        Bundle bundle = new Bundle();
                        bundle.putString(CONTROLER_ACTION, ServiceNetContant.ServiceResponseAction.CONFIRM_FOOD_ORDER_RESPONSE);
                        bundle.putSerializable("foodOrderInfo", (ArrayList) data);
                        getIControlerCallBack().result(bundle);
                    }
                }
            }else{
                doBassesResultMag(foodInfoCate.getMsg());
                Log.i(TAG,"data is null");
            }
        }else if (ServiceNetContant.ServiceResponseAction.FOOD_ORDER_PAYMENT_RESPONSE.equals(action)){
            String body=getResponseBody(responseData);
            Log.i(TAG,"body=="+body);
            PayMentInfoCate foodInfoCate=(PayMentInfoCate)getJsonToObject(body, PayMentInfoCate.class);
            if (foodInfoCate.getCode() == 0) {
                if (getIControlerCallBack() != null) {//解析数据成功，通知UI界面
//                        Bundle bundle = new Bundle();
//                        bundle.putString(CONTROLER_ACTION, ServiceNetContant.ServiceResponseAction.FOOD_ORDER_CONFIRM_RESPONSE);
//                        bundle.putString("payMentMsg", foodInfoCate.getMsg());
//                        getIControlerCallBack().result(bundle);
                    doBassesResultMag(foodInfoCate.getMsg());
                }
            }else{
                Log.i(TAG,"data is null");
                doBassesResultMag(foodInfoCate.getMsg());
            }
        }

        else {
            Log.i(TAG,"data is null");
        }
    }
    public static  final  String REQ_MSG="reqMsg";
    public static  final  String REQ_MSG_ERROR="error_promot_toast";
     public   void   doBassesResultMag(String reqMsg){
            Bundle bundle = new Bundle();
            bundle.putString(CONTROLER_ACTION,REQ_MSG_ERROR);
            bundle.putString(REQ_MSG,reqMsg);
            getIControlerCallBack().result(bundle);
        }


    /**
     * 获取餐品信息相关接口
     * @param isScope   今天，明天，后天  选餐时间
     * @param userIdStr  用户Id
     */
    public void doReqFoodInfo(String isScope, String userIdStr){
        LeLaohuiApp app= (LeLaohuiApp) getContext();
        if(app==null){
            throw  new RuntimeException(" app is null exception");
        }
        FootRequestUtil requestUtil=new FootRequestUtil(String.valueOf(getCenterId()),String.valueOf(getOrgId()),String.valueOf(getOrgType()));
        RequestParam requestParam = requestUtil.getRequestParam(isScope, userIdStr,NetContant.ServiceAction.QUERY_FOOD_INFO);

        app.reqData(requestParam);
    }
    /**
     * 获取餐品信息相关接口
     * @param isScope   今天，明天，后天  选餐时间
     */
    public void doReqFoodInfo(String isScope){
        doReqFoodInfo( isScope,getSysVar().getUserId());
    }


    /**
     * 提交餐品信息
     */
public void confirmFoodOrder(int isDistr,int payType,int totalMoney,String addressStr,String phone,
                              String  isScope, int mealtime, String userIdStr,String buyUserId, ArrayList<Bundle> cofirmOrderData){
    LeLaohuiApp app= (LeLaohuiApp) getContext();
    if(app==null){
        throw  new RuntimeException(" app is null exception");
    }
    FootRequestUtil requestUtil=new FootRequestUtil(String.valueOf(getCenterId()),String.valueOf(getOrgId()),String.valueOf(getOrgType()));
    RequestParam requestParam =  requestUtil.getRequestParam(isScope, userIdStr,NetContant.ServiceAction.FOOD_ORDER_CONFIRM);
    requestParam.addBody(Protocol_KEY.CHANNEL,"1");
    requestParam.addBody(Protocol_KEY.IS_DISTR,isDistr);
    requestParam.addBody(Protocol_KEY.MEALTIME,mealtime);
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

    /**
     * 提交购物车相关信息接口
     */
    public RequestParam cofirmFoodOrder(String  isScope, int mealtime, String userIdStr, String buyUserId, ArrayList<Bundle> cofirmOrderData){
        LeLaohuiApp app= (LeLaohuiApp) getContext();
        if(app==null){
            throw  new RuntimeException(" app is null exception");
        }
        FootRequestUtil requestUtil=new FootRequestUtil(String.valueOf(getCenterId()),String.valueOf(getOrgId()),String.valueOf(getOrgType()));
        RequestParam requestParam =requestUtil.getRequestParam(isScope, userIdStr,NetContant.ServiceAction.CONFIRM_FOOD_ORDER);
        requestParam.addBody(Protocol_KEY.CHANNEL,1);
        requestParam.addBody(Protocol_KEY.MEALTIME,mealtime);
        requestParam.addBody(Protocol_KEY.BUY_USER_ID,buyUserId);
        requestParam.addBody(Protocol_KEY.CONFIRM_DATA,cofirmOrderData);
        app.reqData(requestParam);
        return requestParam;
    }

    public void getFoodInfo(String isScroe){
        if (footDataManager != null) {
            try{
                footDataManager.requestFoodInfo(isScroe);
                Log.i(TAG, "getFoodInfo: "+isScroe);
//                footDataManager.requestFoodInfo("1");
//                footDataManager.requestFoodInfo("2");
            }catch (Exception e){

            }

        }
    }
    @Override
    public void destroy() {
        super.destroy();
        if (footDataManager != null) {
            footDataManager.onDestory();
        }
    }


    ProFoodInfoDaoOperator dao;
    @Override
    public BaseDaoOperator getBaseDaoOperator() {
        dao= ProFoodInfoDaoOperator.getInstance();
        dao.setmContext(getContext());
        return dao;
    }
    private Cursor queryFoodInfoCursor(String mealTime,String cateId,String isscrole){
        Cursor cursor=null;
        if(getBaseDaoOperator()!=null){
            ProFoodInfoDaoOperator  sdao= (ProFoodInfoDaoOperator) getBaseDaoOperator();
            cursor= sdao.queryFoodInfo(mealTime,cateId,isscrole);
        }else{
            throw new RuntimeException("获取数据库对象为null");
        }
        return cursor;
    }
    /**
     * 获取餐品类型
     * @param mealType
     * @return
     */
  public Cursor  getFoodTypeCursor(String mealType){
        return queryFoodTypeCursor(mealType);
    }
    /**
     * 获取餐品类型
     * @param mealType
     * @return
     */
    public Cursor  getFoodCursor(String mealTime,String mealType){
        return queryFoodTypeCursor(mealType);
    }
    private Cursor queryFoodTypeCursor(String mealType){
        Cursor cursor=null;
        if(getBaseDaoOperator()!=null){
            ProFoodInfoDaoOperator  sdao= (ProFoodInfoDaoOperator) getBaseDaoOperator();
            cursor= sdao.queryFoodType(mealType);
        }else{
            throw new RuntimeException("获取数据库对象为null");
        }
        return cursor;
    }
    /**
     * 插入数据库
     * @param foodInfoDataList
     */
    public void instertData(List<FoodInfoData> foodInfoDataList){
        if(foodInfoDataList!=null&&foodInfoDataList.size()>0){
            insertData(foodInfoDataList);
            Log.i(TAG,"insert food info data succ...");
        }else{
            Log.i(TAG,"insert food info data erro...");
        }
    }
    /**
     * 查询获取餐品信息
     * @param mealTime
     * @param cateId
     * @param isscorle
     * @return
     */
    public Cursor getFoodInfoCursor(String mealTime,String cateId,String isscorle){
        return queryFoodInfoCursor(mealTime,cateId,isscorle);
    }

    public void setFootDataManager(FootDataManager footDataManager) {
        this.footDataManager = footDataManager;
    }
}
