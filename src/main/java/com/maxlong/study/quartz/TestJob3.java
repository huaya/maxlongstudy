package com.maxlong.study.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/** 
* @author ���� maxlong: 
* @version ����ʱ�䣺2016��6��17�� ����6:15:56 
* ��˵�� 
*/
@Slf4j
public class TestJob3 extends QuartzJobBean {

	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		String returnstr = DateFormat.format(d);
		log.info(returnstr+"@@@@@@@@@@@@@@@@@@");
	}
}
 