/**
 * 
 */
package com.yls.freamwork.utils;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;

import com.yls.bus.sys.dao.entity.ScheduleJob;
import com.yls.freamwork.utils.YlsConstants.ScheduleStatus;

/**
 * @author Lian Shan Yang
 *
 */
public interface YlsScheduleUtils {
	
    final static String JOB_NAME = "TASK_";
    
    /**
     * ��ȡ������key
     */
    public static TriggerKey getTriggerKey(String jobId) {
        return TriggerKey.triggerKey(JOB_NAME + jobId);
    }
    
    /**
     * ��ȡjobKey
     */
    public static JobKey getJobKey(String jobId) {
        return JobKey.jobKey(JOB_NAME + jobId);
    }

    /**
     * ��ȡ���ʽ������
     */
    public static CronTrigger getCronTrigger(Scheduler scheduler, String jobId) {
        try {
            return (CronTrigger) scheduler.getTrigger(getTriggerKey(jobId));
        } catch (SchedulerException e) {
            throw new YlsRRException("��ȡ��ʱ����CronTrigger�����쳣", e);
        }
    }

    /**
     * ������ʱ����
     */
    public static void createScheduleJob(Scheduler scheduler, ScheduleJob scheduleJob) {
        try {
        	//����job��Ϣ
            JobDetail jobDetail = JobBuilder.newJob(YlsScheduleJob.class).withIdentity(getJobKey(scheduleJob.getJobId())).build();

            //���ʽ���ȹ�����
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());

            //���µ�cronExpression���ʽ����һ���µ�trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(getTriggerKey(scheduleJob.getJobId())).withSchedule(scheduleBuilder).build();

            //�������������ʱ�ķ������Ի�ȡ
            jobDetail.getJobDataMap().put(ScheduleJob.JOB_PARAM_KEY, scheduleJob);
            
            scheduler.scheduleJob(jobDetail, trigger);
            
            //��ͣ����
            if(scheduleJob.getStatus() == ScheduleStatus.PAUSE.getValue()){
            	pauseJob(scheduler, scheduleJob.getJobId());
            }
        } catch (SchedulerException e) {
            throw new YlsRRException("������ʱ����ʧ��", e);
        }
    }
    
    /**
     * ���¶�ʱ����
     */
    public static void updateScheduleJob(Scheduler scheduler, ScheduleJob scheduleJob) {
        try {
            TriggerKey triggerKey = getTriggerKey(scheduleJob.getJobId());

            //���ʽ���ȹ�����
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());

            CronTrigger trigger = getCronTrigger(scheduler, scheduleJob.getJobId());
            
            //���µ�cronExpression���ʽ���¹���trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            
            //����
            trigger.getJobDataMap().put(ScheduleJob.JOB_PARAM_KEY, scheduleJob);
            
            scheduler.rescheduleJob(triggerKey, trigger);
            
            //��ͣ����
            if(scheduleJob.getStatus() == ScheduleStatus.PAUSE.getValue()){
            	pauseJob(scheduler, scheduleJob.getJobId());
            }
            
        } catch (SchedulerException e) {
            throw new YlsRRException("���¶�ʱ����ʧ��", e);
        }
    }

    /**
     * ����ִ������
     */
    public static void run(Scheduler scheduler, ScheduleJob scheduleJob) {
        try {
        	//����
        	JobDataMap dataMap = new JobDataMap();
        	dataMap.put(ScheduleJob.JOB_PARAM_KEY, scheduleJob);
        	
            scheduler.triggerJob(getJobKey(scheduleJob.getJobId()), dataMap);
        } catch (SchedulerException e) {
            throw new YlsRRException("����ִ�ж�ʱ����ʧ��", e);
        }
    }

    /**
     * ��ͣ����
     */
    public static void pauseJob(Scheduler scheduler, String jobId) {
        try {
            scheduler.pauseJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            throw new YlsRRException("��ͣ��ʱ����ʧ��", e);
        }
    }

    /**
     * �ָ�����
     */
    public static void resumeJob(Scheduler scheduler, String jobId) {
        try {
            scheduler.resumeJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            throw new YlsRRException("��ͣ��ʱ����ʧ��", e);
        }
    }

    /**
     * ɾ����ʱ����
     */
    public static void deleteScheduleJob(Scheduler scheduler, String jobId) {
        try {
            scheduler.deleteJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            throw new YlsRRException("ɾ����ʱ����ʧ��", e);
        }
    }
}
