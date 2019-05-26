package com.zsc.service;

import com.alibaba.fastjson.JSONObject;

public interface ExcelOperate {

    String migrateQYCreditData(JSONObject object);

    String getyTypeByLoanId(JSONObject object);

    String saveCompanyRegisterInfo(JSONObject object);

    String userInfo(Integer id);

    String checkUserById(String userId);
}
