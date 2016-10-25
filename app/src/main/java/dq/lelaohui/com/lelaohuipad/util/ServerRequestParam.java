package dq.lelaohui.com.lelaohuipad.util;

import android.os.Bundle;

import dq.lelaohui.com.lelaohuipad.LeLaohuiApp;
import dq.lelaohui.com.nettylibrary.socket.RequestParam;
import dq.lelaohui.com.nettylibrary.util.NetContant;
import dq.lelaohui.com.nettylibrary.util.Protocol_KEY;

/**
 * Created by ThinkPad on 2016/10/25.
 */

public class ServerRequestParam {
    /**
     * 获取服务数据请求的cateGory
     */
    private static final String CATEGORY = "lelaohui_server";
    public static final String USEDATA ="query.service";
    private SysVar var=null;
    public ServerRequestParam(){
        var=SysVar.getInstance();
    }
    /**
     * 获取数据的用户相关信息
     * @param parmBundle
     */
    private void setOrgBundleParm(Bundle parmBundle) {
        if(var.getOrgType()==3){
            parmBundle.putString(Protocol_KEY.PACKORG_ID,String.valueOf(var.getOrgId()));
            parmBundle.putString(Protocol_KEY.PACKORG_TYPE_ID,String.valueOf(var.getOrgType()));
        }else{
            parmBundle.putString(Protocol_KEY.SUPPLIERID,String.valueOf(var.getOrgId()));
            parmBundle.putString(Protocol_KEY.SUPPLIER_TYPE_ID,String.valueOf(var.getOrgType()));
            parmBundle.putString(Protocol_KEY.PACK_SUPPLIER_ID,String.valueOf(var.getCenterId()));
            parmBundle.putString(Protocol_KEY.PACK_SUPPLIER_TYPE_ID,String.valueOf(var.getCenterType()));
        }
    }
    /**
     * 获取类别服务项内容
     * @param cateIdL  二级级类别cateId
     * @param isPackInt  二级类别isPack
     */
    public RequestParam doQueryServerCategory(long  cateIdL,int isPackInt){
        Bundle parmBundle=new Bundle();
        setOrgBundleParm(parmBundle);
        if (isPackInt==1){
            parmBundle.putLong(Protocol_KEY.SERVICE_CATE_ID,cateIdL);
        }else{
            parmBundle.putLong(Protocol_KEY.DETAIL_CATE_ID,cateIdL);
        }
       return doQueryServerCategory(NetContant.ServiceAction.QUERY_SERVICE_CATEGORYS,Protocol_KEY.SER_PRO_PACKAGE,parmBundle);
    }

    /**
     * @param interfaceNameStr  接口名
     * @param cateKeyStr  发送数据与后台对接的Key
     * @param parmBundle 发送的相关参数信息
     */
    public RequestParam  doQueryServerCategory(String interfaceNameStr,String cateKeyStr, Bundle parmBundle){
        {

            RequestParam requestParam=getRequestParam();
            requestParam.addHeader(Protocol_KEY.ACTION,interfaceNameStr);
            requestParam.addBody(Protocol_KEY.IS_SERVER_REQ,true);
            requestParam.addBody(cateKeyStr,parmBundle);
            return requestParam;
        }
    }
    public RequestParam getRequestParam(){
        RequestParam requestParam=new RequestParam();
        requestParam.addHeader(Protocol_KEY.CATEGORY,CATEGORY);
        requestParam.addHeader(Protocol_KEY.USERDATA, USEDATA);
        return requestParam;
    }
}
