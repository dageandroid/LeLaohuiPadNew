package dq.lelaohui.com.lelaohuipad.controler;

import android.os.Bundle;
import android.text.TextUtils;

import java.util.List;

import dq.lelaohui.com.lelaohuipad.LeLaohuiApp;
import dq.lelaohui.com.lelaohuipad.LeLaohuiMainActivity;
import dq.lelaohui.com.lelaohuipad.base.LaoHuiBaseControler;
import dq.lelaohui.com.lelaohuipad.bean.LogonBena;
import dq.lelaohui.com.lelaohuipad.fragement.shop.FooterActivity;
import dq.lelaohui.com.lelaohuipad.util.JsonUtil;
import dq.lelaohui.com.lelaohuipad.util.RequestParamUti;
import dq.lelaohui.com.lelaohuipad.util.SysVar;
import dq.lelaohui.com.nettylibrary.socket.LlhResponseHandler;
import dq.lelaohui.com.nettylibrary.socket.RequestParam;
import dq.lelaohui.com.nettylibrary.util.NetContant;
import dq.lelaohui.com.nettylibrary.util.Protocol_KEY;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.UserInfo;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.manager.BaseDaoOperator;

/**
 * Created by ThinkPad on 2016/10/14.
 */

public class LogonControler extends LaoHuiBaseControler {
    private  static LogonControler controler=null;
    private RequestParamUti paramUti;
    private LogonControler(){
        paramUti=new RequestParamUti(getSysVar());
    }
    public static LogonControler getControler(){
        if(controler==null){
            synchronized (LogonControler.class){
                if(controler==null){
                    controler=new LogonControler();
                }
            }
        }
        return  controler;
    }
    public void goToMainActitivy(){
        gotoPage(LeLaohuiMainActivity.class);
    }
    private LeLaohuiApp app=null;
    /**
     * business.head = Json
     .createObjectBuilder()
     .add("sn", strSn)
     .add("uid",
     TransferClientNetworkImpl.getInstance().getUid())
     .add("userdata", "jieban").add("category", "lelaohui")
     .add("action", "getCurSuccInfo").build();
     * @param username
     * @param pwd
     */
    public void reqLogon(String username,String pwd){
        if(app==null){
            app= (LeLaohuiApp) getContext().getApplicationContext();
        }
        log("usenamr="+username+",pwd="+pwd);
        RequestParam requestParam=paramUti.getLogonParam(username,pwd);
        app.reqData(requestParam);
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
        log("doBusses: "+responseData);
        if(action.equals(NetContant.ServiceResponseAction.LOGON_RESPONSE)){
            String bodey=getResponseBody(responseData);
            String uid=responseData.getString(LlhResponseHandler.Respon_Key.UID);
            LogonBena logonBena= (LogonBena) getJsonToObject(bodey, LogonBena.class);
            if(logonBena.getCode()==0){
                saveUserInfo(logonBena);
//            gotoPage(TestMainActivity.class);
                gotoPage(FooterActivity.class);
            }else{
                Bundle bundle=new Bundle();
                bundle.putInt(RESPONSE_CODE_KEY,logonBena.getCode());
                bundle.putString(RESPONSE_MESSAGE,logonBena.getMsg());
               doErrorPromot(bundle);
            }
        }
    }
    private void saveUserInfo(LogonBena logonBena) {
        log("logonBena toString="+logonBena.toString());
        List<UserInfo> userInfoList=logonBena.getData();
//        UserDao userInfoDao=new UserDao(app.getBaseContext());
//        userInfoDao.delete(userInfoList);
//        userInfoDao.insert(userInfoList);
        if(userInfoList!=null&&userInfoList.size()!=0){
            saveUseInfo(userInfoList);
        }
//        queryUserInfo(  userInfoDao);
    }

    private void saveUseInfo(List<UserInfo> userInfoList) {
        SysVar var=SysVar.getInstance();
        UserInfo userInfo=userInfoList.get(0);
        if(userInfo==null){
            return;
        }
        var.setSysVar(Protocol_KEY.CENTERID,userInfo.getCenterId());
        var.setSysVar(Protocol_KEY.USERID,userInfo.getUserId());
        var.setSysVar(Protocol_KEY.ORG_ID,userInfo.getOrgId());
        var.setSysVar(Protocol_KEY.ORG_TYPE,userInfo.getOrgType());
        var.setSysVar(Protocol_KEY.HELP_PHONE,userInfo.getHelpPhone());
        var.setSysVar(Protocol_KEY.USERNAME, userInfo.getRealName());
        var.setSysVar(Protocol_KEY.BINDCUSTOMERSTATUS,userInfo.getBindCustomerStatus());
    }

    @Override
    public BaseDaoOperator getBaseDaoOperator() {
        return null;
    }
}
