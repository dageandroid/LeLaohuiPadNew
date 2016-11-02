package dq.lelaohui.com.lelaohuipad.controler;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;

import java.util.List;

import dq.lelaohui.com.lelaohuipad.LeLaohuiApp;
import dq.lelaohui.com.lelaohuipad.base.LaoHuiBaseControler;
import dq.lelaohui.com.lelaohuipad.bean.ServerCate;
import dq.lelaohui.com.lelaohuipad.bean.ServerMenuCate;
import dq.lelaohui.com.lelaohuipad.dao.ProMenumServiceDaoOperator;
import dq.lelaohui.com.lelaohuipad.util.ServerRequestParam;
import dq.lelaohui.com.nettylibrary.socket.RequestParam;
import dq.lelaohui.com.nettylibrary.util.ServiceNetContant;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.ProCateMenuService;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.manager.BaseDaoOperator;

/**
 * Created by ZTF on 2016/11/1.
 */

public class ServerMenuControler extends LaoHuiBaseControler {
    public static final String SUCCESS_CODE="200";
    private static ServerMenuControler serverMenuControler=null;
    private ServerRequestParam requestParam;
    private ServerMenuControler(){
        requestParam=new ServerRequestParam();
    }
    public static ServerMenuControler getControler(){
        if(serverMenuControler==null){
            synchronized (ServerMenuControler.class){
                if(serverMenuControler==null){
                    serverMenuControler=new ServerMenuControler();
                }
            }
        }
        return serverMenuControler;
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
            System.out.println("responseData==="+responseData.toString());
            ServerMenuCate serverCate= (ServerMenuCate) getJsonToObject(body,ServerMenuCate.class);
            if(serverCate.getCode().equals(SUCCESS_CODE)){
                if(getIControlerCallBack()!=null){//解析数据成功，通知UI界面
                    List<ProCateMenuService> data= (List< ProCateMenuService>) serverCate.getData();                 if (data!=null){
                         insertData(data);
                      }
                    Bundle bundle=new Bundle();
                    getIControlerCallBack().result(bundle);
                    List<ProCateMenuService> queryList= (List<ProCateMenuService>) queryData(new ProCateMenuService());
                    if(queryList!=null){
                        log("doBusses query2:"+queryList.toString());
                    }else{
                        log("doBusses query2: queryList is null");
                    }
                }
            }else{

            }
        }
        log("doBusses: "+responseData);
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



    private ProMenumServiceDaoOperator proCateMenuServiceDao;

    @Override
    public BaseDaoOperator getBaseDaoOperator(String version) {
//        if(TextUtils.isEmpty(version)){
            proCateMenuServiceDao= ProMenumServiceDaoOperator.getInstance();
            proCateMenuServiceDao.setmContext(getContext());
            return proCateMenuServiceDao;
//        }
//        if(GET_SERVER_CATE_TWO_VERSION.equals(version)){
//            proCateMenuServiceDao= ProMenumServiceDaoOperator.getInstance();
//            proCateMenuServiceDao.setmContext(getContext());
//            return proCateMenuServiceDao;
//        }

//        return null;
    }
    private Cursor getQueryTwoCursor(long orgId , int orgTypeId,long cateIdL){
        Cursor cursor=null;
        if(getBaseDaoOperator()!=null){
            ProMenumServiceDaoOperator  sdao= (ProMenumServiceDaoOperator) getBaseDaoOperator();
            cursor= sdao.queryTwo(orgId,orgTypeId,cateIdL);

        }else{
            throw new RuntimeException("获取数据库对象为null");
        }
        return cursor;
    }
    public Cursor getQueryTwoCursor(long cateIdL){

        return getQueryTwoCursor(getOrgId(),getOrgType(),cateIdL);
    }
    @Override
    public BaseDaoOperator getBaseDaoOperator() {

        return getBaseDaoOperator(null);
    }
}
