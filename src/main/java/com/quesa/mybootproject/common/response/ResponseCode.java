package com.quesa.mybootproject.common.response;

/**
 *
 */
public enum ResponseCode {

    SUCCESS("200","SUCCESS"),
    ERROR("500","ERROR"),
    NEED_LOGIN("99","NEED_LOGIN"),


    //=======黑名单的错误=====
    USER_EXIST("801","此用户已被移入黑名单"),
    //用户不存在
    USER_NOT_EXIST("802","用户不存在"),
    //员工已存在黑名单中,
    HAVA_EXIST_BLACK_LIST("803","员工已存在黑名单中"),
    //没有权限
    NOT_HAVE_PERMISSION("804","没有权限，无法操作"),
    //操作失败
    OPERATION_FAILURE("805","数据操作失败"),


    //===========校验字段=============
    //用户名已存在
    USERNAME_EXIST("901","该用户名已存在，请更改"),
    //编号已存在
    EMPLOYEE_NUM_EXIST("902","该编号已存在，请更改"),
    //手机号已存在
    PHONE_EXIST("903","该手机号已存在，请更改"),
    //姓名已存在
    NAME_EXIST("904","该姓名已存在，可以保存，但会有重名现象"),


    ILLEGAL_ARGUMENT("900","ILLEGAL_ARGUMENT");


    private final String code;
    private final String msg;


    ResponseCode(String code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public String getCode(){
        return code;
    }
    public String getMsg(){
        return msg;
    }

}
