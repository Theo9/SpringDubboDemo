package com.zsc.common;

import okhttp3.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class OkHttpClientComponet {
    /**
     * 连接池
     */
    private ConnectionPool connectionPool;
    /**
     * 客户端
     */
    private OkHttpClient okHttpClient;

    @PostConstruct
    private void init(){
        connectionPool = new ConnectionPool(20,10, TimeUnit.MINUTES);
        okHttpClient = new OkHttpClient()
                .newBuilder()
                .connectionPool(connectionPool)
                .readTimeout(15,TimeUnit.MINUTES).writeTimeout(15,TimeUnit.MINUTES)
                .connectTimeout(15,TimeUnit.MINUTES).build();
    }

    public Response postReturnResponse(String url, String params, Map<String,String> headers) throws IOException {
        Headers requestHeaders = null;

        if(null != headers){
            requestHeaders = Headers.of(headers);
        }

        MediaType mediaType = MediaType.parse("application/json");

        RequestBody body = RequestBody.create(mediaType,params);

        Request request;
        Request.Builder builder = new Request.Builder().url(url).post(body).addHeader("content-type","application/json");
        if(requestHeaders != null){
            builder.headers(requestHeaders);
        }

        request = builder.build();

        return okHttpClient.newCall(request).execute();
    }



    public Response getReturnRepsponse(String url,Map<String,String> headers) throws IOException {
        Headers requestHeaders = null;

        if(null != headers){
            requestHeaders = Headers.of(headers);
        }
        Request request;
        Request.Builder builder = new Request.Builder().url(url).get();

        if(requestHeaders != null){
            builder.headers(requestHeaders);
        }

        request = builder.build();

        return okHttpClient.newCall(request).execute();
    }

}
