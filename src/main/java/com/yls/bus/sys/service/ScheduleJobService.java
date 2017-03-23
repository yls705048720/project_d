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
	 * 根据ID，查询定时任务
	 */
	ScheduleJob queryObject(String jobId);
	
	/**
	 * 查询定时任务列表
	 */
	List<ScheduleJob> queryList(Map<String, String> map);
	
	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, String> map);
	
	/**
	 * 保存定时任务
	 */
	void save(ScheduleJob scheduleJob);
	
	/**
	 * 更新定时任务
	 */
	void update(ScheduleJob scheduleJob);
	
	/**
	 * 批量删除定时任务
	 */
	void deleteBatch(String[] jobIds);
	
	/**
	 * 批量更新定时任务状态
	 */
	int updateBatch(String[] jobIds, String status);
	
	/**
	 * 立即执行
	 */
	void run(String[] jobIds);
	
	/**
	 * 暂停运行
	 */
	void pause(String[] jobIds);
	
	/**
	 * 恢复运行
	 */
	void resume(String[] jobIds);
}
