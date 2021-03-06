package dq.lelaohui.com.nettylibrary.util;

/**
 * Created by ThinkPad on 2016/10/20.
 */

public class Protocol_KEY {
    public static final String CATEGORY = "category";
    public static final String ACTION = "action";
    public static final String USERID = "userId";
    public static final String USERNAME = "userName";
    public static final String PWD = "pwd";
    public static final String USERDATA = "userdata";
    public static final String LOGINTYPE = "loginType";
    /**
     * 订单号
     */
    public static final String ORDER_CODE = "orderCode";
    public static final String SER_ORDER_INFO = "serOrderInfo";

    /**
     * 购买渠道
     */
    public static final String CHANNEL = "channel";
    /**
     * 是否是产品
     */
    public static final String IS_DISTR = "isDistr";

    /**
     * 中心ID
     */
    public static final String CENTERID = "centerId";
    /**
     * 今天0、明天1、后天2
     */
    public static final String ISSCOPE = "isScope";
    /**
     * 1早、2午、3晚、4夜加餐
     */
    public static final String MEALTIME = "mealTime";
    /**
     * 购买者Id
     */
    public static final String BUY_USER_ID="buyUserId";
    /**
     * 提交购物车相关信息
     */
    public static final String CONFIRM_DATA="confirmData";
    /**
     * 应急电话
     */
    public static final String HELP_PHONE = "helpPhone";
    /**
     * 用户电话
     */
    public static final String PHONE = "phone";
    /**
     * String	0—未绑定老人
     * 1—已经绑定老人
     */
    public static final String BINDCUSTOMERSTATUS = "bindCustomerStatus";
    /**
     * 机构id
     */
    public static final String ORG_ID = "orgId";
    /**
     * 机构Name
     */
    public static final String ORG_NAME = "orgName";
    /**
     * 机构类型：
     */
    public static final String ORG_TYPE = "orgType";
    /**
     * 中心Name
     */
    public static final String CENTER_NAME = "centerName";
    /**
     * 用户类型
     */
    public static final String USER_TYPE = "userType";
    /**
     * 用来封装服务分类，proCateService对应的json串
     */
    public static final String PRO_CATE_SERVICE = "proCateService";
    /**
     * 用来封装服务二级分类，serProPackage对应的json串
     */
    public static final String SER_PRO_PACKAGE = "serProPackage";
    /**
     * 类别级别0：顶级 1：一级；目前总共两级
     */
    public static final String CATE_LEVEL = "cateLevel";
    /**
     * 顶级为0；当查询二级时候使用
     */
    public static final String PARENT_ID = "parentId";
    /**
     * 1：是服务包类型 0：不是服务包类型
     */
    public static final String ISPACK = "isPack";
    /**
     * 机构类型
     */
    public static final String ORG_TYPE_ID = "orgTypeId";
    /**
     * 是否显示空菜单
     */
    public static final String IS_EMPTY_SHOW = "isEmptyShow";
    /**
     * 服务类别状态
     */
    public static final String PACK_STATUS = "packStatus";
    /**
     *
     */
    public static final String PACKORG_ID = "packorgId";
    /**
     *
     */
    public static final String PACKORG_TYPE_ID = "packorgTypeId";
    /**
     * 供应商ID
     */
    public static final String SUPPLIERID = "supplierId";
    /**
     * 根据ispack判断是否使用
     * if(ispack==1)
     * 服务类别Id
     */
    public static final String SERVICE_CATE_ID = "serviceCateId";
    /**
     * 根据ispack判断是否使用
     * if(ispack!=1)
     * 服务类别Id
     */
    public static final String DETAIL_CATE_ID = "detailCateId";
    /**
     * 是不是服务请求
     */
    public static final String IS_SERVER_REQ = "is_server";
    public static final String SUPPLIER_TYPE_ID = "supplierTypeId";
    public static final String PACK_SUPPLIER_ID = "packsupplierId";
    public static final String PACK_SUPPLIER_TYPE_ID = "packsupplierTypeId";
    /**
     * 购物车提交serOrderInfDetailList
     */
    public static final String PACK_SER_ORDER_INFO_DETAIL_LIST = "serOrderInfoDetailList";
    /**
     * 提交订单serOrderStoreBean
     */
    public static final String SER_ORDER_STORE_KEY = "serOrderStoreBean";
    /**
     * 老人Id
     */
    public static final String CUSTOMER_ID = "customerId";
    /**
     * 结束时间
     */
    public static final String END_TIME = "endTime";
    /**
     * 老人姓名
     */
    public static final String CUSTOMER_NAME = "customerName";
    /**
     * 真实姓名
     */
    public static final String REAL_NAME = "realName";
    /**
     * serialNo订单随机号
     */
    public static final String SERIAL_NO = "serialNo";
    /**
     * payAmt支付方式
     */
    public static final String PAY_AMT = "payAmt";
    /**
     * payType支付方式
     */
    public static final String PAY_TYPE = "payType";
    /**
     * supplierName
     */
    public static final String SUPPLIERNAME = "supplierName";

    /**
     * 总额
     */
    public static final String TOTAL_MONEY="totalMoney";
/**
 * 老人Id
 */
    public static  final String OLD_MAN_ID="oldManId";
    /**
     * orderData 订单数据
     */
    public static  final String ORDER_DATA="orderData";
    /**
     * 订单号
     */
    public static final String ORDER_NO = "orderNo";

    /**
     * 是否创建新的地址
     */
    public static final String IS_FROM_CREAT_ORDER_ADDRESS = "isFromCreatOrder";
    /**
     * 地址Id
     */
    public static final String ADDRESS_ID = "addressId";
    /**
     * address 详情
     */
    public static final String ADDRESS = "address";



    public static final class CACHE_KEY {
        /**
         */
        public static final String PRODUCT_TYPE_KEY = "PRODUCT_TYPE_KEY";
        /**
         */
        public static final String SERVICE_TYPE_KEY = "SERVICE_TYPE_KEY";
        /**
         * 明天
         */
        public static final String FOOT_TOMORROW_KEY = "FOOT_TOMORROW_KEY";
        /**
         * 后天
         */
        public static final String FOOT_AFTERTOMORROW_KEY = "FOOT_AFTERTOMORROW_KEY";
        public static final String FOOT_TODAY_KEY = "FOOT_TODAY_KEY";
        /**
         * 成功
         */
        public static final String SUCCESS_CODE = "2";
    }


}
