package dq.lelaohui.com.lelaohuipad.util;

/**
 * Created by ZTF on 2016/12/7.
 */

public class Constants {

    /**
     * 订单
     */
    public static final Integer ORDER_WAITE_PAY_STATUS = 0; // 等待付款
    public static final Integer ORDER_WAITE_CONSUME =1; //待客户就餐
    public static final Integer ORDER_WAIT_SEND_STATUS = 2; // 待配送
    public static final Integer ORDER_SENDING_STATUS = 3; // 配送中
    public static final Integer ORDER_SEND_OVER_STATUS=4;// 配送成功
    public static final Integer ORDER_SUCESS_OVER_STATUS = 5;// 交易成功
    public static final Integer ORDER_CLOSE_OVER_STATUS = 6;// 交易关闭
    public static final Integer ORDER_CANCEL_SUCCESS_STATUS = 7;// 订单已取消
    public static final Integer ORDER_REFUNDING_STATUS =8;//退款中
    public static final Integer ORDER_REFUND_OVER_STATUS =9;//退款成功

    public static final String WAITE_FOR_ASSIGN = "1";//系统自动派单
    public static final String ASSIGN_TO_WAITERS = "2"; //客户指定派给服务员
    public static final String WAITERS_CONFUSE_SATAUS ="3"; //服务员已拒单
    public static final String CUSTOMER_WAITERS_ACCEPT ="4"; //客户指定的服务员已接单
    public static final String SYSTOMER_WAITERS_ACCEPT ="5"; //服务员已抢单
    public static final String SERVICE_HAVE_COMPLETE ="6";//任务执行完成
    public static final String SERVICE_HAVE_DENYED ="7";//撤单
    public static final String SERVICE_HAVE_CANCEL="8";//取消
}
