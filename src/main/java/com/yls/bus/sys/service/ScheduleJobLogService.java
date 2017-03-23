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
	 * ����ID����ѯ��ʱ������־
	 */
	ScheduleJobLog queryObject(String logId);
	
	/**
	 * ��ѯ��ʱ������־�б�
	 */
	List<ScheduleJobLog> queryList(Map<String, String> map);
	
	/**
	 * ��ѯ����
	 */
	int queryTotal(Map<String, Object> map);
	
	/**
	 * ���涨ʱ������־
	 */
	void save(ScheduleJobLog log);

}
