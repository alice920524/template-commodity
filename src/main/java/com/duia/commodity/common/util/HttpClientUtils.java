package com.duia.commodity.common.util;

import ch.qos.logback.classic.Logger;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by liuhao on 2016/9/27.
 */
public class HttpClientUtils {
    public static Logger logger = (Logger) LoggerFactory.getLogger(HttpClientUtils.class);

    private final static int TIME_OUT = 60000;

    public static String post(String url, Map<String, String> param) {
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(url);

        try {
            for (String key : param.keySet()) {
                String value = param.get(key);
                method.addParameter(key, value);
            }
            method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
            //使用系统提供的默认的恢复策略
            method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
            //设置超时的时间
            method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, TIME_OUT);
            int statusCode = client.executeMethod(method);

            if (statusCode == HttpStatus.SC_OK) {
                String res = method.getResponseBodyAsString();
                return res;
            }
        } catch (IllegalArgumentException e) {
            logger.error("非法的URL：{}", url);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            method.releaseConnection();
        }
        return null;
    }

    public static String get(String url) {
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(url);
        try {
            method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
            //使用系统提供的默认的恢复策略
            method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
            //设置超时的时间
            method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, TIME_OUT);
            int statusCode = client.executeMethod(method);

            if (statusCode == HttpStatus.SC_OK) {
                String res = method.getResponseBodyAsString();
                return res;
            }
        } catch (IllegalArgumentException e) {
            logger.error("非法的URL：{}", url);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            method.releaseConnection();
        }
        return null;
    }
}
