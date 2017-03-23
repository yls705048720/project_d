/**
 * 
 */
package com.yls.bus.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.yls.bus.sys.dao.entity.ScheduleJob;
import com.yls.bus.sys.dao.entity.ScheduleJobExample;
import com.yls.bus.sys.dao.mapper.ScheduleJobMapper;
import com.yls.bus.sys.service.ScheduleJobService;
import com.yls.freamwork.utils.YlsConstants.ScheduleStatus;
import com.yls.freamwork.utils.YlsScheduleUtils;

/**
 * @author Lian Shan Yang
 *
 */
@Service
public class ScheduleJobServiceImpl implements ScheduleJobService {
	
	@Autowired
    private Scheduler scheduler;
	
	@Autowired
	private ScheduleJobMapper scheduleJobMapper;

	/* (non-Javadoc)
	 * @see com.yls.bus.sys.service.ScheduleJobService#queryObject(java.lang.String)
	 */
	@Override
	public ScheduleJob queryObject(String jobId) {
		// TODO Auto-generated method stub
		return scheduleJobMapper.selectByPrimaryKey(jobId);
	}

	/* (non-Javadoc)
	 * @see com.yls.bus.sys.service.ScheduleJobService#queryList(java.util.Map)
	 */
	@Override
	public List<ScheduleJob> queryList(Map<String, String> map) {
		// TODO Auto-generated method stub
		   int rows = 10;
		   int page = 0;
		   
		   if(StringUtils.isNotEmpty(map.get("rows"))){
			   rows = Integer.parseInt( map.get("rows"));
		   }
		   if( StringUtils.isNotEmpty(map.get("page"))){
			   page = Integer.parseInt(map.get("page"));
		   }
		   
		   ScheduleJobExample scheduleJobExample = new ScheduleJobExample();
		   ScheduleJobExample.Criteria criteria = scheduleJobExample.createCriteria();
		   
		   if(StringUtils.isNotEmpty(map.get("beanName"))){
			   criteria.andBeanNameLike("%"+map.get("beanName")+"%");
		   }
		   
		   PageHelper.startPage(page,rows);
		   
		return scheduleJobMapper.selectByExample(scheduleJobExample);
	}

	/* (non-Javadoc)
	 * @see com.yls.bus.sys.service.ScheduleJobService#queryTotal(java.util.Map)
	 */
	@Override
	public int queryTotal(Map<String, String> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.yls.bus.sys.service.ScheduleJobService#save(com.yls.bus.sys.dao.entity.ScheduleJob)
	 */
	@Override
	@Transactional
	public void save(ScheduleJob scheduleJob) {
		// TODO Auto-generated method stub
		scheduleJobMapper.insert(scheduleJob);
	}

	/* (non-Javadoc)
	 * @see com.yls.bus.sys.service.ScheduleJobService#update(com.yls.bus.sys.dao.entity.ScheduleJob)
	 */
	@Override
	@Transactional
	public void update(ScheduleJob scheduleJob) {
		// TODO Auto-generated method stub
		scheduleJobMapper.updateByPrimaryKey(scheduleJob);
	}

	/* (non-Javadoc)
	 * @see com.yls.bus.sys.service.ScheduleJobService#deleteBatch(java.lang.String[])
	 */
	@Override
	@Transactional
	public void deleteBatch(String[] jobIds) {
		// TODO Auto-generated method stub
		for(String jobId: jobIds){
			scheduleJobMapper.deleteByPrimaryKey(jobId);
		}
	}

	/* (non-Javadoc)
	 * @see com.yls.bus.sys.service.ScheduleJobService#updateBatch(java.lang.String[], java.lang.String)
	 */
	@Override
	@Transactional
	public int updateBatch(String[] jobIds, String status) {
		// TODO Auto-generated method stub
		for(String jobId: jobIds){
			ScheduleJob scheduleJob =scheduleJobMapper.selectByPrimaryKey(jobId);
			scheduleJob.setStatus(status);
			scheduleJobMapper.updateByPrimaryKey(scheduleJob);
		}
		return 1;
	}

	/* (non-Javadoc)
	 * @see com.yls.bus.sys.service.ScheduleJobService#run(java.lang.String[])
	 */
	@Override
	public void run(String[] jobIds) {
		// TODO Auto-generated method stub
    	for(String jobId : jobIds){
    		YlsScheduleUtils.run(scheduler, queryObject(jobId));
    	}
	}

	/* (non-Javadoc)
	 * @see com.yls.bus.sys.service.ScheduleJobService#pause(java.lang.String[])
	 */
	@Override
	@Transactional
	public void pause(String[] jobIds) {
		// TODO Auto-generated method stub
        for(String jobId : jobIds){
        	YlsScheduleUtils.pauseJob(scheduler, jobId);
    	}      
    	updateBatch(jobIds, ScheduleStatus.PAUSE.getValue());
	}

	/* (non-Javadoc)
	 * @see com.yls.bus.sys.service.ScheduleJobService#resume(java.lang.String[])
	 */
	@Override
	@Transactional
	public void resume(String[] jobIds) {
		// TODO Auto-generated method stub
    	for(String jobId : jobIds){
    		YlsScheduleUtils.resumeJob(scheduler, jobId);
    	}

    	updateBatch(jobIds, ScheduleStatus.NORMAL.getValue());
	}

}
