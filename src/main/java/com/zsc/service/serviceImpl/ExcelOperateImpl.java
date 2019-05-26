package com.zsc.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zsc.common.OkHttpClientComponet;
import com.zsc.init.UrlContent;
import com.zsc.service.ExcelOperate;
import okhttp3.*;
import okhttp3.internal.http2.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;

@Service
public class ExcelOperateImpl implements ExcelOperate {
    @Autowired
    private OkHttpClientComponet okHttpClientComponet;
    @Autowired
    private UrlContent urlContent;

    public String migrateQYCreditData(JSONObject object) {
        Response response = null;
        try{
            String url = urlContent.getMigrateQYCreditUrl();
            String param = object.toString();
            response = okHttpClientComponet.postReturnResponse(url,param,null);
            JSONObject jsonObject = JSONObject.parseObject(response.body().string(),JSONObject.class);
            return jsonObject.getString("code");
        }catch (Exception e){
            e.printStackTrace();
            return "HTTP_ERROR";
        }
    }

    @Override
    public String getyTypeByLoanId(JSONObject object) {
        Response response = null;
        try{
            String url = urlContent.getQyTypeUrl();
            String param = object.toString();
            response = okHttpClientComponet.postReturnResponse(url,param,null);
            JSONObject result = JSONObject.parseObject(response.body().string(),JSONObject.class);
            if(true == result.getBoolean("successful") || null != result.getString("items")){
                List<JSONObject> items = JSON.parseArray(result.getString("items"),JSONObject.class);
                if(CollectionUtils.isEmpty(items)){
                    return null;
                }else{
                    return items.get(0).getString("type");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return "HTTP_ERROR";
        }
        return null;
    }

    @Override
    public String saveCompanyRegisterInfo(JSONObject object) {
        Response response = null;
        try{
            String url = urlContent.getCompanyRegisterInfoUrl();
            String param = object.toString();
            response = okHttpClientComponet.postReturnResponse(url,param,null);
            JSONObject result = JSONObject.parseObject(response.body().string(),JSONObject.class);
            if(!"true".equals(result.getString("successful"))){
               return result.getString("code");
            }
        }catch (Exception e){
            e.printStackTrace();
            return "HTTP_ERROR";
        }
        return "10000";
    }

    @Override
    public String userInfo(Integer id) {
        Response response = null;
        try{
            String url = urlContent.getIfcertUserInfoUrl();
            JSONObject param = new JSONObject();
            param.put("id",id);
            response = okHttpClientComponet.postReturnResponse(url, param.toString(),null);
            JSONObject result = JSONObject.parseObject(response.body().string(),JSONObject.class);
            if(!"10000".equals(result.getString("code"))){
                return result.getString("code");
            }
        }catch (Exception e){
            e.printStackTrace();
            return "HTTP_ERROR";
        }
        return "10000";
    }

    @Override
    public String checkUserById(String userId) {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "userId="+userId);
        Request request = new Request.Builder()
                .url("http://pushplatform.kaiyuan.net/pushplatform/ifcert/checkUserInfo")
                .post(body)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "f763b49f-8b4b-83d8-aa83-62b1b2db04cb")
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            JSONObject result = JSONObject.parseObject(response.body().string(),JSONObject.class);
            if(!"10000".equals(result.getString("code"))){
                System.out.println("错误userId="+userId);
                return result.getString("code");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "10000";
    }
}
