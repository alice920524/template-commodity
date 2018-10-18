package com.duia.commodity.common;

/**
 * Created by 李恒名 on 2017/7/18.
 */
public final class Constant {
    private Constant() {
    }

    public static final String BOOK_ORDER_PREFIX = "T";//图书前缀

    public static final String DES_KEY_ORDER = "Yz0*6VriskRoU#@^";
    public static final String DES_KEY_DISCOUNT = "Yz0*6VriskRoU#@^_DISCOUNT";
    public static final String PAY_TYPE_BATCH = "pay_type_batch";//分批订单
    public static final String PAY_TYPE_UMONEY = "pay_type_umoney";//百度有钱花支付
    public static final String PAY_TYPE_COFFEE = "pay_type_coffee";//咖啡易融支付
    public static final String PAY_TYPE_HAIMI = "pay_type_haimi"; //海米支付
    public static final String PAY_TYPE_ZEROPAY = "pay_type_zero";//零元购支付
    public static final String PAY_TYPE_ADDSTUDENT = "pay_type_add_student";//添加学员
    //分批支付订单金额
    public static final String BATCH_PAY_MAX_MONEY = "500";
    //百度、咖啡最小使用额
    public static final Double BAIDU_PAY_PERCENT = 0.9;
    public static final String BAIDU_UMONEY_PAY_MIN_MONEY = "1000";
    public static final Double COFFEE_EASE_PAY_PERCENT = 0.9;
    public static final String COFFEE_EASE_PAY_MIN_MONEY = "800";
    public static final Double HAIMI_PAY_PERCENT = 0.9;
    public static final String HAIMI_PAY_MIN_MONEY = "1000";

    public static final String PAY_STATUS_SUCCESS = "pay_status_success";
    public static final String PAY_STATUS_THROUGH = "pay_status_through";
    public static final String PAY_STATUS_NON = "pay_status_non";
    public static final String PAY_STATUS_FAILED = "pay_status_failed";
    public static final String PAY_STATUS_BAD = "pay_status_bad";
    public static final String PAY_STATUS_YET = "pay_status_yet";
    public static final String TRADE_WAIT_BUYER_PAY = "TRADE_WAIT_BUYER_PAY";//  没有付款
    public static final String TRADE_SELLER_SEND_GOODS = "TRADE_SELLER_SEND_GOODS";//    已付款
    public static final String TRADE_WAIT_BUYER_CONFIRM_GOODS = "TRADE_WAIT_BUYER_CONFIRM_GOODS";// 已发货
    public static final String TRADE_FINISHED = "TRADE_FINISHED";//   交易完成
    public static final String TRADE_AUTOMATIC_CLOSED = "TRADE_AUTOMATIC_CLOSED";//    交易取消

    public static final Integer WEB_TYPE = 999;
    public static final Integer WAP_TYPE = 998;

    public static final int ORDER_TYPE_NORMAL = 0; // 普通订单
    public static final int ORDER_TYPE_UPGRADE = 1; // 升级订单

    public static final String DISCOUNT_TYPE_PT = "pt"; // 普通优惠券
    public static final String DISCOUNT_TYPE_SJ = "sj"; // 升级优惠券
    public static final String DISCOUNT_TYPE_ZERO = "zero"; // 零元购优惠券
    public static final String DISCOUNT_TYPE_THEMATIC = "thematic"; // 专题课
    public static final String DISCOUNT_TYPE_YHTQ = "yhtq"; // 优惠特权券

    public static final Integer DISCOUNT_USE_REFUSE = 0; // 不可使用优惠券
    public static final Integer DISCOUNT_USE_ACCESS = 1; // 可以使用优惠券

    public static final Integer DISCOUNT_COURSE_TYPE_ALL = -1;// 通用优惠券

    public static final Integer DISCOUNT_USE_UN_LIMIT = 0; // 无上限控制
    public static final Integer DISCOUNT_USE_TOP_LIMIT = 1; // 有使用上限
    public static final Integer DISCOUNT_USE_ZERO_PAY = 2; // 配置零元购

    public static final Integer COM_MODE_SPECIAL = 1; // 套餐、组合商品
    public static final Integer COM_MODE_NORMAL = 2; // 普通商品

    public static final String CACHE_KEY_PREFIX_CHAPTER_LIST = "cacheSuperFast:class.course.by.class.";

    public static final String CACHE_KEY_PREFIX_COMMODITY_DEADLINE = "item:commodity.deadline.";

    public static final String CACHE_KEY_PREFIX_TEACHER_LIST = "item:class.teacher.";

    public static final String CACHE_KEY_PREFIX_TEACHER_SCORE = "item:teacher.score.";

    public static final String CACHE_KEY_PREFIX_SHARE_HASH = "cacheSuperFast:commodity.share.";

    public static final String CACHE_KEY_PREFIX_ACCESS_TOKEN = "wx.access.token:";

    public static final String CACHE_KEY_PREFIX_TICKET = "wx.api.ticket:";

    // 课表节playType字段
    public static final Integer CLASS_SCHEDULE_COURSE_PLAY_TYPE_LIVE = 1; // 直播
    public static final Integer CLASS_SCHEDULE_COURSE_PLAY_TYPE_VIDEO = 2; // 视频
    public static final Integer CLASS_SCHEDULE_COURSE_PLAY_TYPE_PLAYBACK = 3; // 回放

    /**
     * app类型：公众号
     */
    public static final Integer APP_TYPE_WX_GZH = 0;

    // 订单来源
    public static final String ORDER_SOURCE_NBACK = "nback";
    public static final String ORDER_SOURCE_WEB = "web";
    // ios内购  APP_**AppType**
    public static final String ORDER_SOURCE_IOS = "APP_";
    // 多质保期质保期标识 0:普通 1:多类型
    public static final Integer GUA_MUL_COMMON = 0;
    public static final Integer GUA_MUL_MULTITYPE = 1;

    // 类型
    public static final Integer GUA_TYPE_COMMON = 0;
    public static final Integer GUA_TYPE_FIRST = 1;
    public static final Integer GUA_TYPE_SECOND = 2;


}