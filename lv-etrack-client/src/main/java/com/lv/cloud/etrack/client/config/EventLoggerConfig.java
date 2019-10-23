package com.lv.cloud.etrack.client.config;

import com.lv.cloud.etrack.client.context.ETrackContext;
import com.lv.cloud.etrack.client.eventlog.EventLoggerFactory;
import com.lv.cloud.etrack.client.producer.EventLogKafkaTemplate;
import com.lv.cloud.kafka.annotation.EnableLvKafka;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author xiaoyulin
 * @description
 * @date 2019-10-18
 */
@Configuration
@EnableAsync
@EnableLvKafka
@EnableConfigurationProperties(EventLoggerProperties.class)
public class EventLoggerConfig {

    /** Set the ThreadPoolExecutor's core pool size. */
    private int corePoolSize = 10;
    /** Set the ThreadPoolExecutor's maximum pool size. */
    private int maxPoolSize = 100;
    /** Set the capacity for the ThreadPoolExecutor's BlockingQueue. */
    private int queueCapacity = 300;

    @Bean("etraceAsyncExecutor")
    public Executor etraceAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setThreadNamePrefix("ETraceAsync-");
        executor.initialize();
        return executor;
    }

    @Bean
    @ConditionalOnMissingBean
    public EventLogKafkaTemplate eventLogKafkaTemplate(KafkaTemplate<Object, Object> kafkaTemplate,
                                                       EventLoggerProperties eventLoggerProperties){
        ETrackContext.appName = eventLoggerProperties.getAppname();
        EventLogKafkaTemplate eventLogKafkaTemplate = new EventLogKafkaTemplate(kafkaTemplate);
        return eventLogKafkaTemplate;
    }

    @Bean
    @ConditionalOnBean(EventLogKafkaTemplate.class)
    @ConditionalOnMissingBean
    public EventLoggerFactory eventLoggerFactory(EventLogKafkaTemplate eventLogKafkaTemplate){
        EventLoggerFactory eventLoggerFactory = new EventLoggerFactory(eventLogKafkaTemplate);
        return eventLoggerFactory;
    }
}
