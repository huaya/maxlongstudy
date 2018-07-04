package com.maxlong.study.quartz;

import java.text.SimpleDateFormat;  
import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;  

/** 
* @author ���� maxlong: 
* @version ����ʱ�䣺2016��6��17�� ����6:04:29 
* ��˵�� 
*/
//����main����  
//QuartzTest.<a href="http://lib.csdn.net/base/17" class='replace_word' title="Java EE֪ʶ��" target='_blank' style='color:#df3434; font-weight:bold;'>Java</a>  

@Slf4j
public class QuartzTest {  

  /** *//** 
   * @param args 
   */  
  public static void main(String[] args) {

      SimpleDateFormat DateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
      Date d = new Date();  
      String returnstr = DateFormat.format(d);          
        
      TestJob job = new TestJob();  
      TestJob2 job2 = new TestJob2();  
      String job_name ="job_1";
      String job_name2 ="job_2";
      try {  
    	  StdSchedulerFactory factory = QuartzManager.initFactory();
          factory.initialize("quartz-cluster.properties");

    	  Scheduler scheduler = factory.getScheduler();
//    	  QuartzManager.addJob(job_name,"maxlong1",job,"0/30 * * * * ?",scheduler);
//    	  QuartzManager.addJob(job_name2,"maxlong2",job2,"0/30 * * * * ?",scheduler);
    	  QuartzManager.startScheduler(scheduler);

          log.info(returnstr+ "【系统启动】");
      }  catch (Exception e) {  
          e.printStackTrace();  
      }  
  }  
}  
 