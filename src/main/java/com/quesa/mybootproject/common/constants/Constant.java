package com.quesa.mybootproject.common.constants;



import com.quesa.mybootproject.common.util.Constants;

import java.util.HashMap;
import java.util.Map;

public class Constant extends Constants {

    /**
     * 用户缓存标识
     */
    public static final String USERCACHE = "USERCACHE";

    /**
     * int类型的boolean值 真 1
     */
    public static final Integer TRUE = 1;
    /**
     * int类型的boolean值 假 0
     */
    public static final Integer FALSE = 0;

    /**
     * 等于符号常量
     */
    public static final String E_Q = "eq";
    /**
     * 大于符号常量
     */
    public static final String G_T = "gt";
    /**
     * 小于符号常量
     */
    public static final String L_T = "lt";

    /**
     * 0字符串
     */
    public static final String ZERO_STR = "0";

    /**
     * 特殊字符串
     */
    public static final String SPECIAL_STR = "^§^";

    /**
     * 开始标志
     */
    public final static String START_TYPE = "start round";

    /**
     * 结束标志
     */
    public final static String END_TYPE = "end round";

    /**
     * 系统部门
     */
    public final static String SYS_DEPT = "SYS_DEPT";
    /**
     * 系统角色
     */
    public final static String SYS_ROLE = "SYS_ROLE";

    /**
     * 系统城市
     */
    public final static String SYS_CITY = "SYS_CITY";

    /**
     * 系统用户
     */
    public final static String SYS_USER = "SYS_USER";


    /**
     * 操作日志redis存储key
     */
    public final static String HAND_RECORD_LOG_REDIS_KEY = "HAND_RECORD_LOG_REDIS_KEY";

    /**
     * 消息推送redis存储key
     */
    public final static String PUSH_MESSAGE_REDIS_KEY = "PUSH_MESSAGE_REDIS_KEY";

    /**
     * 排序类型-升序关键字
     */
    public final static String SORT_TYPE_ASC = "asc";
    /**
     * 排序类型-降序关键字
     */
    public final static String SORT_TYPE_DESC = "desc";

    /**
     * 总计关键字
     */
    public final static String TOTAL_KEY = "Total";

    /**
     * 百分号
     */
    public final static String PERCENT_SIGN = "%";

    /**
     * 空值关键字
     */
    public final static String EMPTY_KEY = "EMPTY";


    /**
     * 获取相反的int型boolean 变量值
     *
     * @param intBoolean {@link #TRUE} {@link #FALSE}
     * @return
     */
    public static Integer getNotIntegerBoolean(Integer intBoolean) {
        if (TRUE.equals(intBoolean)) {
            return FALSE;
        }
        return TRUE;
    }

