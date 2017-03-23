package com.yls.bus.sys.service;

import java.util.List;
import java.util.Map;


import com.yls.bus.sys.dao.entity.ScheduleJobLog;

/**
 * 
 * @author Lian Shan Yang
 *
 */
public interface ScheduleJobLogService {

	/**
	 * 根据ID，查询定时任务日志
	 */
	ScheduleJobLog queryObject(String logId);
	
	/**
	 * 查询定时任务日志列表
	 */
	List<ScheduleJobLog> queryList(Map<String, String> map);
	
	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, Object> map);
	
	/**
	 * 保存定时任务日志
	 */
	void save(ScheduleJobLog log);

}
