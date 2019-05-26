package com.zsc.test;

import com.alibaba.fastjson.JSONObject;
import com.zsc.service.ExcelOperate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author:ShaochaoZhao
 * @Description:
 * @Date:Create in 20:04 2018/11/3
 * @Modified By:
 **/
public class QyTest extends BaseTest {

    @Autowired
    private ExcelOperate excelOperate;
    @Test
    public void test(){
        JSONObject param = new JSONObject();
        param.put("loanId","5f49abdf-8080-452d-a6d5-8d54eb81f537");
        param.put("type",8);
        String type = excelOperate.getyTypeByLoanId(param);
    }
}
