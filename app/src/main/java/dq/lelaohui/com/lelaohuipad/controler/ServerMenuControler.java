package dq.lelaohui.com.lelaohuipad.controler;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.reflect.TypeToken;

import java.util.List;

import dq.lelaohui.com.lelaohuipad.LeLaohuiApp;
import dq.lelaohui.com.lelaohuipad.base.LaoHuiBaseControler;
import dq.lelaohui.com.lelaohuipad.bean.SerInitProPackBean;
import dq.lelaohui.com.lelaohuipad.bean.SerOrderInfoCate;
import dq.lelaohui.com.lelaohuipad.bean.SerOrderInfoData;
import dq.lelaohui.com.lelaohuipad.bean.ServerMenuCate;
import dq.lelaohui.com.lelaohuipad.dao.ProMenumServiceDaoOperator;
import dq.lelaohui.com.lelaohuipad.dao.ProServerContentDaoOperator;
import dq.lelaohui.com.lelaohuipad.fragement.shop.dataprovider.ServerMenuDataManager;
import dq.lelaohui.com.lelaohuipad.util.ServerRequestParam;
import dq.lelaohui.com.nettylibrary.socket.RequestParam;
import dq.lelaohui.com.nettylibrary.util.ServiceNetContant;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.ProCateMenuService;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.SerInitProPack;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.manager.BaseDaoOperator;

/**
 * Created by ZTF on 2016/11/1.
 */

public class ServerMenuControler extends LaoHuiBaseControler {
    public static final String SUCCESS_CODE="200";
    private static ServerMenuControler serverMenuControler=null;
    private static final String TAG="ServerMenuControler";
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
            ServerMenuCate serverCate = getBodyResponse(responseData);
            if(serverCate.getCode().equals(SUCCESS_CODE)){
                if(getIControlerCallBack()!=null){//解析数据成功，通知UI界面
                    List<ProCateMenuService> data= (List< ProCateMenuService>) getJsonToObject(serverCate.getObj(),new TypeToken< List<ProCateMenuService> >(){}.getType(),true);
                      if (data!=null){
                          setDataList(data);
                      }
                    Bundle bundle=new Bundle();
                    bundle.putString("action",ServiceNetContant.ServiceResponseAction.GETSERPROCATEJSONLIST_RESPONSE);
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
        }else if (ServiceNetContant.ServiceResponseAction.QUERY_SERVICE_CATEGORYSJSONLIST_RESPONSE.equals(action))
        {
//            SerInitProPackBean serverCate = getBodySerInitProPackResponse(responseData);
//            if(serverCate.getCode().equals(SUCCESS_CODE)){
//                if(getIControlerCallBack()!=null){//解析数据成功，通知UI界面
//                    List<SerInitProPack> serInitProPacksData= (List<SerInitProPack> )getJsonToObject(serverCate.getObj(),new TypeToken< List<SerInitProPack> >(){}.getType(),true);
//                    if (serInitProPacksData!=null){
//                        setSerInitProPackData(serInitProPacksData);
//                    }
//                    log("doBusses ist<SerInitProPack> : "+serInitProPacksData.toString());
//                    Bundle bundle=new Bundle();
//                    bundle.putString("action",ServiceNetContant.ServiceResponseAction.QUERY_SERVICE_CATEGORYSJSONLIST_RESPONSE);
//                    getIControlerCallBack().result(bundle);
//                }
//            }else{
//
//            }
        }else if ((ServiceNetContant.ServiceResponseAction.CAL_ORDER_MONEY.equals(action))){
            log("doBusses CAL_ORDER_MONEY : "+responseData);
            SerOrderInfoCate serOrderInfoCate=getBodySerOrderInfoResponse(responseData);
            if(serOrderInfoCate.getCode().equals(SUCCESS_CODE)){
                if(getIControlerCallBack()!=null){//解析数据成功，通知UI界面
                    SerOrderInfoData infoData= serOrderInfoCate.getData();
                log("doBusses infoData="+infoData.toString());
                    Bundle bundle=new Bundle();
                    bundle.putString("action",ServiceNetContant.ServiceResponseAction.CAL_ORDER_MONEY);
                    bundle.putParcelable("serOrderInfo",infoData);
                    getIControlerCallBack().result(bundle);
                }
            }
        }
        //服务器返回的数据会回调这里。在这里写解析。要判断一下服务器返回的ResponseAction,如果需要更新Activity则
       // Bundle bundle=new Bundle();//将要传递的数据封装到bundle里
//        getIControlerCallBack().result(bundle);//并调用此方法回调通知Actitity
        log("doBusses: "+responseData);
    }
    public SerInitProPackBean getBodySerInitProPackResponse(Bundle responseData) {
        String body=getResponseBody(responseData);
        return (SerInitProPackBean) getJsonToObject(body,SerInitProPackBean.class,false);
    }
    private SerOrderInfoCate getBodySerOrderInfoResponse(Bundle responseData ){
        String body=getResponseBody(responseData);
        return (SerOrderInfoCate) getJsonToObject(body,SerOrderInfoCate.class,false);
    }
    private ServerMenuCate getBodyResponse(Bundle responseData) {
        String body=getResponseBody(responseData);
        return (ServerMenuCate) getJsonToObject(body,ServerMenuCate.class,false);
    }

