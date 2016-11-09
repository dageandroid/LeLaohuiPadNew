package dq.lelaohui.com.lelaohuipad.controler;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import dq.lelaohui.com.lelaohuipad.LeLaohuiApp;
import dq.lelaohui.com.lelaohuipad.base.LaoHuiBaseControler;
import dq.lelaohui.com.lelaohuipad.bean.ServerCate;
import dq.lelaohui.com.lelaohuipad.dao.ProCateServiceDaoOperator;
import dq.lelaohui.com.lelaohuipad.dao.ProMenumServiceDaoOperator;
import dq.lelaohui.com.lelaohuipad.util.ServerRequestParam;
import dq.lelaohui.com.nettylibrary.socket.RequestParam;
import dq.lelaohui.com.nettylibrary.util.NetContant;
import dq.lelaohui.com.nettylibrary.util.Protocol_KEY;
import dq.lelaohui.com.nettylibrary.util.ServiceNetContant;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.BaseBean;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.ProCateService;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.dao.ProCateMenuServiceDao;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.dao.ProCateServiceDao;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.manager.BaseDaoOperator;

import static android.content.ContentValues.TAG;

/**
 * Created by ThinkPad on 2016/10/20.
 */

public class ServerControler extends LaoHuiBaseControler {
    public static final String SUCCESS_CODE="200";
    private static ServerControler serverControler=null;
    private ServerRequestParam requestParam;
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
                        setDataList(data);
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
    public  void setDataList(  List<ProCateService> data){
        if(data!=null){
            for (int i=0;i<data.size();i++){
                data.get(i).setOrgId(getOrgId());
                data.get(i).setOrgTypeId(getOrgType());
            }
            insertData(data);
        }
    }

    /**
     * 获取服务一级类别
     */
    public void doQueryServerCategory(){
        LeLaohuiApp app= (LeLaohuiApp) getContext();
        if(app==null){
            throw  new RuntimeException(" app is null exception");
        }
        RequestParam requestParam1=requestParam.doQueryServerCategory();

        app.reqData(requestParam1);
    }
    /**
     * 获取二级服务分类
     */
    public void doQueryServerCategory(long  cateIdL,int isPackInt){
        LeLaohuiApp app= (LeLaohuiApp) getContext();
        if(app==null){
            throw  new RuntimeException(" app is null exception");
        }
        RequestParam requestParam1=requestParam.doQueryServerCategory(cateIdL,isPackInt);
        app.reqData(requestParam1);
    }
    public void doQueryServerCategory(long  cateIdL,int isPackInt,int cateLevelInt){
        LeLaohuiApp app= (LeLaohuiApp) getContext();
        if(app==null){
            throw  new RuntimeException(" app is null exception");
        }
        RequestParam requestParam1=requestParam.doQueryServerCategory(cateIdL,isPackInt,cateLevelInt);
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
    private static String  GET_SERVER_CATE_TWO_VERSION="getSerProCateJsonList";
    private ProMenumServiceDaoOperator proCateMenuServiceDao;

    @Override
    public BaseDaoOperator getBaseDaoOperator(String version) {
        if(TextUtils.isEmpty(version)){
            dao=ProCateServiceDaoOperator.getInstance();
            dao.setmContext(getContext());
            return dao;
        }
        if(GET_SERVER_CATE_TWO_VERSION.equals(version)){
            proCateMenuServiceDao= ProMenumServiceDaoOperator.getInstance();
            proCateMenuServiceDao.setmContext(getContext());
            return proCateMenuServiceDao;
        }

        return null;
    }
    private Cursor getQueryFirstCursor(long orgId ,int orgTypeId){
        Cursor cursor=null;
        if(getBaseDaoOperator()!=null){
            ProCateServiceDaoOperator  sdao= (ProCateServiceDaoOperator) getBaseDaoOperator();
            cursor= sdao.queryFirst(orgId,orgTypeId);
        }else{
            throw new RuntimeException("获取数据库对象为null");
        }
        return cursor;
    }
    public Cursor getQueryFirstCursor(){

        return getQueryFirstCursor(getOrgId(),getOrgType());
    }
    private Cursor getQueryAllCursor(ProCateService proCateService){
        {
            Cursor cursor=null;
            if(getBaseDaoOperator()!=null){
                ProCateServiceDaoOperator  sdao= (ProCateServiceDaoOperator) getBaseDaoOperator();
                cursor= sdao.query(proCateService);
            }else{
                throw new RuntimeException("获取数据库对象为null");
            }
            return cursor;
        }
    }
    public  Cursor getQueryAll(ProCateService proCateService){
        return getQueryAllCursor(proCateService);
    }
    @Override
    public BaseDaoOperator getBaseDaoOperator() {

        return getBaseDaoOperator(null);
    }
}
