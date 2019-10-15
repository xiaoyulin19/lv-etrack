package com.lv.test;

import com.lv.cloud.etrack.client.producer.EventLogKafkaTemplate;
import com.lv.cloud.kafka.annotation.EnableLvKafka;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * @author xiaoyulin
 * @description
 * @date 2019-10-14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApp.class)
@EnableLvKafka
public class EventLogSendTest {

    @Autowired
    private KafkaTemplate<Object, Object> kafkaTemplate;

    @Test
    public void test(){
        EventLogVo eventLogVo = new EventLogVo();
        eventLogVo.setAppName("lvcloud-test1");
        eventLogVo.setBussinessCode("test2");
        eventLogVo.setErrorCode("test1_error");
        eventLogVo.setBussinessId("33333");
        eventLogVo.setTag("ORDER2");
        eventLogVo.setMessage("测试2");
        eventLogVo.setDetail("error-测试2");
        eventLogVo.setCreateTime(new Date());
        kafkaTemplate.send(EventLogKafkaTemplate.TOPIC_NAME, eventLogVo);
    }

    public static class EventLogVo{
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
        private Date createTime;

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

        public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
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
                    '}';
        }
    }
}