    /**
     * 二级服务类别插入数据库方法
     * @param data
     */
    public  void setDataList(  List<ProCateMenuService> data){
        if(data!=null){
            for (int i=0;i<data.size();i++){
                data.get(i).setOrgId(getOrgId());
                data.get(i).setOrgTypeId(getOrgType());
            }
            insertData(data);
        }
    }
    public static final int DETAIL_CATEID=-2;
    /**请求数据 统一接口
     * @param isrfesh 是否立即刷新。true 立即刷新，false判断缓存
     * @param cateIdL
     * @param isPackInt
     * @param cateLevelInt 当cateLevelInt 为-1时，为右侧详细页面
     */
    public void getQueryServerCateqory(boolean isrfesh,long  cateIdL,int isPackInt,int cateLevelInt) throws InterruptedException {

        if(getDataManager()!=null){
            ServerMenuDataManager dataManager= (ServerMenuDataManager) getDataManager();
            Bundle bundle=new Bundle();
            bundle.putLong(ServerMenuDataManager.CATE_ID_KEY,cateIdL);
            if(cateLevelInt!=DETAIL_CATEID){
                bundle.putInt(ServerMenuDataManager.CATE_LEVEL_KEY,cateLevelInt);
            }
            bundle.putInt(ServerMenuDataManager.PACK_IS_KEY,isPackInt);
            if(cateLevelInt!=DETAIL_CATEID){
                dataManager.reqData(ServerMenuDataManager.LEFT_MENUM,bundle,isrfesh);
            }else{
                dataManager.reqData(ServerMenuDataManager.SERVER_DETAILE_PAGE,bundle,isrfesh);
            }
        }
    }
    /**
     * 获取类别下的服务项内容
     * @param data
     */
    public  void setSerInitProPackData(List<SerInitProPack> data){
        if (data!=null){
            for (int i=0;i<data.size();i++){
                if(data.get(i).serInitProPackDetailList!=null){
                    for (int j=0;j<data.get(i).serInitProPackDetailList.size();j++){
                        data.get(i).serInitProPackDetailList.get(j).setOrgId(getOrgId());
                        data.get(i).serInitProPackDetailList.get(j).setOrgTypeId(getOrgType());
                    }
                }

            }
//            insertData(GET_SER_INIT_PROPACK_DATA,data);
            proServerContentDao.intsert(data);
            log("服务项插入数据库成功。。。。。");
        }
    }
    /**
     * 获取二级服务项右分类
     */
    public String doQueryServerCategory(long  cateIdL,int isPackInt){
        LeLaohuiApp app= (LeLaohuiApp) getContext();
        if(app==null){
            throw  new RuntimeException(" app is null exception");
        }
        RequestParam requestParam1=requestParam.doQueryServerCategory(cateIdL,isPackInt);
        app.reqData(requestParam1);
        return requestParam1.getCurrenSN();
    }
   /**
    * 获取二级服务左分类
    */
    public String doQueryServerCategory(long  cateIdL,int isPackInt,int cateLevelInt){
        LeLaohuiApp app= (LeLaohuiApp) getContext();
        if(app==null){
            throw  new RuntimeException(" app is null exception");
        }
        RequestParam requestParam1=requestParam.doQueryServerCategory(cateIdL,isPackInt,cateLevelInt);
        String SN=requestParam1.getCurrenSN();
        app.reqData(requestParam1);
        return SN;
    }
    /**
     * @param interfaceNameStr  接口名
     * @param cateKeyStr  发送数据与后台对接的Key
     * @param parmBundle 发送的相关参数信息
     */
    public String  doQueryServerCategory(String interfaceNameStr,String cateKeyStr, Bundle parmBundle){
        {
            LeLaohuiApp app= (LeLaohuiApp) getContext();
            if(app==null){
                throw  new RuntimeException(" app is null exception");
            }
            RequestParam requestParam1=requestParam.doQueryServerCategory(interfaceNameStr,cateKeyStr,parmBundle);
            app.reqData(requestParam1);
            return requestParam1.getCurrenSN();
        }
    }

