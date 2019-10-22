package com.lv.cloud.etrack.client.eventlog;

import ch.qos.logback.classic.Level;
import com.lv.cloud.etrack.client.context.ETrackContext;
import com.lv.cloud.etrack.client.producer.EventLogKafkaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 事件日志
 * Created by xiaoyulin on 2017/8/15.
 */
public class EventLogger {
	
    private Logger log;

    EventLogger(Class clazz){
        log = LoggerFactory.getLogger(clazz);
    }

    private EventLogKafkaTemplate getEventLogKafkaTemplate(){
    	return EventLoggerFactory.eventLogKafkaTemplate;
	}
        
    /**
     * 输出系统事件日志
     * @param bussinessCode 业务/模块编码
     * @param errorCode 错误码
     * @param message 日志描述（要求简短）
     * @param detail 日志详情
     * @param ex 异常
     * @param bussinessId 业务id（比如订单号）
     * @param tag 标记（便于检索）
     */
    public void outputSystemEventLog(String bussinessCode, String errorCode, String message, String detail, Throwable ex, Object bussinessId, String tag){ 
    	try {
			// 调用方法
    		int i = 2;
			EventLogKafkaTemplate.EventLogVo eventLog = getSystemEventLog(bussinessCode, errorCode, bussinessId, tag,
					message, detail, ex, Thread.currentThread().getStackTrace()[i].toString());
			log.error("E-TRACK:" + eventLog.toString());
			this.getEventLogKafkaTemplate().sendEventLog(eventLog);
		} catch (Exception e) {
			log.error("输出系统事件日志失败，{}", e); 
		}
    }

    private EventLogKafkaTemplate.EventLogVo getSystemEventLog(String bussinessCode, String errorCode, Object bussinessId, String tag, String message, String detail,
															   Throwable ex, String stackTrace){
		EventLogKafkaTemplate.EventLogVo eventLog = getErrorLogicEventLog(bussinessCode, errorCode, String.valueOf(bussinessId), tag, message, detail, stackTrace);
		eventLog.setType(LoggingEnum.LOG_EVENT.SYSTEM.name());
		StringBuffer detailSb = new StringBuffer();
		detailSb.append(detail).append("-EXCEPTION:").append(ex.getMessage());
		if(ex.getStackTrace().length > 0){
			detailSb.append(ex.getStackTrace()[0]);
		}
		eventLog.setDetail(detailSb.toString());
		return eventLog;
	}
    
    /**
     * 记录简洁业务事件日志（info级别）,带业务模块编码
     * @param bussinessCode 业务/模块编码
     * @param bussinessId 业务id（比如订单号）
     * @param tag 标记（便于检索）
     * @param message 日志描述（要求简短）
     * 
     */
    public void infoLogicEventLog(String bussinessCode, Object bussinessId, String tag, String message){
        try {
			// 输出日志
			int i = 2;
			EventLogKafkaTemplate.EventLogVo eventLog = getInfoLogicEventLog(bussinessCode, null, bussinessId, tag,
					message, null, Thread.currentThread().getStackTrace()[i].toString());
			log.info("E-TRACK:" + eventLog);
			this.getEventLogKafkaTemplate().sendEventLog(eventLog);
		} catch (Exception e) {
			log.error("记录业务事件日志失败，{}", e); 
		}
    }
    
    /**
     * 记录详细业务事件日志（info级别）,带业务模块编码
     * @param bussinessCode 业务/模块编码
     * @param bussinessId 业务id（比如订单号）
     * @param tag 标记（便于检索）
     * @param message 日志描述（要求简短）
     * @param detail 日志详情
     */
    public void infoLogicEventLog(String bussinessCode, Object bussinessId, String tag, String message, String detail){
        try {
			// 输出日志
			int i = 2;
			EventLogKafkaTemplate.EventLogVo eventLog = getInfoLogicEventLog(bussinessCode, null, bussinessId, tag,
					message, detail, Thread.currentThread().getStackTrace()[i].toString());
			log.info("E-TRACK:" + eventLog);
			this.getEventLogKafkaTemplate().sendEventLog(eventLog);
		} catch (Exception e) {
			log.error("记录业务事件日志失败，{}", e); 
		}
    }
    
