package dq.lelaohui.com.lelaohuipad.controler;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dq.lelaohui.com.lelaohuipad.LeLaohuiApp;
import dq.lelaohui.com.lelaohuipad.base.LaoHuiBaseControler;
import dq.lelaohui.com.lelaohuipad.bean.FoodInfoCate;
import dq.lelaohui.com.lelaohuipad.dao.ProFoodInfoDaoOperator;
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
    private String TAG = getClass().getSimpleName();
    /**
     * 获取订餐数据请求的cateGory
     */
    private static final String CATEGORY_FOOD = "lelaohui";
    /**
     *
     */
    public static final String USEDATA_FOOD ="query.food.menu";

    private  static FootterControler controler=null;
    /**
     *今天
     */
    public static final int ISSCOPE_TODAY=0;
    /**
     * 明天
     */
    public static final int ISSCOPE_TOMORROW=1;
    /**
     * 后天
     */
    public static final int ISSCOPE_AFTER_TOMORROW=2;

    /**
     * 早餐
     */
    public static final int MEALTIME_MORNING=2;
    /**
     * 午餐
     */
    public static final int MEALTIME_AFTERNOON=2;
    /**
     *
     */
    public static final int MEALTIME_NIGHT=2;
    public static final int MEALTIME_MID_NIGHT=2;


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
            Log.i(TAG,"food.info==="+responseData);
            FoodInfoCate foodInfoCate=(FoodInfoCate)getJsonToObject(body, FoodInfoCate.class);
            if (foodInfoCate.getCode() == 0) {
                if (getIControlerCallBack() != null) {//解析数据成功，通知UI界面
                    List<FoodInfoData> data = foodInfoCate.getData();
                    instertData(data);
                    if (data != null && data.size() > 0) {
                        Bundle bundle = new Bundle();
                        bundle.putString("action", ServiceNetContant.ServiceResponseAction.QUERY_FOOD_INFO_RESPONSE);
                        bundle.putParcelableArrayList("foodInfo", (ArrayList<? extends Parcelable>) data);
                        getIControlerCallBack().result(bundle);
                    }

                }
            }
            }else{

        }
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
//    /**
//     *获取餐饮类别
//     */
//    public void doQueryFoodCategory(){
//        LeLaohuiApp app= (LeLaohuiApp) getContext();
//        if(app==null){
//            throw  new RuntimeException(" app is null exception");
//        }
//        RequestParam requestParam=new RequestParam();
//        requestParam.addHeader(NetContant.Protocol_KEY.ACTION, NetContant.ServiceAction.QUERY_FOOD_INFO);
//        requestParam.addBody(NetContant.Protocol_KEY.ORG_ID,sysVar.getOrgId());
//        requestParam.addBody(NetContant.Protocol_KEY.ORG_TYPE,sysVar.getOrgType());
//        app.reqData(requestParam);
//    }

    /**
     * 获取餐品信息相关接口
     * @param isScope   今天，明天，后天  选餐时间
     * @param userIdStr  用户Id
     */
    public void doQueryFoodInfo(String isScope,String userIdStr){
        LeLaohuiApp app= (LeLaohuiApp) getContext();
        if(app==null){
            throw  new RuntimeException(" app is null exception");
        }
        RequestParam requestParam = getRequestParam(isScope, userIdStr,NetContant.ServiceAction.QUERY_FOOD_INFO);

        app.reqData(requestParam);
    }

    /**
     * 获取餐品信息发送数据
     * @param isScope  今天，明天，后天
     * @param userIdStr  用户Id
     * @param interfaceName  发送请求的接口名
     * @return
     */
    private RequestParam getRequestParam(String isScope, String userIdStr,String interfaceName) {
        RequestParam requestParam=new RequestParam();
        requestParam.addHeader(Protocol_KEY.ACTION,interfaceName );
        requestParam.addBody(Protocol_KEY.ISSCOPE,Integer.valueOf(isScope));
        requestParam.addBody(Protocol_KEY.USERID,userIdStr);
        requestParam.addBody(Protocol_KEY.CENTERID,getCenterId());
        requestParam.addBody(Protocol_KEY.ORG_ID,String.valueOf(getOrgId()));
        requestParam.addBody(Protocol_KEY.ORG_TYPE,String.valueOf(getOrgType()));
        return requestParam;
    }

    /**
     * 提交购物车相关信息接口
     */
    public void cofirmFoodOrder(String  isScope, int mealtime, String userIdStr,String buyUserId, ArrayList<Bundle> cofirmOrderData){
        LeLaohuiApp app= (LeLaohuiApp) getContext();
        if(app==null){
            throw  new RuntimeException(" app is null exception");
        }
        RequestParam requestParam = getRequestParam(isScope, userIdStr,NetContant.ServiceAction.CONFIRM_FOOD_ORDER);
        requestParam.addBody(Protocol_KEY.CHANNEL,"1");
        requestParam.addBody(Protocol_KEY.IS_DISTR,"1");
        requestParam.addBody(Protocol_KEY.MEALTIME,mealtime);
        requestParam.addBody(Protocol_KEY.BUY_USER_ID,buyUserId);
        requestParam.addBody("cofirmData",cofirmOrderData);
        app.reqData(requestParam);
    }
    ProFoodInfoDaoOperator dao;
    @Override
    public BaseDaoOperator getBaseDaoOperator(String version) {
        if(TextUtils.isEmpty(version)){
            dao= ProFoodInfoDaoOperator.getInstance();
            dao.setmContext(getContext());
            return dao;
        }
        return null;
    }
    private Cursor queryFoodInfoCursor(String mealTime,String cateName,String mealType){
        Cursor cursor=null;
        if(getBaseDaoOperator()!=null){
            ProFoodInfoDaoOperator  sdao= (ProFoodInfoDaoOperator) getBaseDaoOperator();
            cursor= sdao.queryFoodInfo(mealTime,cateName,mealType);
        }else{
            throw new RuntimeException("获取数据库对象为null");
        }
        return cursor;
    }

    private Cursor queryFoodTypeCursor(String mealTime,String mealType){
        Cursor cursor=null;
        if(getBaseDaoOperator()!=null){
            ProFoodInfoDaoOperator  sdao= (ProFoodInfoDaoOperator) getBaseDaoOperator();
            cursor= sdao.queryFoodType(mealTime,mealType);
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
     * 获取餐品类型
     * @param mealTime
     * @param mealType
     * @return
     */
    public Cursor  getFoodTypeCursor(String mealTime,String mealType){
        return queryFoodTypeCursor(mealTime,mealType);
    }
    /**
     * 查询获取餐品信息
     * @param mealTime
     * @param cateName
     * @param mealType
     * @return
     */
    public Cursor getFoodInfoCursor(String mealTime,String cateName,String mealType){
        return queryFoodInfoCursor(mealTime,cateName,mealType);
    }
    @Override
    public BaseDaoOperator getBaseDaoOperator() {
        return getBaseDaoOperator(null);
    }
}
