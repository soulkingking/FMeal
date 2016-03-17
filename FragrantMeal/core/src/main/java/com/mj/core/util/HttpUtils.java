package com.mj.core.util;

import android.content.Context;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.webkit.CookieManager;
import com.loopj.android.http.*;

import org.apache.http.Header;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;

import java.util.*;
import java.util.Map.Entry;

/**
 * Android http访问公共类.
 * User: bijingzhou
 * Date: 14-3-10
 * Time: 上午11:05
 */
public class HttpUtils {
    /**
     * 向指定URL发送GET方法的请求
     * @param url  发送请求的URL
     * @return URL 所代表远程资源的响应结果
     */
    public static void AsyncSendGet(String url, Context context, TextHttpResponseHandler textHandler) {
        final StringBuffer resultData = new StringBuffer();
        AsyncHttpClient asyncClient = AsyncHttpClientUtil.getInstance();
        asyncClient.get(url, textHandler);
    }


    /**
     * 向指定URL发送POST方法的请求
     * @param url  发送请求的URL
     * @return URL 所代表远程资源的响应结果
     */
    public static void AsyncSendPost(String url, Context context, Map<String, String> param, TextHttpResponseHandler JsonHandler) {
        AsyncHttpClient asyncClient = AsyncHttpClientUtil.getInstance();
        PersistentCookieStore myCookieStore = new PersistentCookieStore(context);

        RequestParams postParams = new RequestParams();
        for (Entry<String, String> e : param.entrySet()) {
            postParams.add(e.getKey(), e.getValue());
        }

        asyncClient.post(url, postParams, JsonHandler);
    }

    /**
     * 向指定URL发送POST方法的请求
     * @param url  发送请求的URL
     * @return URL 所代表远程资源的响应结果
     */
    public static void AsyncPostJson(String url, Context context, StringEntity param, JsonHttpResponseHandler JsonHandler) {
        AsyncHttpClient asyncClient = AsyncHttpClientUtil.getInstance();
        asyncClient.post(context, url, param, "application/json", JsonHandler);
    }


}

class AsyncHttpClientUtil {
    private static AsyncHttpClient client = null;
    public synchronized static AsyncHttpClient getInstance(){
        if(client == null){
            client = new AsyncHttpClient();
            client.setTimeout(5000);
        }
        return client;
    }
}
