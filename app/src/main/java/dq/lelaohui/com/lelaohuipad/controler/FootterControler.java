package dq.lelaohui.com.lelaohuipad.controler;

import android.os.Bundle;

import java.util.ArrayList;

import dq.lelaohui.com.lelaohuipad.LeLaohuiApp;
import dq.lelaohui.com.lelaohuipad.base.LaoHuiBaseControler;
import dq.lelaohui.com.nettylibrary.socket.RequestParam;
import dq.lelaohui.com.nettylibrary.util.NetContant;
import dq.lelaohui.com.nettylibrary.util.Protocol_KEY;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.manager.BaseDaoOperator;

/**
 * Created by ThinkPad on 2016/10/18.
 */

public class FootterControler extends LaoHuiBaseControler {
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
     * @param mealtime   早，中，晚
     * @param userIdStr  用户Id
     */
    public void doQueryFoodInfo(int isScope,int mealtime,String userIdStr){
        LeLaohuiApp app= (LeLaohuiApp) getContext();
        if(app==null){
            throw  new RuntimeException(" app is null exception");
        }
        RequestParam requestParam = getRequestParam(isScope, mealtime, userIdStr,NetContant.ServiceAction.QUERY_FOOD_INFO);
        app.reqData(requestParam);
    }

    /**
     * 获取餐品信息发送数据
     * @param isScope  今天，明天，后天
     * @param mealtime  早，中，晚
     * @param userIdStr  用户Id
     * @param interfaceName  发送请求的接口名
     * @return
     */
    private RequestParam getRequestParam(int isScope, int mealtime, String userIdStr,String interfaceName) {
        RequestParam requestParam=new RequestParam();
        requestParam.addHeader(Protocol_KEY.ACTION,interfaceName );
        requestParam.addBody(Protocol_KEY.ISSCOPE,isScope);
        requestParam.addBody(Protocol_KEY.USERID,userIdStr);
        requestParam.addBody(Protocol_KEY.CENTERID,getCenterId());
        requestParam.addBody(Protocol_KEY.MEALTIME,mealtime);
        requestParam.addBody(Protocol_KEY.ORG_ID,String.valueOf(getOrgId()));
        requestParam.addBody(Protocol_KEY.ORG_TYPE,String.valueOf(getOrgType()));
        return requestParam;
    }

    /**
     * 提交购物车相关信息接口
     * @param isScope  今天，明天，后天
     * @param mealtime  早，中，晚
     * @param userIdStr  用户Id
     * @param cofirmOrderData  购物车相关商品信息
     */
    public void cofirmFoodOrder(int isScope, int mealtime, String userIdStr, ArrayList<Bundle> cofirmOrderData){
        LeLaohuiApp app= (LeLaohuiApp) getContext();
        if(app==null){
            throw  new RuntimeException(" app is null exception");
        }
        RequestParam requestParam = getRequestParam(isScope, mealtime, userIdStr,NetContant.ServiceAction.CONFIRM_FOOD_ORDER);
        requestParam.addBody(Protocol_KEY.CHANNEL,"1");
        requestParam.addBody(Protocol_KEY.IS_DISTR,"1");
        app.reqData(requestParam);
    }

    @Override
    public BaseDaoOperator getBaseDaoOperator(String version) {
        return null;
    }

    @Override
    public BaseDaoOperator getBaseDaoOperator() {
        return null;
    }
}
