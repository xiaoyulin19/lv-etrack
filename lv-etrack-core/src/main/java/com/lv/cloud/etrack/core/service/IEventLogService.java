package com.lv.cloud.etrack.core.service;

import com.lv.cloud.etrack.core.document.EventLogDoc;

import java.util.List;
import java.util.Map;

public interface IEventLogService {

    void batchAdd(List<EventLogDoc> list);

    void add(EventLogDoc eventLogDoc);

    List<EventLogDoc> query(Map<String, Object> params);

}
