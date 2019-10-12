package com.quesa.mybootproject.common.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


/**
 * 远程 调用
 */
public class HttpClientUtil {


    /**
     * post 请求
     * targetUrl :请求路径
     * json 请求参数
     *
     * @return
     */
    public static JSONObject httpClientPost(String targetUrl, String json) {
        JSONObject jsonMessage = null; // 错误返回信息
        // 1.建立HttpClient对象
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(targetUrl);
        StringEntity entity = new StringEntity(json, "utf-8");//解决中文乱码问题
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        post.setEntity(entity);
        try {

            CloseableHttpResponse response = client.execute(post);
            int responseCode = response.getStatusLine().getStatusCode();
            if (responseCode == 200) {
                //得到服务器响应的消息对象
                HttpEntity httpentity = response.getEntity();
                JSONObject respcontext = JSONObject.parseObject(EntityUtils.toString(httpentity, "utf-8"));
                JSONObject resp = new JSONObject();
                if (respcontext != null && respcontext.getJSONObject("result") != null) {
                    return respcontext.getJSONObject("result");
                } else {
                    return respcontext.getJSONObject("status");
                }

            } else {
                jsonMessage = new JSONObject();
                jsonMessage.put("CODE", "203");
                jsonMessage.put("SYS_MSG", "请求金融端数据发生异常");
                jsonMessage.put("MSG", "网络开了一会小差，信息保存失败!code:204");
                System.out.println("响应失败!");
                return jsonMessage;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
