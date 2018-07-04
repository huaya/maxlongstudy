package com.maxlong.study.quartz;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/** 
* @author ���� maxlong: 
* @version ����ʱ�䣺2016��6��20�� ����10:05:23 
* ��˵�� 
*/
@Slf4j
public class TestJob2 extends QuartzJobBean {


	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		String returnstr = DateFormat.format(d);
		log.info(returnstr + "**************");
	}
}
 