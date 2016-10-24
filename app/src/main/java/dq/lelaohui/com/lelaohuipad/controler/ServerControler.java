package dq.lelaohui.com.lelaohuipad.controler;

import android.os.Bundle;
import android.text.TextUtils;

import dq.lelaohui.com.lelaohuipad.LeLaohuiApp;
import dq.lelaohui.com.lelaohuipad.base.LaoHuiBaseControler;
import dq.lelaohui.com.lelaohuipad.bean.ServerCate;
import dq.lelaohui.com.nettylibrary.socket.RequestParam;
import dq.lelaohui.com.nettylibrary.util.NetContant;
import dq.lelaohui.com.nettylibrary.util.Protocol_KEY;
import dq.lelaohui.com.nettylibrary.util.ServiceNetContant;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.manager.BaseDaoOperator;

/**
 * Created by ThinkPad on 2016/10/20.
 */

public class ServerControler extends LaoHuiBaseControler {
    private static ServerControler serverControler=null;
    private static final String SUCCESS_CODE="200";
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
                        Bundle bundle=new Bundle();
                        getIControlerCallBack().result(bundle);
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
        RequestParam requestParam=getRequestParam();
        requestParam.addHeader(Protocol_KEY.ACTION, NetContant.ServiceAction.QUERY_SERVICE_CATEGORY);
        requestParam.addBody(Protocol_KEY.IS_SERVER_REQ,true);

        Bundle parmBundle=new Bundle();
        if(getOrgType()==3){
            parmBundle.putString(Protocol_KEY.PACKORG_ID,String.valueOf(getOrgId()));
            parmBundle.putString(Protocol_KEY.PACKORG_TYPE_ID,String.valueOf(getOrgType()));

        }else{
            parmBundle.putString(Protocol_KEY.SUPPLIERID,String.valueOf(getOrgId()));
            parmBundle.putString(Protocol_KEY.SUPPLIER_TYPE_ID,String.valueOf(getOrgType()));
            parmBundle.putString(Protocol_KEY.PACK_SUPPLIER_ID,String.valueOf(getCenterId()));
            parmBundle.putString(Protocol_KEY.PACK_SUPPLIER_TYPE_ID,String.valueOf(getCenterType()));
        }
            parmBundle.putInt(Protocol_KEY.IS_EMPTY_SHOW,IS_EMPTY_NOT_SHOW);

            parmBundle.putInt(Protocol_KEY.CATE_LEVEL,CATE_LEVEl_PARENT);

            parmBundle.putInt(Protocol_KEY.ISPACK,NO_PACK_SERVER_TYPE);

        parmBundle.putInt(Protocol_KEY.PACK_STATUS,PACKSTATUS);
        requestParam.addBody(Protocol_KEY.PRO_CATE_SERVICE,parmBundle);
        app.reqData(requestParam);
    }
    private RequestParam getRequestParam(){
        RequestParam requestParam=new RequestParam();
        requestParam.addHeader(Protocol_KEY.CATEGORY,CATEGORY);
        requestParam.addHeader(Protocol_KEY.USERDATA, USEDATA);
        return requestParam;
    }

    @Override
    public BaseDaoOperator getBaseDaoOperator() {
        return null;
    }
}
