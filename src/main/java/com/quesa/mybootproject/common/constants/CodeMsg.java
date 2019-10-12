package com.quesa.mybootproject.common.constants;


import com.quesa.mybootproject.common.util.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统自定义消息，状态码请保证在1000以上
 *
 * @author
 */
public class CodeMsg extends Constants {
    // ---------------------------------------用户信息定义(1000-1100)-------------------------
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
    public static final Map<String, String> USER_NAME_and_PWD_ERROR = new HashMap<String, String>() {
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
}
