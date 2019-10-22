package com.lv.cloud.etrack.client.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(EventLoggerConfig.class)
public @interface EnableEventLogger {
}
