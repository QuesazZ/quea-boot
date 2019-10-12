package com.quesa.mybootproject.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统常量定义
 *
 */
@SuppressWarnings("serial")
public class Constants {
    /**
     * 插入参数封装data
     */
    public static final String DATA = "data";
    /**
     * 插入参数封装子参数 params
     */
    public static final String PUBLICTYPE = "publicType";

    /**
     * 插入参数封装子参数 params
     */
    public static final String PARAMS = "params";

    /**
     * 用戶ID
     */
    public static final String USERID = "userid";

    /**
     * 用戶token
     */
    public static final String TOKEN = "token";
    /**
     * 专属通道
     */
    public static final String HEADER = "header";
    /**
     * 用户是否登录
     */
    public static final String ISLOGIN = "isLogin";
    /**
     * 请求方法
     */
    public static final String METHOD = "method";
    /**
     * 请求设备来源
     */
    public static final String USERAGENT = "userAgent";
    /**
     * 返回结果result
     */
    public static final String RESULT = "result";
    /**
     * 返回结果status
     */
    public static final String STATUS = "status";
    /**
     * 消息对应CODE
     */
    public static final String CODE = "code";
    /**
     * 系统显示消息
     */
    public static final String SYS_MSG = "sys_msg";
    /**
     * 前端显示消息
     */
    public static final String MSG = "msg";
    /**
     * 请求异常分割符
     */
    public static final String EXCEPT_SPLIT = "requestException";
    /**
     * 上次请求地址
     */
    public static final String PREREQUEST = "PREREQUEST";
    /**
     * 上次请求时间
     */
    public static final String PREREQUEST_TIME = "PREREQUEST_TIME";
    /**
     * 非法请求次数
     */
    public static final String MALICIOUS_REQUEST_TIMES = "MALICIOUS_REQUEST_TIMES";
    /**
     * 缓存命名空间
     */
    public static final String CACHE_NAMESPACE = "quesa";

    // -------------------------------------系統常量（1-1000）-------------------------------------
    /**
     * CODE 200 SYS_MSG 操作成功 MSG 操作成功
     */
    public static Map<String, String> SYSTEM_SUCCESS;

    static {
        if (SYSTEM_SUCCESS == null) {
            SYSTEM_SUCCESS = new HashMap<String, String>(3) {
                {
                    put(CODE, "200");
                    put(SYS_MSG, "操作成功!");
                    put(MSG, "操作成功!");
                }
            };
        }
    }

    /**
     * CODE 201 SYS_MSG 系统获取参数异常 MSG 网络开了一会小差，获取不到信息
     */
    public static Map<String, String> SYSTEM_GETPARAMETER;

    static {
        if (SYSTEM_GETPARAMETER == null) {
            SYSTEM_GETPARAMETER = new HashMap<String, String>(3) {
                {
                    put(CODE, "201");
                    put(SYS_MSG, "系统获取参数异常!");
                    put(MSG, "网络开了一会小差，获取不到信息!code:201");
                }
            };
        }
    }

    /**
     * CODE 202 SYS_MSG 系统获取参数信息不能为空 MSG 请完善需要填写的信息
     */
    public static Map<String, String> SYSTEM_GETPARAMETER_ISNULL;

    static {
        if (SYSTEM_GETPARAMETER_ISNULL == null) {
            SYSTEM_GETPARAMETER_ISNULL = new HashMap<String, String>(3) {
                {
                    put(CODE, "202");
                    put(SYS_MSG, "系统获取参数不能为空!");
                    put(MSG, "请完善需要填写的信息!code:202");
                }
            };
        }
    }

    /**
     * CODE 203 SYS_MSG 系统获取数据发生异常 MSG 网络开了一会小差，未获取到想要信息
     */
    public static Map<String, String> SYSTEM_GET_DATA_ERROR;

    static {
        if (SYSTEM_GET_DATA_ERROR == null) {
            SYSTEM_GET_DATA_ERROR = new HashMap<String, String>(3) {
                {
                    put(CODE, "203");
                    put(SYS_MSG, "系统获取数据发生异常");
                    put(MSG, "网络开了一会小差，未获取到想要信息!code:203");
                }
            };
        }
    }

    /**
     * CODE 204 SYS_MSG 系统保存数据时发生异常 MSG 网络开了一会小差，信息保存失败
     */
    public static Map<String, String> SYSTEM_ADD_DATA_ERROR;

    static {
        if (SYSTEM_ADD_DATA_ERROR == null) {
            SYSTEM_ADD_DATA_ERROR = new HashMap<String, String>(3) {
                {
                    put(CODE, "204");
                    put(SYS_MSG, "系统保存数据时发生异常");
                    put(MSG, "网络开了一会小差，信息保存失败!code:204");
                }
            };
        }
    }

    /**
     * CODE 205 SYS_MSG 系统删除数据时发生异常 MSG 网络开了一会小差，信息删除失败
     */
    public static Map<String, String> SYSTEM_DELETE_DATA_ERROR;

