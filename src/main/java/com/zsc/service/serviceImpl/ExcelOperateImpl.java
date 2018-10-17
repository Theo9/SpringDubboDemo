package com.zsc.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.zsc.common.OkHttpClientComponet;
import com.zsc.init.UrlContent;
import com.zsc.service.ExcelOperate;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExcelOperateImpl implements ExcelOperate {
    @Autowired
    private OkHttpClientComponet okHttpClientComponet;
    @Autowired
    private UrlContent urlContent;

    public String migrateQYCreditData(JSONObject object) {
        Response response = null;
        try{
            response = okHttpClientComponet.postReturnResponse(urlContent.getMigrateQYCreditUrl(),JSONObject.toJSONString(object),null);
            JSONObject jsonObject = JSONObject.parseObject(response.body().string(),JSONObject.class);
            return jsonObject.getString("code");
        }catch (Exception e){
            e.printStackTrace();
            return "HTTP_ERROR";
        }
    }
}
