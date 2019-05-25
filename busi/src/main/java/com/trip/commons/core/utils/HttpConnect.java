package com.trip.commons.core.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Http连接工具
 *
 * @author fqh
 * @create 2016-12-20 16:35
 */
public class HttpConnect {

    private String charset = "UTF-8";

    private RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(15000)
            .setConnectTimeout(15000)
            .setConnectionRequestTimeout(15000)
            .build();

    /**
     * 发送POST请求
     * @param url
     * @param params
     * @return
     */
    public String post(String url, String params, String contentType) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        StringEntity stringEntity = new StringEntity(params, charset);
        stringEntity.setContentType(contentType);
        httpPost.setEntity(stringEntity);
        return post(httpPost);
    }

    public String post(String url, String params) throws Exception {
        return post(url, params, "text/plain");
    }

    /**
     * 发送POST请求
     * @param url
     * @param maps
     * @return
     * @throws Exception
     */
    public String post(String url, Map<String, String> maps) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        for (String key : maps.keySet()) {
            nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, charset));
        return post(httpPost);
    }

    private String post(HttpPost httpPost) throws Exception {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try {
            // 创建默认的httpClient实例.
            httpClient = HttpClients.createDefault();
            httpPost.setConfig(requestConfig);
            // 执行请求
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, charset);
        } finally {
            try {
                // 关闭连接,释放资源
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseContent;
    }

    /**
     * 发起GET请求
     * @param url
     * @return
     * @throws Exception
     */
    private String get(String url) throws Exception {
        HttpGet httpGet = new HttpGet(url);
        return get(httpGet);
    }

    private String get(HttpGet httpGet) throws Exception {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try {
            // 创建默认的httpClient实例.
            httpClient = HttpClients.createDefault();
            httpGet.setConfig(requestConfig);
            // 执行请求
            response = httpClient.execute(httpGet);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        } finally {
            try {
                // 关闭连接,释放资源
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseContent;
    }

}
