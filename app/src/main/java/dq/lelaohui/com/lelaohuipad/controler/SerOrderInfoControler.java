package dq.lelaohui.com.lelaohuipad.controler;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;

import java.util.List;

import dq.lelaohui.com.lelaohuipad.LeLaohuiApp;
import dq.lelaohui.com.lelaohuipad.base.LaoHuiBaseControler;
import dq.lelaohui.com.lelaohuipad.bean.ServerCate;
import dq.lelaohui.com.lelaohuipad.dao.ProCateServiceDaoOperator;
import dq.lelaohui.com.lelaohuipad.dao.ProMenumServiceDaoOperator;
import dq.lelaohui.com.lelaohuipad.util.ServerRequestParam;
import dq.lelaohui.com.nettylibrary.socket.RequestParam;
import dq.lelaohui.com.nettylibrary.util.ServiceNetContant;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.ProCateService;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.manager.BaseDaoOperator;

/**
 * Created by ZTF on 2016/11/13.
 */

public class SerOrderInfoControler  extends LaoHuiBaseControler {
    public static final String SUCCESS_CODE="200";
    private static SerOrderInfoControler serverControler=null;
    private ServerRequestParam requestParam;
    private SerOrderInfoControler(){
        requestParam=new ServerRequestParam();
    }
    public static SerOrderInfoControler getControler(){
        if(serverControler==null){
            synchronized (SerOrderInfoControler.class){
                if(serverControler==null){
                    serverControler=new SerOrderInfoControler();
                }
            }
        }
        return serverControler;
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
        if(ServiceNetContant.ServiceResponseAction.QUERY_USER_ADDRESS_RESPONSE.equals(action)){
            String body=getResponseBody(responseData);
//            ServerCate serverCate= (ServerCate) getJsonToObject(body,ServerCate.class);
//            if(serverCate.getCode().equals(SUCCESS_CODE)){
//                if(getIControlerCallBack()!=null){//解析数据成功，通知UI界面
//                    List<ProCateService> data= (List<ProCateService>) getJsonToObject(serverCate.getObj(),new TypeToken< List<ProCateService> >(){}.getType());
//                    Bundle bundle=new Bundle();
//                    getIControlerCallBack().result(bundle);
//                    List<ProCateService> queryList= (List<ProCateService>) queryData(new ProCateService());
//                    if(queryList!=null){
//                        log("doBusses query1:"+queryList.toString());
//                    }else{
//                        log("doBusses query1: queryList is null");
//                    }
//                }
//            }else{
//
//            }
        }
        log("doBusses: "+responseData);
    }
    /**
     * 获取用户地址
     */
    public void doUserAddressInfo(){
        LeLaohuiApp app= (LeLaohuiApp) getContext();
        if(app==null){
            throw  new RuntimeException(" app is null exception");
        }
        RequestParam requestParam1=requestParam.doUserAddressInfo();

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
