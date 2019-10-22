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

/**
 * @author xiaoyulin
 * @description
 * @date 2019-10-18
 */
@Configuration
@EnableLvKafka
@EnableConfigurationProperties(EventLoggerProperties.class)
public class EventLoggerConfig {

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
