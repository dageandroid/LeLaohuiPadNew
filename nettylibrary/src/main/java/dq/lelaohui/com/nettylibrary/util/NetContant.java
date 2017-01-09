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
        public static final String MESSAGE_RESPONSE_ACTION="dq.lelaohui.net.message.response";
        public static final String IMAGE_URL_ACTION="query.image.url";
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
         * 提交餐品购物车相关内容接口
         */
        public static final String CONFIRM_FOOD_ORDER="confirm.food.order";

        /**
         *食物订单提交
         */
        public static final String FOOD_ORDER_CONFIRM="food.order.confirm";
        /**
         * 食物订单支付
         */
        public static final String FOOD_ORDER_PAYMEN="food.order.payment";

        /**
         * 获得服务一级和获取二级类别都使用该接口
         */

        public static final String QUERY_SERVICE_CATEGORY="getSerProCateJsonList";
        /**
         * 获得服务项内容
         */
        public static final String QUERY_SERVICE_CATEGORYS="getInitSerProPackList";

        /**
         * 获得服务产品信息
         */
        public static final String QUERY_SERVICE_PRODUCT="query.service.product";
        /**
         * 上传购物车内的数据
         */
        public static final String UPLOAD_SHOPPING_CAR_DATA="calOrderMoney";
        /**
         * 获取用户地址
         * query.user.address
         */
        public static final String QUERY_USER_ADDRESS="query.user.address";
        /**
         * 确认下单
         */
        public static final String UPLOAD_USER_ORDERINFO="saveMobileOrderInfo";

        /**
         * 服务支付接口
         */
        public static final String UPLOAD_SERVER_ORDER_PAYMENY = "serverOrderPayment";
        /**
         * 获取订单信息接口
         */
        public static final String GET_SERVER_ORDER_SUCC_INFO = "getSerOrderInfos";

        /**
         * 获取用户地址信息
         */
        public static final String GET_USER_ADDRESS_INFO = "query.user.address";
        /**
         * 删除用户信息
         */
        public static final String DELETE_USER_ADDRESS_INFO = "del.user.address";
        /**
         * 插入用户信息
         */
        public static final String EDIT_USER_ADDRESS_INFO = "add.user.address";
        /**
         * 获取库存信息
         */
        public static final String GET_STOCK_DETAIL_BY_USER = "getStockDetailByUser";
        /**
         * 查询预约服务接口
         */
        public static final String GET_SERVER_DETAIL_BY_INFO = "getDetailByUserAndDate";
        /**
         * 提交服务预约信息
         */
        public static final String CONFIRM_ORDER_SERVER_APP = "confirmOrderServiceApp";

        /**
         * 获取服务员相关信息
         */
        public static final String GET_SERVER_INFOS = "query.waiter.list";

        /**
         * 查询用户预约记录
         */
        public static final String SEARCH_APPOINTMENT_FOR_APP = "searchAppointmentForApp";

        /**
         * 获取用户设备相关信息
         */
        public static final String GET_DEVICE_STATUS_INFOS = "query.device.status";
        /**
         * 获取用户老人基本信息
         */
        public static final String GET_USER_OLDMAN_INFOS = "query.oldman.info";

    }

}
