package com.quesa.mybootproject.common.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
@ConfigurationProperties(prefix = "signkey")
@Data
public class SignKeyUitl {

    private String apartment;
    private String finance;

    private String apartmentURL;
    private String financeURL;

    /*
    * 服务方判断请求方的签名是否支持获取数据
    * signkeydata 请求方生成的签名
    * key 为当前对象的成员变量
    * params 为 请求方的params对应的json 业务数据
    * */
    public static boolean checkDateKey(String signkeydata, String key, String params) {
        return signkeydata.equals(JCEDigest.toMD5(key + params));
    }

    /*
    * 请求方生成key
    * */
    public static String generateDateKey(String key, String params) {
        return JCEDigest.toMD5(key + params);
    }


    /*public void setApartmentURL(String apartmentURL){
        this.apartmentURL=apartmentURL;
    }
    public String getApartmentURL(){
        return apartmentURL;
    }*/
}