    private ProMenumServiceDaoOperator proCateMenuServiceDao;
    private ProServerContentDaoOperator proServerContentDao;
    public static final String GET_SER_INIT_PROPACK_DATA="getInitSerProPackList";
    public BaseDaoOperator getBaseDaoOperator(String version) {
        if(TextUtils.isEmpty(version)){
            proCateMenuServiceDao= ProMenumServiceDaoOperator.getInstance();
            proCateMenuServiceDao.setmContext(getContext());
            return proCateMenuServiceDao;
        }
        if(GET_SER_INIT_PROPACK_DATA.equals(version)){
            Log.i(TAG,"version"+version);
            proServerContentDao= ProServerContentDaoOperator.getInstance();
            proServerContentDao.setmContext(getContext());
            return proServerContentDao;
        }

        return null;
    }

    /**
     * 查询普通类别的二级服务分类数据
     * @param orgId     用户机构orgid
     * @param orgTypeId  用户机构类型
     * @param cateIdL  一级类别cateId
     * @return
     */
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

    /**
     * 查询二级服务分类Sql
     * @param orgId  当前登陆人机构Id
     * @param orgTypeId   当前登陆人机构类型
     * @return
     */
    private Cursor getQueryCateCursor(long orgId , int orgTypeId){
        Cursor cursor=null;
        if(getBaseDaoOperator()!=null){
            ProMenumServiceDaoOperator  proMenuServiceDao= (ProMenumServiceDaoOperator) getBaseDaoOperator();
            cursor= proMenuServiceDao.queryDataTwo(orgId,orgTypeId);
        }else{
            throw new RuntimeException("获取数据库对象为null");
        }
        return cursor;
    }

    /**
     * 查询二级服务分类Sql
     * @return
     */
    public Cursor getQueryCateCursor(){

        return getQueryCateCursor(getOrgId(),getOrgType());
    }

    /**
     * 查询服务项sql
     * @return
     */
    private Cursor getSerInitProCursor(long orgId , int orgTypeId,int cateId){
        Cursor cursor=null;
        if(getBaseDaoOperator()!=null){
            ProServerContentDaoOperator  proServerContentDao= (ProServerContentDaoOperator) getBaseDaoOperator(GET_SER_INIT_PROPACK_DATA);
            cursor= proServerContentDao.querySerInitProData(orgId,orgTypeId,cateId);
            log("获取服务项数据成功。。。。。。");
        }else{
            throw new RuntimeException("获取数据库对象为null");
        }
        return cursor;
    }

    /**
     * 获取服务项查询
     * @param cateIdL
     * @return
     */
    public Cursor getSerInitProCursor(int cateIdL){

        return getSerInitProCursor(getOrgId(),getOrgType(),cateIdL);
    }

    @Override
    public BaseDaoOperator getBaseDaoOperator() {

        return getBaseDaoOperator(null);
    }
}
