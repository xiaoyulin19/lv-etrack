package com.lv.cloud.etrack.client.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;

import java.io.Serializable;

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

    @Async("etraceAsyncExecutor")
    public void sendEventLog(EventLogVo eventLog){
        kafkaTemplate.send(TOPIC_NAME, eventLog);
    }

    public static class EventLogVo implements Serializable {

        /**
         * 模块编码
         */
        private String bussinessCode;

        /**
         * 错误码
         */
        private String errorCode;

        /**
         * 日志内容（概要）
         */
        private String message;

        /**
         * 日志内容（详细描述：Exception等）
         */
        private String detail;

        /**
         * 业务ID
         */
        private String bussinessId;

        /**
         * 业务标识
         */
        private String tag;

        /**
         * 堆栈信息
         */
        private String stackTrace;

        private String trackNumber;

        /**
         * 应用名
         */
        private String appName;

        /**
         * 创建时间
         */
        private Long createTime;

        /**
         * 日志级别：INFO，WARN，ERROR
         */
        private String level;

        /**
         * 日志类型(LOGIC/SYSTEM)
         */
        private String type;

        public String getBussinessCode() {
            return bussinessCode;
        }

        public void setBussinessCode(String bussinessCode) {
            this.bussinessCode = bussinessCode;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getBussinessId() {
            return bussinessId;
        }

        public void setBussinessId(String bussinessId) {
            this.bussinessId = bussinessId;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getStackTrace() {
            return stackTrace;
        }

        public void setStackTrace(String stackTrace) {
            this.stackTrace = stackTrace;
        }

        public String getTrackNumber() {
            return trackNumber;
        }

        public void setTrackNumber(String trackNumber) {
            this.trackNumber = trackNumber;
        }

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

        public Long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Long createTime) {
            this.createTime = createTime;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "EventLogVo{" +
                    "bussinessCode='" + bussinessCode + '\'' +
                    ", errorCode='" + errorCode + '\'' +
                    ", message='" + message + '\'' +
                    ", detail='" + detail + '\'' +
                    ", bussinessId='" + bussinessId + '\'' +
                    ", tag='" + tag + '\'' +
                    ", stackTrace='" + stackTrace + '\'' +
                    ", trackNumber='" + trackNumber + '\'' +
                    ", appName='" + appName + '\'' +
                    ", createTime=" + createTime +
                    ", level='" + level + '\'' +
                    ", type='" + type + '\'' +
                    '}';
        }
    }
}
