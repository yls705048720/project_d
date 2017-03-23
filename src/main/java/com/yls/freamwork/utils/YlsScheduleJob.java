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
	 * �첽ִ��
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
        
        //��ȡspring bean
        ScheduleJobLogService scheduleJobLogService = (ScheduleJobLogService) YlsSpringConextUtils.getBean("scheduleJobLogService");
        
        //���ݿⱣ��ִ�м�¼
        ScheduleJobLog log = new ScheduleJobLog();
        log.setJobId(scheduleJob.getJobId());
        log.setBeanName(scheduleJob.getBeanName());
        log.setMethodName(scheduleJob.getMethodName());
        log.setParams(scheduleJob.getParams());
        log.setCreateTime(new Date());
        
        //����ʼʱ��
        long startTime = System.currentTimeMillis();
        
        try {
            //ִ������
        	logger.info("����׼��ִ�У�����ID��" + scheduleJob.getJobId());
        	
            ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getBeanName(), 
            																						scheduleJob.getMethodName(), scheduleJob.getParams());
            /**
             *  Future ������������ж� Runnable �Ƿ����ִ�С�
             */
            Future<?> future = service.submit(task);
            
			future.get();
			
			//����ִ����ʱ��
			long times = System.currentTimeMillis() - startTime;
			log.setTimes(new String());
			//����״̬    0���ɹ�    1��ʧ��
			log.setStatus("0");
			
			logger.info("����ִ����ϣ�����ID��" + scheduleJob.getJobId() + "  �ܹ���ʱ��" + times + "����");
		} catch (Exception e) {
			logger.error("����ִ��ʧ�ܣ�����ID��" + scheduleJob.getJobId(), e);
			
			//����ִ����ʱ��
			long times = System.currentTimeMillis() - startTime;
			log.setTimes(String.valueOf(times));
			
			//����״̬    0���ɹ�    1��ʧ��
			log.setStatus("1");
			log.setError(StringUtils.substring(e.toString(), 0, 2000));
		}finally {
			scheduleJobLogService.save(log);
		}
	}

}
