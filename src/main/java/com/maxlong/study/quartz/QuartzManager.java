package com.maxlong.study.quartz;

import java.util.Properties;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.listeners.TriggerListenerSupport;

/** 
* @Title:Quartz������
* @Description:
* @Copyright: 
* @author ���� maxlong: 
* @version ����ʱ�䣺2016��6��17�� ����4:54:57 
* ��˵�� 
*/

public class QuartzManager {
	

	private static String JOB_GROUP_NAME = "group1";

	public static Scheduler addJob(String jobName,String triggerName, Job job,String time,Scheduler scheduler) throws SchedulerException{
		JobDetail jobDetail = JobBuilder.newJob(job.getClass()).withIdentity(jobName, JOB_GROUP_NAME).build();
		jobDetail.getJobDataMap().put("background.job.arg", "123456");
		
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName)
        .withSchedule(CronScheduleBuilder.cronSchedule(time).withMisfireHandlingInstructionFireAndProceed()).build();
		return initScheduler(jobDetail,trigger,scheduler);
	}
	
	public static Scheduler initScheduler(JobDetail jobDetail,Trigger trigger,Scheduler scheduler) throws SchedulerException{
		scheduler.getListenerManager().addTriggerListener(new BackgroundTriggerListener());
		scheduler.scheduleJob(jobDetail, trigger);
		return scheduler;
	}
	
	public static StdSchedulerFactory initFactory() throws SchedulerException{
		StdSchedulerFactory factory = new StdSchedulerFactory();
	    Properties result = new Properties();
	    result.put("org.quartz.threadPool.class","org.quartz.simpl.SimpleThreadPool");
	    result.put("org.quartz.threadPool.threadCount","1");
	    result.put("org.quartz.scheduler.instanceName","maxlong_Scheduler");
		factory.initialize(result);
		return factory;
	}
	

	
	public static void startScheduler(Scheduler scheduler) throws SchedulerException{
		scheduler.start();
	}
	

     
}

