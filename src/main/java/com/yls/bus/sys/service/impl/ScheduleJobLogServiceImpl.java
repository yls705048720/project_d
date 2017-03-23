/**
 * 
 */
package com.yls.bus.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.yls.bus.sys.dao.entity.ScheduleJobLog;
import com.yls.bus.sys.dao.entity.ScheduleJobLogExample;
import com.yls.bus.sys.dao.mapper.ScheduleJobLogMapper;
import com.yls.bus.sys.service.ScheduleJobLogService;

/**
 * @author Lian Shan Yang
 *
 */
@Service
public class ScheduleJobLogServiceImpl implements ScheduleJobLogService {
	
	@Autowired
	private ScheduleJobLogMapper scheduleJobLogMapper;

	/* (non-Javadoc)
	 * @see com.yls.bus.sys.service.ScheduleJobLogService#queryObject(java.lang.String)
	 */
	@Override
	public ScheduleJobLog queryObject(String logId) {
		// TODO Auto-generated method stub
		return scheduleJobLogMapper.selectByPrimaryKey(logId);
	}

	/* (non-Javadoc)
	 * @see com.yls.bus.sys.service.ScheduleJobLogService#queryList(java.util.Map)
	 */
	@Override
	public List<ScheduleJobLog> queryList(Map<String, String> map) {
		// TODO Auto-generated method stub
		   int pageSize = 10;
		   int pageNum = 0;
		   
		   if(map.get("row") != null){
			   pageSize = Integer.parseInt( map.get("row"));
			}
		   if( map.get("page") != null){
			   pageNum = Integer.parseInt(map.get("page"));
			}
		   
		   ScheduleJobLogExample scheduleJobLogExample = new ScheduleJobLogExample();
		   @SuppressWarnings("unused")
		   ScheduleJobLogExample.Criteria criteria = scheduleJobLogExample.createCriteria();
		   
		   
		   PageHelper.startPage(pageNum, pageSize);
		   
		return scheduleJobLogMapper.selectByExample(scheduleJobLogExample);
	}

	/* (non-Javadoc)
	 * @see com.yls.bus.sys.service.ScheduleJobLogService#queryTotal(java.util.Map)
	 */
	@Override
	public int queryTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.yls.bus.sys.service.ScheduleJobLogService#save(com.yls.bus.sys.dao.entity.ScheduleJobLog)
	 */
	@Override
	@Transactional
	public void save(ScheduleJobLog log) {
		// TODO Auto-generated method stub
		scheduleJobLogMapper.insert(log);
	}

}
