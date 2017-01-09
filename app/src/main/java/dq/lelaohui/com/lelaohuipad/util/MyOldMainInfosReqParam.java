package dq.lelaohui.com.lelaohuipad.util;

import dq.lelaohui.com.nettylibrary.socket.RequestParam;
import dq.lelaohui.com.nettylibrary.util.NetContant;
import dq.lelaohui.com.nettylibrary.util.Protocol_KEY;

/**
 * Created by ZTF on 2017/1/6.
 */

public class MyOldMainInfosReqParam {

        /**
         * 获取数据请求的cateGory
         */
        private static final String CATEGORY = "lelaohui";
        private SysVar var=null;
        public MyOldMainInfosReqParam(){
            var=SysVar.getInstance();
        }
        public RequestParam getRequestParamLLH(){
            RequestParam requestParam=new RequestParam();
            requestParam.addHeader(Protocol_KEY.CATEGORY,CATEGORY);
            return requestParam;
        }
        /**
         * 获取用户老人信息
         */
        public RequestParam   doUserOldMainInfos(){
            RequestParam requestParam=getRequestParamLLH();
            requestParam.addHeader(Protocol_KEY.ACTION,NetContant.ServiceAction.GET_USER_OLDMAN_INFOS);
            requestParam.addHeader(Protocol_KEY.USERDATA,NetContant.ServiceAction.GET_USER_OLDMAN_INFOS);
            requestParam.addBody(Protocol_KEY.IS_SERVER_REQ,false);
            requestParam.addBody(Protocol_KEY.USERID,var.getUserId());
            requestParam.addBody(Protocol_KEY.USERNAME,var.getUserName());
            requestParam.addBody(Protocol_KEY.CENTERID,var.getCenterId());
            return requestParam;
        }
}
