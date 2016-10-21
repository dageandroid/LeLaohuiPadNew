package dq.lelaohui.com.nettylibrary.util;

/**
 * Created by ThinkPad on 2016/10/12.
 */

public class NetContant {
    public static class NET_ACTION{
        /**
         * 服务器返回数据Action
         */
        public static  String RESP_ACTION="dq.lelaohui.net.response.action";

        /**
         * 服务器推送消息action
         */
        public static String MESSAGE_RESPONSE_ACTION="dq.lelaohui.net.message.response";
    }
    public static class ServiceResponseAction{
        /**
         * 登入服务器返回action
         */
        public static String LOGON_RESPONSE="logon.response";
    }
    public  static class ServiceAction{
        /**
         * 登入接口
         */
        public static final String LOGON="logon";
        /**
         * 获取餐饮类别
         */
        @Deprecated
        public static final String QUERY_FOOD_CATEGORY="query.food.category";
        /**
         * 获取菜品信息
         */
        public static final String QUERY_FOOD_INFO="query.food.info";
        /**
         * 获得服务类别
         */
        public static final String QUERY_SERVICE_CATEGORY="getSerProCateJsonList";
        /**
         * 获得服务产品信息
         */
        public static final String QUERY_SERVICE_PRODUCT="query.service.product";
    }
}
