package com.weiyiysw.http.utils;

import okhttp3.*;

import java.io.IOException;
import java.util.Map;

/**
 * @author yishiwei
 * @Date 2020/2/5
 */
public class OkHttpClientUtil {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    static OkHttpClient client = new OkHttpClient();

    public static void doGet(String baseUrl, Map<String, String> params) {
        Call call = generateGetCall(baseUrl, params);
        try {
            Response response = call.execute();
            // TODO: deal with response
            System.out.println(response.toString());
            response.close();
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    public static void doGetAsync(String baseUrl, Map<String, String> params) {
        Call call = generateGetCall(baseUrl, params);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.err.println("Get async err, " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.toString());
            }
        });
    }

    private static Call generateGetCall(String baseUrl, Map<String, String> params) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl).newBuilder();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
            }
        }

        String url = urlBuilder.build().toString();
        Request request = new Request.Builder().url(url).build();
        return client.newCall(request);
    }

    public void doPost(String baseUrl, String json) {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(baseUrl).post(body).build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response.toString());
            response.close();
        } catch (IOException e) {

        }
    }

    public void doPostForm(String baseUrl, Map<String, String> formData) {
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : formData.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        RequestBody body = builder.build();

        Request request = new Request.Builder().url(baseUrl).post(body).build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response.toString());
            response.close();
        } catch (IOException e) {

        }
    }
}