    public static String getCityName(String cityCode) {
        String cityName = "";
        //北京
        if ("010".equals(cityCode)) {
            cityName = "bj";
        }
        //上海
        else if ("021".equals(cityCode)) {
            cityName = "sh";
        }
        //天津
        else if ("022".equals(cityCode)) {
            cityName = "tj";
        }
        //重庆
        else if ("023".equals(cityCode)) {
            cityName = "cq";
        }
        //武汉
        else if ("027".equals(cityCode)) {
            cityName = "wh";
        }
        //深圳
        else if ("0755".equals(cityCode)) {
            cityName = "sz";
        }
        //广州
        else if ("020".equals(cityCode)) {
            cityName = "gz";
        }
        //杭州
        else if ("0571".equals(cityCode)) {
            cityName = "hz";
        }
        //南京
        else if ("025".equals(cityCode)) {
            cityName = "nj";
        }
        //成都
        else if ("028".equals(cityCode)) {
            cityName = "cd";
        }
        //西安
        else if ("029".equals(cityCode)) {
            cityName = "xa";
        }
        //郑州
        else if ("0371".equals(cityCode)) {
            cityName = "zz";
        }
        //济南
        else if ("0531".equals(cityCode)) {
            cityName = "jn";
        }
        //兰州
        else if ("0931".equals(cityCode)) {
            cityName = "lz";
        }
        //苏州
        else if ("0512".equals(cityCode)) {
            cityName = "su";
        }
        //太原
        else if ("0351".equals(cityCode)) {
            cityName = "ty";
        }
        //乌鲁木齐
        else if ("0991".equals(cityCode)) {
            cityName = "xj";
        }
        //中山
        else if ("0411".equals(cityCode)) {
            cityName = "zs";
        }
        //佛山
        else if ("0757".equals(cityCode)) {
            cityName = "fs";
        }
        //珠海
        else if ("0756".equals(cityCode)) {
            cityName = "zh";
        }
        //东莞
        else if ("0769".equals(cityCode)) {
            cityName = "dg";
        }
        //青岛
        else if ("0532".equals(cityCode)) {
            cityName = "qd";
        }
        //哈尔滨
        else if ("0451".equals(cityCode)) {
            cityName = "hrb";
        }
        //沈阳
        else if ("024".equals(cityCode)) {
            cityName = "sy";
        }
        //长春
        else if ("0431".equals(cityCode)) {
            cityName = "cc";
        }
        //大连
        else if ("0411".equals(cityCode)) {
            cityName = "dl";
        }
        //南昌
        else if ("0791".equals(cityCode)) {
            cityName = "nc";
        }
        //长沙
        else if ("0731".equals(cityCode)) {
            cityName = "cs";
        }
        //乐山
        else if ("0833".equals(cityCode)) {
            cityName = "ls";
        }
        //福州
        else if ("0591".equals(cityCode)) {
            cityName = "fz";
        }
        //厦门
        else if ("0592".equals(cityCode)) {
            cityName = "xm";
        }
        //宜宾
        else if ("0831".equals(cityCode)) {
            cityName = "yb";
        }
        //昆山
        else if ("0512".equals(cityCode)) {
            cityName = "ks";
        }
        //南宁
        else if ("0771".equals(cityCode)) {
            cityName = "nn";
        }
        //海南
        else if ("0898".equals(cityCode)) {
            cityName = "hn";
        }
        //无锡
        else if ("0510".equals(cityCode)) {
            cityName = "wx";
        }
        //石家庄
        else if ("0311".equals(cityCode)) {
            cityName = "sjz";
        }
        //宜春
        else if ("0795".equals(cityCode)) {
            cityName = "yc";
        }
        return cityName;
    }

