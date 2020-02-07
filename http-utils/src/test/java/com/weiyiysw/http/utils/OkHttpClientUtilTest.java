package com.weiyiysw.http.utils;

import org.junit.Test;

/**
 * @author yishiwei
 * @Date 2020/2/5
 */
public class OkHttpClientUtilTest {

    @Test
    public void doGet() {
        String baseUrl = "https://cn.bing.com/";
        OkHttpClientUtil.doGet(baseUrl, null);
    }

    @Test
    public void doGetAsync() throws Exception {
        String baseUrl = "https://cn.bing.com/";
        OkHttpClientUtil.doGetAsync(baseUrl, null);
        // 等待异步请求结束
        Thread.sleep(2000);
    }

    @Test
    public void doPost() {

    }

    @Test
    public void doPostForm() {

    }
}