package com.lv.cloud.etrack.client.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xiaoyulin
 * @description
 * @date 2019-10-22
 */
@ConfigurationProperties(prefix = "lvcloud.etrack")
public class EventLoggerProperties {

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    private String appname;


}