    static {
        if (SYSTEM_DELETE_DATA_ERROR == null) {
            SYSTEM_DELETE_DATA_ERROR = new HashMap<String, String>(3) {
                {
                    put(CODE, "205");
                    put(SYS_MSG, "系统删除数据时发生异常");
                    put(MSG, "网络开了一会小差，信息删除失败!code:205");
                }
            };
        }
    }

    /**
     * CODE 206 SYS_MSG 系统修改数据时发生异常 MSG 网络开了一会小差，信息修改失败
     */
    public static Map<String, String> SYSTEM_UPDATE_DATA_ERROR;

    static {
        if (SYSTEM_UPDATE_DATA_ERROR == null) {
            SYSTEM_UPDATE_DATA_ERROR = new HashMap<String, String>(3) {
                {
                    put(CODE, "206");
                    put(SYS_MSG, "系统修改数据时发生异常");
                    put(MSG, "网络开了一会小差，信息修改失败!code:206");
                }
            };
        }
    }

    /**
     * CODE 208 SYS_MSG 系统新增数据时发现数据已存在 MSG 您需要保存的信息已存在哦
     */
    public static Map<String, String> SYSTEM_ADD_REPEAT_DATA_ERROR;

    static {
        if (SYSTEM_ADD_REPEAT_DATA_ERROR == null) {
            SYSTEM_ADD_REPEAT_DATA_ERROR = new HashMap<String, String>(3) {
                {
                    put(CODE, "207");
                    put(SYS_MSG, "系统新增数据时发现数据已存在");
                    put(MSG, "您需要保存的信息已存在哦!");
                }
            };
        }

    }

    /**
     * 查询数据为空提示
     */
    public static Map<String, String> SYSTEM_GET_DATA_ISNULL;

    static {
        if (SYSTEM_GET_DATA_ISNULL == null) {
            SYSTEM_GET_DATA_ISNULL = new HashMap<String, String>(3) {
                {
                    put(CODE, "208");
                    put(SYS_MSG, "获取数据为空！");
                    put(MSG, "没有查询到信息！");
                }
            };
        }

    }

