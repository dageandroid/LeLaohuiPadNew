package dq.lelaohui.com.lelaohuipad.controler;

import android.content.Context;
import android.os.Bundle;

import dq.lelaohui.com.lelaohuipad.LeLaohuiApp;
import dq.lelaohui.com.lelaohuipad.base.LaoHuiBaseControler;
import dq.lelaohui.com.lelaohuipad.util.SysVar;
import dq.lelaohui.com.nettylibrary.socket.RequestParam;
import dq.lelaohui.com.nettylibrary.util.NetContant;
import dq.lelaohui.com.nettylibrary.util.Protocol_KEY;

/**
 * Created by ThinkPad on 2016/10/18.
 */

public class FootterControler extends LaoHuiBaseControler {
    private  static FootterControler controler=null;
    private SysVar sysVar=SysVar.getInstance();
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
//        requestParam.addBody(NetContant.Protocol_KEY.ORGID,sysVar.getOrgId());
//        requestParam.addBody(NetContant.Protocol_KEY.ORGTYPE,sysVar.getOrgType());
//        app.reqData(requestParam);
//    }
    public void doQueryFoodInfo(int isScope,int mealtime){
        LeLaohuiApp app= (LeLaohuiApp) getContext();
        if(app==null){
            throw  new RuntimeException(" app is null exception");
        }
        RequestParam requestParam=new RequestParam();
        requestParam.addHeader(Protocol_KEY.ACTION, NetContant.ServiceAction.QUERY_FOOD_INFO);
        requestParam.addBody(Protocol_KEY.ISSCOPE,isScope);
        requestParam.addBody(Protocol_KEY.CENTERID,sysVar.getCenterId());
        requestParam.addBody(Protocol_KEY.MEALTIME,mealtime);
        app.reqData(requestParam);
    }
}
