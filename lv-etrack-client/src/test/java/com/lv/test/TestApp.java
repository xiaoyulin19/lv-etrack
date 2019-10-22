package com.lv.test;

import com.lv.cloud.etrack.client.config.EnableEventLogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author xiaoyulin
 * @description
 * @date 2019-10-14
 */
@SpringBootApplication
@ServletComponentScan
@EnableEventLogger
public class TestApp {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TestApp.class, args);
    }
}
