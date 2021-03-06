package com.api;

import com.api.util.SpringContextUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;

/**
 * @author czy-2020727
 */
@EnableConfigurationProperties
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class MocoApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MocoApplication.class, args);
        SpringContextUtil.setApplicationContext(context);
    }
}