    /**
     * CODE 301 SYS_MSG 系统上传文件时发生异常 MSG 网络开了一会小差，文件保存失败
     */
    public static final Map<String, String> SYSTEM_UPLOAD_DATA_ERROR = new HashMap<String, String>(3) {
        {
            put(CODE, "301");
            put(SYS_MSG, "系统上传文件时发生异常");
            put(MSG, "网络开了一会小差，文件保存失败!code:301");
        }
    };
    /**
     * CODE 310 SYS_MSG 系统导入Excel数据时发生异常 MSG 网络开了一会小差，导入Excel数据失败
     */
    public static final Map<String, String> SYSTEM_IMP_EXCEL_ERROR = new HashMap<String, String>(3) {
        {
            put(CODE, "310");
            put(SYS_MSG, "系统导入Excel数据时发生异常");
            put(MSG, "网络开了一会小差，导入Excel数据失败!code:310");
        }
    };
    /**
     * CODE 311 SYS_MSG 系统导出Excel数据时发生异常 MSG 网络开了一会小差，导出Excel数据失败
     */
    public static final Map<String, String> SYSTEM_EXP_EXCEL_ERROR = new HashMap<String, String>(3) {
        {
            put(CODE, "311");
            put(SYS_MSG, "系统导出Excel数据时发生异常!");
            put(MSG, "网络开了一会小差，导出Excel数据失败!code:311");
        }
    };
    /**
     * CODE 320 SYS_MSG 系统打印数据时发生异常 MSG 网络开了一会小差，打印数据失败
     */
    public static final Map<String, String> SYSTEM_PRINT_ERROR = new HashMap<String, String>(3) {
        {
            put(CODE, "320");
            put(SYS_MSG, "系统打印数据时发生异常!");
            put(MSG, "网络开了一会小差，打印数据失败!code:320");
        }
    };
    /**
     * CODE 404 SYS_MSG 未知系统错误 MSG 网络异常
     */
    public static final Map<String, String> SYSTEM_UNKNOWN_ERROR = new HashMap<String, String>(3) {
        {
            put(CODE, "404");
            put(SYS_MSG, "未知系统错误");
            put(MSG, "网络异常!code:404");
        }
    };
    /**
     * CODE 405 SYS_MSG 系統找不到指定方法 MSG 网络异常
     */
    public static final Map<String, String> SYSTEM_UNKNOWN_METHOD_ERROR = new HashMap<String, String>(3) {
        {
            put(CODE, "405");
            put(SYS_MSG, "系統找不到指定方法");
            put(MSG, "网络异常!code:405");
        }
    };
    // ---------------------------------------转换异常(100-199)-------------------------------
    /**
     * CODE 100 SYS_MSG 系统参数未知类型转换发生异常 MSG 请检查输入信息的合法性
     */
    public static final Map<String, String> SYSTEM_CONVERT_UNKNOWN = new HashMap<String, String>(3) {
        {
            put(CODE, "100");
            put(SYS_MSG, "系统参数未知类型转换发生异常!");
            put(MSG, "请检查输入信息的合法性!");
        }
    };
    /**
     * CODE 101 SYS_MSG 系统参数转换int类型发生异常 MSG 请检查输入信息的合法性!
     */
    public static final Map<String, String> SYSTEM_CONVERT_INT = new HashMap<String, String>(3) {
        {
            put(CODE, "101");
            put(SYS_MSG, "系统参数转换int类型发生异常!");
            put(MSG, "请检查输入信息的合法性!");
        }
    };
    /**
     * CODE 102 SYS_MSG 参数转换Long类型发生异常 MSG 请检查输入信息的合法性
     */
    public static final Map<String, String> SYSTEM_CONVERT_LONG = new HashMap<String, String>(3) {
        {
            put(CODE, "102");
            put(SYS_MSG, "系统参数转换Long类型发生异常!");
            put(MSG, "请检查输入信息的合法性!");
        }
    };
    /**
     * CODE 103 SYS_MSG 参数转换float类型发生异常 MSG 请检查输入信息的合法性
     */
    public static final Map<String, String> SYSTEM_CONVERT_FLOAT = new HashMap<String, String>(3) {
        {
            put(CODE, "103");
            put(SYS_MSG, "系统参数转换float类型发生异常!");
            put(MSG, "请检查输入信息的合法性!");
        }
    };
    /**
     * CODE 104 SYS_MSG 参数转换double类型发生异常 MSG 请检查输入信息的合法性
     */
    public static final Map<String, String> SYSTEM_CONVERT_DOUBLE = new HashMap<String, String>(3) {
        {
            put(CODE, "104");
            put(SYS_MSG, "系统参数转换double类型发生异常!");
            put(MSG, "请检查输入信息的合法性!");
        }
    };
    /**
     * CODE 105 SYS_MSG 参数转换char类型发生异常 MSG 请检查输入信息的合法性
     */
    public static final Map<String, String> SYSTEM_CONVERT_CHAR = new HashMap<String, String>(3) {
        {
            put(CODE, "105");
            put(SYS_MSG, "系统参数转换char类型发生异常!");
            put(MSG, "请检查输入信息的合法性!");
        }
    };
    /**
     * CODE 106 SYS_MSG 参数转换byte类型发生异常 MSG 请检查输入信息的合法性
     */
    public static final Map<String, String> SYSTEM_CONVERT_BYTE = new HashMap<String, String>(3) {
        {
            put(CODE, "106");
            put(SYS_MSG, "系统参数转换byte类型发生异常!");
            put(MSG, "请检查输入信息的合法性!");
        }
    };
    /**
     * CODE 107 SYS_MSG 参数转换short类型发生异常 MSG 请检查输入信息的合法性
     */
    public static final Map<String, String> SYSTEM_CONVERT_SHORT = new HashMap<String, String>(3) {
        {
            put(CODE, "107");
            put(SYS_MSG, "系统参数转换short类型发生异常!");
            put(MSG, "请检查输入信息的合法性!");
        }
    };
    /**
     * CODE 108 SYS_MSG 参数转换date类型发生异常 MSG 请检查输入信息的合法性
     */
    public static final Map<String, String> SYSTEM_CONVERT_DATE = new HashMap<String, String>(3) {
        {
            put(CODE, "108");
            put(SYS_MSG, "系统参数转换date类型发生异常!");
            put(MSG, "请检查输入信息的合法性!");
        }
    };
    /**
     * CODE 109 SYS_MSG 参数转换BigDecimal类型发生异常 MSG 请检查输入信息的合法性
     */
    public static final Map<String, String> SYSTEM_CONVERT_BIGDECIMAL = new HashMap<String, String>(3) {
        {
            put(CODE, "108");
            put(SYS_MSG, "系统参数转换BigDecimal类型发生异常!");
            put(MSG, "请检查输入信息的合法性!");
        }
    };
    // ---------------------------------------自定义消息(1-99)-------------------------------

    /**
     * 自定义状态提示信息
     *
     * @param code 状态码(1-99)之间
     * @param msg
     * @return
     */
    public static Map<String, String> setMsg(String code, String msg) {
        Map<String, String> map = new HashMap<String, String>(2);
        map.put(CODE, code);
        map.put(MSG, msg);
        return map;
    }

    /**
     * 自定义异常提示信息
     *
     * @param msg
     * @return
     */
    public static String setThrowableMsg(String msg) {
        return "88" + Constants.EXCEPT_SPLIT + msg;
    }

    /**
     * 自定义状态提示信息
     *
     * @param msg 状态码(99)
     * @param msg
     * @return
     */
    public static Map<String, String> setMsg(String msg) {
        Map<String, String> map = new HashMap<String, String>(2);
        map.put(CODE, "99");
        map.put(MSG, msg);
        return map;
    }

    /**
     * 自定义状态提示信息
     *
     * @param code
     * @param msg
     * @return
     */
    public static Map<String, String> setBackMsg(String code, String msg) {
        Map<String, String> map = new HashMap<String, String>(2);
        map.put(CODE, code);
        map.put(MSG, msg);
        return map;
    }


}
