package com.quesa.mybootproject.common.util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 *
 */
public class HttpHelper {
    /**
     * 获取请求Body
     *
     * @param request
     * @return
     */
    public static String getBodyString(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = request.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String argParam = sb.toString();
        String isEncrypt = request.getHeader("isEncrypt");//是否加密：1：是，0：否
        if ("1".equals(isEncrypt)) {
            //前端加密，后端解码
            if (sb != null) {
                try {
                    argParam = Base64Encrypt.decodeSalt(argParam, request.getHeader(Constants.TOKEN), request.getHeader("timeStamp"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return argParam;
    }

    public static String getBodyString(BufferedReader br) {
        String inputLine;
        String str = "";
        try {
            while ((inputLine = br.readLine()) != null) {
                str += inputLine;
            }
            br.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        return str;
    }
}
