package com.zsc.init;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UrlContent {

    @Value("${migrateQYCredit.url}")
    private String migrateQYCreditUrl;

    public String getMigrateQYCreditUrl() {
        return migrateQYCreditUrl;
    }

    @Value("${qy.type.url}")
    private String qyTypeUrl;

    @Value("${company.register.url}")
    private String companyRegisterInfoUrl;
    @Value("${userinfo.ifcert.url}")
    private String ifcertUserInfoUrl;

    public void setMigrateQYCreditUrl(String migrateQYCreditUrl) {
        this.migrateQYCreditUrl = migrateQYCreditUrl;
    }

    public String getQyTypeUrl() {
        return qyTypeUrl;
    }

    public void setQyTypeUrl(String qyTypeUrl) {
        this.qyTypeUrl = qyTypeUrl;
    }

    public String getCompanyRegisterInfoUrl() {
        return companyRegisterInfoUrl;
    }

    public void setCompanyRegisterInfoUrl(String companyRegisterInfoUrl) {
        this.companyRegisterInfoUrl = companyRegisterInfoUrl;
    }

    public String getIfcertUserInfoUrl() {
        return ifcertUserInfoUrl;
    }

    public void setIfcertUserInfoUrl(String ifcertUserInfoUrl) {
        this.ifcertUserInfoUrl = ifcertUserInfoUrl;
    }
}
