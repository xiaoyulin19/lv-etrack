package com.lv.cloud.etrack.client.producer;

import org.springframework.kafka.core.KafkaTemplate;

/**
 * @author xiaoyulin
 * @description 事件日志kafka消息推送
 * @date 2019-10-12
 */
public class EventLogKafkaTemplate {

    public final static String TOPIC_NAME = "topic-lv.event.log";

    private KafkaTemplate<Object, Object> kafkaTemplate;

    public EventLogKafkaTemplate(KafkaTemplate<Object, Object> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEventLog(String eventLog){
        kafkaTemplate.send(TOPIC_NAME, eventLog);
    }
}
