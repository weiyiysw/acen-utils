package com.weiyiysw.http.utils;

import org.junit.Test;

/**
 * @author yishiwei
 * @Date 2020/2/5
 */
public class OkHttpClientUtilTest {

    @Test
    public void doGet() {
        String baseUrl = "https://cn.bing.com";
        OkHttpClientUtil.doGet(baseUrl, null);
    }
}