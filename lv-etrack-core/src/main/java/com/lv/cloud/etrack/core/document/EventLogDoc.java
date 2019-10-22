package com.lv.cloud.etrack.core.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author xiaoyulin
 * @description 事件日志
 * @date 2019-10-12
 */
@Document(indexName = "event-log", type = "eventLog", replicas=0, shards=3)
public class EventLogDoc {

    @Id
    private String id;

    /**
     * 模块编码
     */
    @Field(type=FieldType.Text)
    private String bussinessCode;

    /**
     * 错误码
     */
    @Field(type=FieldType.Text)
    private String errorCode;

    /**
     * 日志内容（概要）
     */
    @Field(type=FieldType.Text)
    private String message;

    /**
     * 日志内容（详细描述：Exception等）
     */
    @Field(type=FieldType.Text)
//    @Field(searchAnalyzer="ik_smart",analyzer="ik_smart",type=FieldType.Text)
    private String detail;

    /**
     * 业务ID
     */
    @Field(type=FieldType.Text)
    private String bussinessId;

    /**
     * 业务标识
     */
    @Field(type=FieldType.Text)
    private String tag;

    /**
     * 堆栈信息
     */
    @Field(type=FieldType.Text)
    private String stackTrace;

    @Field(type=FieldType.Text)
    private String trackNumber;

    /**
     * 应用名
     */
    @Field(type=FieldType.Text)
    private String appName;

    /**
     * 创建时间
     */
    @Field(type=FieldType.Long)
    private Long createTime;

    /**
     * 日志级别：INFO，WARN，ERROR
     */
    @Field(type=FieldType.Text)
    private String level;

    /**
     * 日志类型(LOGIC/SYSTEM)
     */
    @Field(type=FieldType.Text)
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}
