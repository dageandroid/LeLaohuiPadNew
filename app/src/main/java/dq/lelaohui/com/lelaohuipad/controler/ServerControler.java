package dq.lelaohui.com.lelaohuipad.controler;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.reflect.TypeToken;

import java.util.List;

import dq.lelaohui.com.lelaohuipad.LeLaohuiApp;
import dq.lelaohui.com.lelaohuipad.base.LaoHuiBaseControler;
import dq.lelaohui.com.lelaohuipad.bean.ServerCate;
import dq.lelaohui.com.lelaohuipad.dao.ProCateServiceDaoOperator;
import dq.lelaohui.com.lelaohuipad.util.ServerRequestParam;
import dq.lelaohui.com.nettylibrary.socket.RequestParam;
import dq.lelaohui.com.nettylibrary.util.NetContant;
import dq.lelaohui.com.nettylibrary.util.Protocol_KEY;
import dq.lelaohui.com.nettylibrary.util.ServiceNetContant;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.BaseBean;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.ProCateService;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.manager.BaseDaoOperator;

import static android.content.ContentValues.TAG;

/**
 * Created by ThinkPad on 2016/10/20.
 */

public class ServerControler extends LaoHuiBaseControler {
    public static final String SUCCESS_CODE="200";
    private static ServerControler serverControler=null;
    private ServerRequestParam requestParam;
    /**
     * 获取服务数据请求的cateGory
     */
    private static final String CATEGORY = "lelaohui_server";

    /**
     * 顶级菜单
     */
    public static final int CATE_LEVEl_PARENT=0;
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
    public static final String USEDATA ="query.service";
    private ServerControler(){
        requestParam=new ServerRequestParam();
    }
    public static ServerControler getControler(){
        if(serverControler==null){
            synchronized (ServerControler.class){
                if(serverControler==null){
                    serverControler=new ServerControler();
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
        if(ServiceNetContant.ServiceResponseAction.GETSERPROCATEJSONLIST_RESPONSE.equals(action)){
            String body=getResponseBody(responseData);
            ServerCate serverCate= (ServerCate) getJsonToObject(body,ServerCate.class);
            if(serverCate.getCode().equals(SUCCESS_CODE)){
                    if(getIControlerCallBack()!=null){//解析数据成功，通知UI界面
                        List<ProCateService> data= (List<ProCateService>) getJsonToObject(serverCate.getObj(),new TypeToken< List<ProCateService> >(){}.getType());
                        insertData(data);
                        Bundle bundle=new Bundle();
                        getIControlerCallBack().result(bundle);
                        List<ProCateService> queryList= (List<ProCateService>) queryData(new ProCateService());
                        if(queryList!=null){
                            log("doBusses query1:"+queryList.toString());
                        }else{
                            log("doBusses query1: queryList is null");
                        }
                    }
            }else{

            }
        }
        log("doBusses: "+responseData);
    }
    /**
     * 获取服务一级类别
     */
    public void doQueryServerCategory(){
        LeLaohuiApp app= (LeLaohuiApp) getContext();
        if(app==null){
            throw  new RuntimeException(" app is null exception");
        }
        RequestParam requestParam1=requestParam.getRequestParam();

        app.reqData(requestParam1);
    }





    /**
     * @param interfaceNameStr  接口名
     * @param cateKeyStr  发送数据与后台对接的Key
     * @param parmBundle 发送的相关参数信息
     */
    public void  doQueryServerCategory(String interfaceNameStr,String cateKeyStr, Bundle parmBundle){
        {
            LeLaohuiApp app= (LeLaohuiApp) getContext();
            if(app==null){
                throw  new RuntimeException(" app is null exception");
            }
            RequestParam requestParam1=requestParam.doQueryServerCategory(interfaceNameStr,cateKeyStr,parmBundle);
            app.reqData(requestParam1);
        }
    }



    private ProCateServiceDaoOperator dao;
    @Override
    public BaseDaoOperator getBaseDaoOperator() {
        if(dao==null){
            dao=ProCateServiceDaoOperator.getInstance();
            dao.setmContext(getContext());
        }
        return dao;
    }
}
