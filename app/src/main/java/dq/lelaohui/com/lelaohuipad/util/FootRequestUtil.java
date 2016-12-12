package dq.lelaohui.com.lelaohuipad.util;

import dq.lelaohui.com.nettylibrary.socket.RequestParam;
import dq.lelaohui.com.nettylibrary.util.Protocol_KEY;

/**
 * Created by ZTF on 2016/12/9.
 */

public class FootRequestUtil {
    private String centerId;

    public FootRequestUtil(String centerId, String orgId, String orgType) {
        this.centerId = centerId;
        this.orgId = orgId;
        this.orgType = orgType;
    }

    private String orgId;
    private String orgType;
    /**
     * 获取餐品信息发送数据
     * @param isScope  今天，明天，后天
     * @param userIdStr  用户Id
     * @param interfaceName  发送请求的接口名
     * @return
     */
    public RequestParam getRequestParam(String isScope, String userIdStr, String interfaceName) {
        RequestParam requestParam=new RequestParam();
        requestParam.addHeader(Protocol_KEY.USERDATA,isScope);
        requestParam.addHeader(Protocol_KEY.ACTION,interfaceName );
        requestParam.addBody(Protocol_KEY.ISSCOPE,Integer.valueOf(isScope));
        requestParam.addBody(Protocol_KEY.USERID,userIdStr);
        requestParam.addBody(Protocol_KEY.CENTERID,centerId);
        requestParam.addBody(Protocol_KEY.ORG_ID,String.valueOf(orgId));
        requestParam.addBody(Protocol_KEY.ORG_TYPE,String.valueOf(orgType));
        return requestParam;
    }
    public RequestParam getRequestParam(String interfaceName) {
        RequestParam requestParam=new RequestParam();
        requestParam.addHeader(Protocol_KEY.ACTION,interfaceName );
        requestParam.addBody(Protocol_KEY.CENTERID,centerId);
        requestParam.addBody(Protocol_KEY.ORG_ID,String.valueOf(orgId));
        requestParam.addBody(Protocol_KEY.ORG_TYPE,String.valueOf(orgType));
        return requestParam;
    }
}