    /**
     * CODE 1000
     * SYS_MSG 用户未登录
     * MSG 请检查输入信息的合法性
     */
    public static final Map<String, String> USER_NOT_LOGIN = new HashMap<String, String>() {
        {
            put(CODE, "1000");
            put(SYS_MSG, "用户未登录或token已失效");
            put(MSG, "请重新登录一下 哦!");
        }
    };
    /**
     * CODE 1001
     * SYS_MSG 系统找不到该用户
     * MSG 获取不到用户信息
     */
    public static final Map<String, String> USER_GET_NOT = new HashMap<String, String>() {
        {
            put(CODE, "1001");
            put(SYS_MSG, "系统找不到该用户");
            put(MSG, "获取不到用户信息!");
        }
    };
    /**
     * CODE 1002
     * SYS_MSG 用户已存在
     * MSG 用户已存在
     */
    public static final Map<String, String> USER_IN = new HashMap<String, String>() {
        {
            put(CODE, "1002");
            put(SYS_MSG, "用户已存在");
            put(MSG, "用户已存在哦!");
        }
    };
    /**
     * CODE 1003
     * SYS_MSG 输入用户名
     * MSG 请输入用户名
     */
    public static final Map<String, String> USER_NAME_NOT = new HashMap<String, String>() {
        {
            put(CODE, "1003");
            put(SYS_MSG, "输入用户名");
            put(MSG, "请输入用户名!");
        }
    };
    /**
     * CODE 1004
     * SYS_MSG 用户名错误
     * MSG 请检查用户名是否正确
     */
    public static final Map<String, String> USER_NAME_ERROR = new HashMap<String, String>() {
        {
            put(CODE, "1004");
            put(SYS_MSG, "用户名错误");
            put(MSG, "请检查用户名是否正确!");
        }
    };
    /**
     * CODE 1005
     * SYS_MSG 输入密码
     * MSG 请输入密码
     */
    public static final Map<String, String> USER_PASSWORD_NOT = new HashMap<String, String>() {
        {
            put(CODE, "1005");
            put(SYS_MSG, "输入密码");
            put(MSG, "请输入密码!");
        }
    };
    /**
     * CODE 1006
     * SYS_MSG 密码错误
     * MSG 请检查密码是否正确
     */
    public static final Map<String, String> USER_PASSWORD_ERROR = new HashMap<String, String>() {
        {
            put(CODE, "1006");
            put(SYS_MSG, "密码错误");
            put(MSG, "请检查密码是否正确!");
        }
    };
    /**
     * CODE 1007
     * SYS_MSG 用户名或密码错误
     * MSG 请检查用户名和密码是否正确
     */
    public static final Map<String, String> USER_NAME_AND_PWD_ERROR = new HashMap<String, String>() {
        {
            put(CODE, "1007");
            put(SYS_MSG, "用户名或密码错误");
            put(MSG, "请检查用户名和密码是否正确!");
        }
    };
    /**
     * CODE 1008
     * SYS_MSG 验证码不存在
     * MSG 请输入正确验证码
     */
    public static final Map<String, String> CODE_NOT = new HashMap<String, String>() {
        {
            put(CODE, "1008");
            put(SYS_MSG, "输入验证码");
            put(MSG, "请输入正确验证码!");
        }
    };
    /**
     * CODE 1009
     * SYS_MSG 用户未登录
     * MSG 请检查输入信息的合法性
     */
    public static final Map<String, String> CODE_ERROR = new HashMap<String, String>() {
        {
            put(CODE, "1009");
            put(SYS_MSG, "验证码错误");
            put(MSG, "请检验验证码是否正确!");
        }
    };
    /**
     * CODE 1010
     * SYS_MSG 注销成功
     * MSG 您已安全退出
     */
    public static final Map<String, String> USER_EXIT = new HashMap<String, String>() {
        {
            put(CODE, "1010");
            put(SYS_MSG, "注销成功");
            put(MSG, "您已安全退出!");
        }
    };

    /**
     * CODE 1011
     * SYS_MSG 用户已冻结
     * MSG 当前账号已冻结， 请联系管理员！
     */
    public static final Map<String, String> USER_FREEZE = new HashMap<String, String>() {
        {
            put(CODE, "1011");
            put(SYS_MSG, "用户已冻结");
            put(MSG, "当前账号已冻结， 请联系管理员！");
        }
    };
    /**
     * CODE 1012
     * SYS_MSG 用户待审核
     * MSG 用户待审核
     */
    public static final Map<String, String> USER_PENDING = new HashMap<String, String>() {
        {
            put(CODE, "1012");
            put(SYS_MSG, "用户待审核");
            put(MSG, "用户待审核");
        }
    };
    /**
     * CODE 1013
     * SYS_MSG 用户已删除
     * MSG 用户已删除
     */
    public static final Map<String, String> USER_DELETED = new HashMap<String, String>() {
        {
            put(CODE, "1013");
            put(SYS_MSG, "用户已删除");
            put(MSG, "用户已删除");
        }
    };
    /**
     * CODE 1014
     * SYS_MSG 用户已存在黑名单中
     * MSG 用户已存在黑名单中
     */
    public static final Map<String, String> USER_EXIST_BLACK_LIST = new HashMap<String, String>() {
        {
            put(CODE, "1013");
            put(SYS_MSG, "用户已存在黑名单中");
            put(MSG, "用户已存在黑名单中");
        }
    };

    /**
     * 财务模块关键字
     */
    public static final String FINANCE_MODULE_KEY = "BALANCE";


    /**
     * 去重
     */
    public static final String DISTINCT = "distinct";
}
