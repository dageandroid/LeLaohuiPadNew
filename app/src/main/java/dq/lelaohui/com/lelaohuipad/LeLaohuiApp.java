package dq.lelaohui.com.lelaohuipad;

import android.app.Application;

import dq.lelaohui.com.lelaohuipad.util.SysVar;
import dq.lelaohui.com.nettylibrary.port.ReqParam;
import dq.lelaohui.com.nettylibrary.socket.NetManager;
import dq.lelaohui.com.nettylibrary.util.Protocol_KEY;

/**
 * Created by ThinkPad on 2016/10/13.
 * <p>
 * 111.204.236.14 60090
 * <p>
 * litie    123456
 * 后台管理数据 的地址是   111.204.236.3:8081
 * <p>
 * Lazy Sheep 2016/10/13 10:22:26
 * 用户 pingtai   密码 123456
 */

public class LeLaohuiApp extends Application {
    public static final String IP = "111.204.236.14";
    public static final String PORT = "60090";
    private static final String CATEGORY = "lelaohui";

    public NetManager getNetManager() {
        return netManager;
    }

    private NetManager netManager = null;

    @Override
    public void onCreate() {
        super.onCreate();
        if (netManager == null) {
            netManager = NetManager.getInstance();
        }
        SysVar.getInstance().setmContext(this);
//        LpushManager.getInstance().registerPush(this,null);
    }

    public void init(NetManager.NetStatueCallBack statusCallBack) {
        init(IP, PORT);
        netManager.setStatusCallBack(statusCallBack);
//        ServerUtil.startNetServer(this, NetService.class);
        startNet();
    }
    public void setProgressBarListener(NetManager.ProgressBarListener listener){
        netManager.setProgressBarListener(listener);
    }
    private void startNet() {
        netManager.startNet();
    }

    private void init(String ip, String port) {
        netManager.setContext(this);
        netManager.setIpAndPort(ip, port);

    }

    public void reqData(ReqParam reqParam) {
        if (reqParam.getHeader(Protocol_KEY.CATEGORY) == null) {
            reqParam.addHeader(Protocol_KEY.CATEGORY, CATEGORY);
        }else{
            reqParam.addHeader(Protocol_KEY.CATEGORY, String.valueOf(reqParam.getHeader(Protocol_KEY.CATEGORY)));
        }
        boolean isService=false;
        Object object=reqParam.getBody(Protocol_KEY.IS_SERVER_REQ);
        if(object!=null){
            isService= (boolean) object;
            reqParam.removeBody(Protocol_KEY.IS_SERVER_REQ);
        }
        if(!isService){

            if (SysVar.getInstance().getOrgId() != -1) {
                reqParam.addBody(Protocol_KEY.ORG_ID, SysVar.getInstance().getOrgId());
            }
            if (SysVar.getInstance().getOrgId() != -1) {
                reqParam.addBody(Protocol_KEY.ORG_TYPE, SysVar.getInstance().getOrgType());
            }
            if (SysVar.getInstance().getUserId()!=null){
                reqParam.addBody(Protocol_KEY.USERID, SysVar.getInstance().getUserId());
            }
        }
        netManager.reqData(reqParam);
    }
}