    /**
     * 记录详细业务事件日志（warn级别）,带业务模块编码
     * @param message 日志描述（要求简短）
     * @param bussinessId 业务id（比如订单号）
     * @param tag 标记（便于检索）
     * @param bussinessCode 业务/模块编码
     */
    public void warnLogicEventLog(String bussinessCode, Object bussinessId, String tag, String message, String detail){
        try {
			// 输出日志
			int i = 2;
			EventLogKafkaTemplate.EventLogVo eventLog = getWarnLogicEventLog(bussinessCode, null, bussinessId, tag,
					message, detail, Thread.currentThread().getStackTrace()[i].toString());
			log.warn("E-TRACK:" + eventLog);
			this.getEventLogKafkaTemplate().sendEventLog(eventLog);
        } catch (Exception e) {
			log.error("记录业务事件日志失败，{}", e); 
		}
    }
    
    /**
     * 记录简洁业务事件日志（error级别）,带业务模块编码和错误码
     * @param bussinessCode 业务/模块编码
     * @param errorCode 错误码
     * @param bussinessId 业务id（比如订单号）
     * @param tag 标记（便于检索）
     * @param message 日志描述（要求简短）
     * 
     */
    public void errorLogicEventLog(String bussinessCode, String errorCode, Object bussinessId, String tag, String message){
        try {
			// 输出日志
			int i = 2;
			EventLogKafkaTemplate.EventLogVo eventLog = getErrorLogicEventLog(bussinessCode, errorCode, bussinessId, tag,
					message, null, Thread.currentThread().getStackTrace()[i].toString());
			log.error("E-TRACK:" + eventLog);
			this.getEventLogKafkaTemplate().sendEventLog(eventLog);
		} catch (Exception e) {
			log.error("记录业务事件日志失败，{}", e); 
		}
    }
    
    /**
     * 记录详细业务事件日志（error级别）,带业务模块编码和错误码
     * @param bussinessCode 业务/模块编码
     * @param errorCode 错误码
     * @param bussinessId 业务id（比如订单号）
     * @param tag 标记（便于检索）
     * @param message 日志描述（要求简短）
     * @param detail 日志详情
     */
    public void errorLogicEventLog(String bussinessCode, String errorCode, Object bussinessId, String tag, String message, String detail){
        try {
			// 输出日志
			int i = 2;
			EventLogKafkaTemplate.EventLogVo eventLog = getErrorLogicEventLog(bussinessCode, errorCode, bussinessId, tag,
					message, detail, Thread.currentThread().getStackTrace()[i].toString());
			log.error("E-TRACK:" + eventLog);
			this.getEventLogKafkaTemplate().sendEventLog(eventLog);
		} catch (Exception e) {
			log.error("记录业务事件日志失败，{}", e); 
		}
    }
    
    /**
     * 记录详细业务事件日志（error级别）,带业务模块编码
     * @param bussinessCode 业务/模块编码
     * @param bussinessId 业务id（比如订单号）
     * @param tag 标记（便于检索）
     * @param message 日志描述（要求简短）
     * @param detail 日志详情
     */
    public void errorLogicEventLog(String bussinessCode, Object bussinessId, String tag, String message, String detail){
        try {
			// 输出日志
			int i = 2;
			EventLogKafkaTemplate.EventLogVo eventLog = getErrorLogicEventLog(bussinessCode, null, bussinessId, tag,
					message, detail, Thread.currentThread().getStackTrace()[i].toString());
			log.error("E-TRACK:" + eventLog);
			this.getEventLogKafkaTemplate().sendEventLog(eventLog);
        } catch (Exception e) {
			log.error("记录业务事件日志失败，{}", e); 
		}
    }

	/**
	 * 获取业务事件日志对象(info级别)<br/>
	 * @param bussinessCode 业务/模块编码
	 * @param errorCode 错误码
	 * @param message 日志内容（要求简短）
	 * @param detail 日志详情
	 * @param bussinessId 业务id（比如订单号）
	 * @param tag 标记（便于检索）
	 * @param bussinessCode 业务模块编码
	 * @param stackTrace
	 * @return
	 */
	private EventLogKafkaTemplate.EventLogVo getInfoLogicEventLog(String bussinessCode, String errorCode, Object bussinessId,
																  String tag, String message, String detail, String stackTrace){
		EventLogKafkaTemplate.EventLogVo eventLog = getLogicEventLog(bussinessCode, errorCode, bussinessId, tag, message, detail, stackTrace);
		eventLog.setLevel(Level.INFO.levelStr);
		return eventLog;
	}

