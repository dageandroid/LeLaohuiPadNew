package dq.lelaohui.com.nettylibrary.util;

/**
 * Created by ThinkPad on 2016/10/20.
 */

public class ServiceNetContant {
    public  static class ServiceAction{
        /**
         *查询服务类别
         */
        public static final String GETSERPROCATEJSONLIST="getSerProCateJsonList";
    }
    public static class ServiceResponseAction{
        public static final String GETSERPROCATEJSONLIST_RESPONSE="getSerProCateJsonList.response";

        public static final String  QUERY_SERVICE_CATEGORYSJSONLIST_RESPONSE="getInitSerProPackList.response";
        /**
         * 提交订单
         */
        public static final String  CAL_ORDER_MONEY="calOrderMoney.response";
        /**
         * 查询用户地址
         */
        public static final String  QUERY_USER_ADDRESS_RESPONSE="query.user.address.response";
        /**
         * 删除用户地址
         */
        public static final String  DELETE_USER_ADDRESS_RESPONSE="del.user.address.response";
        /**
         * 添加用户地址
         */
        public static final String  ADD_USER_ADDRESS_RESPONSE="add.user.address.response";
        /**
         *确认下单接口
         */
        public static final String  SAVE_MOBILE_ORDER_INFO="saveMobileOrderInfo.response";
        /**
         * 乐老卡支付接口
         */
        public static final String UPLOAD_SERVER_ORDER_PAYMENY = "serverOrderPayment.response";
        /**
         * 提交订单，支付完成，返回订单信息接口
         */
        public static final String GET_SERVER_ORDER_SUCC_INFO_RESPONSE = "getSerOrderInfos.response";
        /**
         * 获取餐品信息相关接口
         */
        public static final String QUERY_FOOD_INFO_RESPONSE = "query.food.info.response";

        /**
         * 餐品订单支付接口
         */
        public static final String     FOOD_ORDER_PAYMENT_RESPONSE="food.order.payment.response";

        /**
         * 提交食物订单接口
         */
        public static final String     FOOD_ORDER_CONFIRM_RESPONSE="food.order.confirm.response";


        public static String GET_STOCK_DETAIL_BY_USER_RESPONSE="";
    }
}
