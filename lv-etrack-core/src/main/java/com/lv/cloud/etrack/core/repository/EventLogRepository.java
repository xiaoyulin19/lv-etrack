package com.lv.cloud.etrack.core.repository;

import com.google.common.collect.Lists;
import com.lv.cloud.etrack.core.document.EventLogDoc;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.repository.support.ElasticsearchEntityInformation;
import org.springframework.data.elasticsearch.repository.support.UUIDElasticsearchRepository;

import java.util.List;
import java.util.UUID;

/**
 * @author xiaoyulin
 * @description
 * @date 2019-10-12
 */
public class EventLogRepository extends UUIDElasticsearchRepository<EventLogDoc> {

    public EventLogRepository() {
        super();
    }

    public EventLogRepository(ElasticsearchOperations elasticsearchOperations) {
        super(elasticsearchOperations);
    }

    public EventLogRepository(ElasticsearchOperations elasticsearchOperations,
                              ElasticsearchEntityInformation<EventLogDoc, UUID> entityInformation) {
        super(entityInformation, elasticsearchOperations);
    }

    public void batchAdd(List<EventLogDoc> list) {
        if(CollectionUtils.isEmpty(list)) {
            return ;
        }
        List<IndexQuery> queries = Lists.newArrayListWithExpectedSize(list.size());
        IndexQuery indexItem  = null;
        for(EventLogDoc eventLog : list) {
            indexItem = new IndexQuery();
            indexItem.setObject(eventLog);
            queries.add(indexItem);
        }
        elasticsearchOperations.bulkIndex(queries);
    }

}
