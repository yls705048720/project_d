/**
 * 
 */
package com.yls.freamwork.utils;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.yls.bus.sys.dao.entity.ScheduleJob;
import com.yls.bus.sys.dao.entity.ScheduleJobLog;
import com.yls.bus.sys.service.ScheduleJobLogService;

/**
 * @author Lian Shan Yang
 *
 */
public class YlsScheduleJob extends QuartzJobBean {

//	@Autowired
//	private ScheduleJobLogService scheduleJobLogService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 异步执行
	 */
	private ExecutorService service = Executors.newSingleThreadExecutor();
	
	/* (non-Javadoc)
	 * @see org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org.quartz.JobExecutionContext)
	 */
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		
        ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap()
        		.get(ScheduleJob.JOB_PARAM_KEY);
        
        //获取spring bean
        ScheduleJobLogService scheduleJobLogService = (ScheduleJobLogService) YlsSpringConextUtils.getBean("scheduleJobLogService");
        
        //数据库保存执行记录
        ScheduleJobLog log = new ScheduleJobLog();
        log.setJobId(scheduleJob.getJobId());
        log.setBeanName(scheduleJob.getBeanName());
        log.setMethodName(scheduleJob.getMethodName());
        log.setParams(scheduleJob.getParams());
        log.setCreateTime(new Date());
        
        //任务开始时间
        long startTime = System.currentTimeMillis();
        
        try {
            //执行任务
        	logger.info("任务准备执行，任务ID：" + scheduleJob.getJobId());
        	
            ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getBeanName(), 
            																						scheduleJob.getMethodName(), scheduleJob.getParams());
            /**
             *  Future 对象可以用于判断 Runnable 是否结束执行。
             */
            Future<?> future = service.submit(task);
            
			future.get();
			
			//任务执行总时长
			long times = System.currentTimeMillis() - startTime;
			log.setTimes(new String());
			//任务状态    0：成功    1：失败
			log.setStatus("0");
			
			logger.info("任务执行完毕，任务ID：" + scheduleJob.getJobId() + "  总共耗时：" + times + "毫秒");
		} catch (Exception e) {
			logger.error("任务执行失败，任务ID：" + scheduleJob.getJobId(), e);
			
			//任务执行总时长
			long times = System.currentTimeMillis() - startTime;
			log.setTimes(String.valueOf(times));
			
			//任务状态    0：成功    1：失败
			log.setStatus("1");
			log.setError(StringUtils.substring(e.toString(), 0, 2000));
		}finally {
			scheduleJobLogService.save(log);
		}
	}

}