	/**
	 * 获取业务事件日志对象(warn级别)<br/>
	 * @param bussinessCode 业务/模块编码
	 * @param errorCode 错误码
	 * @param message 日志内容（要求简短）
	 * @param detail 日志详情
	 * @param bussinessId 业务id（比如订单号）
	 * @param tag 标记（便于检索）
	 * @param bussinessCode 业务模块编码
	 * @param stackTrace
	 * @return
	 */
	private EventLogKafkaTemplate.EventLogVo getWarnLogicEventLog(String bussinessCode, String errorCode, Object bussinessId,
																   String tag, String message, String detail, String stackTrace){
		EventLogKafkaTemplate.EventLogVo eventLog = getLogicEventLog(bussinessCode, errorCode, bussinessId, tag, message, detail, stackTrace);
		eventLog.setLevel(Level.WARN.levelStr);
		return eventLog;
	}

	/**
	 * 获取业务事件日志对象(Error级别)<br/>
	 * @param bussinessCode 业务/模块编码
	 * @param errorCode 错误码
	 * @param message 日志内容（要求简短）
	 * @param detail 日志详情
	 * @param bussinessId 业务id（比如订单号）
	 * @param tag 标记（便于检索）
	 * @param bussinessCode 业务模块编码
	 * @param stackTrace
	 * @return
	 */
	private EventLogKafkaTemplate.EventLogVo getErrorLogicEventLog(String bussinessCode, String errorCode, Object bussinessId,
																   String tag, String message, String detail, String stackTrace){
		EventLogKafkaTemplate.EventLogVo eventLog = getLogicEventLog(bussinessCode, errorCode, bussinessId, tag, message, detail, stackTrace);
		eventLog.setLevel(Level.ERROR.levelStr);
		return eventLog;
	}

	/**
	 * 获取业务事件日志对象<br/>
	 * @param bussinessCode 业务/模块编码
	 * @param errorCode 错误码
	 * @param message 日志内容（要求简短）
	 * @param detail 日志详情
	 * @param bussinessId 业务id（比如订单号）
	 * @param tag 标记（便于检索）
	 * @param bussinessCode 业务模块编码
	 * @param stackTrace
	 * @return
	 */
	private EventLogKafkaTemplate.EventLogVo getLogicEventLog(String bussinessCode, String errorCode, Object bussinessId,
															  String tag, String message, String detail, String stackTrace){
		EventLogKafkaTemplate.EventLogVo eventLog = new EventLogKafkaTemplate.EventLogVo();
		eventLog.setAppName(ETrackContext.appName);
		eventLog.setTrackNumber(ETrackContext.getTrackNumber());
		eventLog.setType(LoggingEnum.LOG_EVENT.LOGIC.name());
		eventLog.setBussinessCode(bussinessCode);
		eventLog.setErrorCode(errorCode);
		eventLog.setBussinessId(String.valueOf(bussinessId));
		eventLog.setTag(tag);
		eventLog.setMessage(message);
		eventLog.setDetail(detail);
		eventLog.setStackTrace(stackTrace);
		eventLog.setCreateTime(new Date().getTime());
		return eventLog;
	}

    public void info(Object message){
        log.info(Thread.currentThread().getStackTrace()[2].toString() + message);
    }

    public void debug(Object message){
        log.debug(Thread.currentThread().getStackTrace()[2].toString() + message);
    }

    public void error(Object message){
        log.error(Thread.currentThread().getStackTrace()[2].toString() + message);
    }

    public void error(Object message, Throwable t){
        log.error(Thread.currentThread().getStackTrace()[2].toString() + message, t);
    }

    public void warn(Object message){
        log.warn(Thread.currentThread().getStackTrace()[2].toString() + message);
    }
    
    public boolean isDebugEnabled(){
    	return log.isDebugEnabled();
    }
    
    public Logger getLogger(){
    	return log;
    }
}
