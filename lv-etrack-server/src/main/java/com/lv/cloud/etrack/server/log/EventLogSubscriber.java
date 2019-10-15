package com.lv.cloud.etrack.server.log;

import com.lv.cloud.etrack.core.document.EventLogDoc;
import com.lv.cloud.etrack.core.service.IEventLogService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @author xiaoyulin
 * @description
 * @date 2019-10-12
 */
@Component("eventLogSubscriber")
public class EventLogSubscriber {

    private final Logger logger = LoggerFactory.getLogger(EventLogSubscriber.class);

    @Resource
    private IEventLogService eventLogService;

    @Resource
    private ElasticsearchOperations elasticsearchOperations;

    @KafkaListener(id = "eventLogGroup", topics = "topic-lv.event.log")
    public void listen(List<EventLogDoc> list) throws IOException {
        if(CollectionUtils.isEmpty(list)){
           return;
        }
        if(list.size() > 1){
//            list.forEach(eventLogDoc -> eventLogService.add(eventLogDoc));
            eventLogService.batchAdd(list);
        }else {
            eventLogService.add(list.get(0));
        }

        List<EventLogDoc> rs = eventLogService.query(new HashMap<>());
        System.out.println(rs);
    }
}
