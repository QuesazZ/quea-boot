package com.quesa.mybootproject.common.response;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

@JsonSerialize(include =  JsonSerialize.Inclusion.NON_NULL)
public class Response<T> implements Serializable {

    private String status;
    private String msg;
    private T data;

    private Response(String status){
        this.status = status;
    }
    private Response(String status,T data){
        this.status = status;
        this.data = data;
    }

    private Response(String status,String msg,T data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    private Response(String status,String msg){
        this.status = status;
        this.msg = msg;
    }

    @JsonIgnore
    //使之不在json序列化结果当中
    public boolean isSuccess(){
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    public String getStatus(){
        return status;
    }
    public T getData(){
        return data;
    }
    public String getMsg(){
        return msg;
    }


    public static <T> Response<T> success(){
        return new Response<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> Response<T> success(String msg){
        return new Response<T>(ResponseCode.SUCCESS.getCode(),msg);
    }

    public static <T> Response<T> success(T data){
        return new Response<T>(ResponseCode.SUCCESS.getCode(),data);
    }

    public static <T> Response<T> success(String msg,T data){
        return new Response<T>(ResponseCode.SUCCESS.getCode(),msg,data);
    }


    public static <T> Response<T> error(){
        return new Response<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getMsg());
    }


    public static <T> Response<T> error(String errorMessage){
        return new Response<T>(ResponseCode.ERROR.getCode(),errorMessage);
    }

    public static <T> Response<T> error(String errorCode,String errorMessage){
        return new Response<T>(errorCode,errorMessage);
    }

    public static <T> Response<T> error(T data){
        return new Response<T>(ResponseCode.ERROR.getCode(),data);
    }
    public static <T> Response<T> error(String errorCode,String errorMessage,T data){
        return new Response<T>(errorCode,errorMessage,data);
    }

}
