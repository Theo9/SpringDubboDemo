package com.zsc.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;


public class MigrateQYCredit {
    private static final Logger logger = LoggerFactory.getLogger(MigrateQYCredit.class);

    private static AbstractApplicationContext context;
    public static void main(String[] args){
        ClassPathResource resource = new ClassPathResource("Spring-context.xml");
        context = new GenericXmlApplicationContext(resource);
        context.registerShutdownHook();
        context.start();
        logger.info("SpringDubboDemo start--");
        while (true){
            try {
                Thread.sleep(3000);
            }catch (Exception e){
                logger.error("SpringDubboDemo start failed");
            }
        }
    }
}
