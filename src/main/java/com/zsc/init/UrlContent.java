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

    public void setMigrateQYCreditUrl(String migrateQYCreditUrl) {
        this.migrateQYCreditUrl = migrateQYCreditUrl;
    }
}
