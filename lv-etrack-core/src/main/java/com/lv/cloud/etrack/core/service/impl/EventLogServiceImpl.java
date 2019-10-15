package com.lv.cloud.etrack.core.service.impl;

import com.lv.cloud.etrack.core.document.EventLogDoc;
import com.lv.cloud.etrack.core.repository.EventLogRepository;
import com.lv.cloud.etrack.core.service.IEventLogService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author xiaoyulin
 * @description
 * @date 2019-10-12
 */
@Component("eventLogService")
public class EventLogServiceImpl implements IEventLogService {

    @Resource
    private EventLogRepository eventLogRepository;

    @Override
    public void batchAdd(List<EventLogDoc> list) {
        eventLogRepository.batchAdd(list);
    }

    @Override
    public void add(EventLogDoc eventLogDoc) {
        eventLogRepository.save(eventLogDoc);
    }

    @Override
    public List<EventLogDoc> query(Map<String, Object> params) {
//        eventLogRepository.search();
        BoolQueryBuilder qb= QueryBuilders.boolQuery();
        qb.should(QueryBuilders.termQuery("appName","lvcloud-test2".toLowerCase()));
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(qb)
                .withHighlightFields(new HighlightBuilder.Field("appName").fragmentSize(15))
                .build();
        return eventLogRepository.search(searchQuery).getContent();
    }
}
