package com.lv.cloud.etrack.client.eventlog;

import java.util.HashMap;
import java.util.Map;

/**
 * 事件日志工厂
 * Created by xiaoyulin on 2017/8/15.
 */
public class EventLoggerFactory {

    private static Map<Class, EventLogger> orderLogMap = new HashMap<Class, EventLogger>();

    public static EventLogger getLog(Class clazz){
        EventLogger orderLog = orderLogMap.get(clazz);
        if(orderLog == null){
            orderLog = new EventLogger(clazz);
            orderLogMap.put(clazz, orderLog);
        }
        return orderLog;
    }
}
