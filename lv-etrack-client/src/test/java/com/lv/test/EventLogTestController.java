package com.lv.test;

import com.lv.cloud.etrack.client.eventlog.EventLogger;
import com.lv.cloud.etrack.client.eventlog.EventLoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaoyulin
 * @description
 * @date 2019-10-23
 */
@RestController
public class EventLogTestController {

    EventLogger logger = EventLoggerFactory.getLog(EventLogTestController.class);

    @GetMapping(path = "/send/event/{what}")
    public void sendFoo(@PathVariable String what) {
        logger.errorLogicLog("test-event", "test_error", 3211L, "ORDER",
                "事件:"+what, "error-403");
        logger.outputSystemLog("test-event", "test_error","事件:"+what, "error-403",
                new Exception("test ex"), 3211L, "ORDER");
    }
}
