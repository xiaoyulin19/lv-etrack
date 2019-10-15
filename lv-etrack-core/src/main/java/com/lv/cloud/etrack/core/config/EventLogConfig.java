package com.lv.cloud.etrack.core.config;

import com.lv.cloud.etrack.core.document.EventLogDoc;
import com.lv.cloud.etrack.core.repository.EventLogRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.elasticsearch.repository.support.ElasticsearchEntityInformation;
import org.springframework.data.elasticsearch.repository.support.MappingElasticsearchEntityInformation;

/**
 * @author xiaoyulin
 * @description
 * @date 2019-10-14
 */
@EnableElasticsearchRepositories
@Configuration
public class EventLogConfig {

    @Bean
    public EventLogRepository eventLogRepository(ElasticsearchOperations elasticsearchOperations){
        ElasticsearchEntityInformation entityInformation = new MappingElasticsearchEntityInformation(
                elasticsearchOperations.getElasticsearchConverter().getMappingContext().getPersistentEntity(EventLogDoc.class)
        );
        EventLogRepository eventLogRepository = new EventLogRepository(elasticsearchOperations, entityInformation);
        return eventLogRepository;
    }
}
