package com.lv.test;

import com.lv.cloud.etrack.client.eventlog.EventLogger;
import com.lv.cloud.etrack.client.eventlog.EventLoggerFactory;
import com.lv.cloud.kafka.annotation.EnableLvKafka;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author xiaoyulin
 * @description
 * @date 2019-10-14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApp.class)
@EnableLvKafka
public class EventLogSendTest {

    EventLogger logger = EventLoggerFactory.getLog(EventLogSendTest.class);

    @Test
    public void test(){
        logger.errorLogicEventLog("test-event", "test_error", 2222L, "ORDER",
                "测试测试", "error-405");
    }

}
