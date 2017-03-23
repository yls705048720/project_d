/**
 * 
 */
package com.yls.bus.sys.service;

import java.util.List;
import java.util.Map;


import com.yls.bus.sys.dao.entity.ScheduleJob;

/**
 * @author Lian Shan Yang
 *
 */
public interface ScheduleJobService {
	/**
	 * ����ID����ѯ��ʱ����
	 */
	ScheduleJob queryObject(String jobId);
	
	/**
	 * ��ѯ��ʱ�����б�
	 */
	List<ScheduleJob> queryList(Map<String, String> map);
	
	/**
	 * ��ѯ����
	 */
	int queryTotal(Map<String, String> map);
	
	/**
	 * ���涨ʱ����
	 */
	void save(ScheduleJob scheduleJob);
	
	/**
	 * ���¶�ʱ����
	 */
	void update(ScheduleJob scheduleJob);
	
	/**
	 * ����ɾ����ʱ����
	 */
	void deleteBatch(String[] jobIds);
	
	/**
	 * �������¶�ʱ����״̬
	 */
	int updateBatch(String[] jobIds, String status);
	
	/**
	 * ����ִ��
	 */
	void run(String[] jobIds);
	
	/**
	 * ��ͣ����
	 */
	void pause(String[] jobIds);
	
	/**
	 * �ָ�����
	 */
	void resume(String[] jobIds);
}
