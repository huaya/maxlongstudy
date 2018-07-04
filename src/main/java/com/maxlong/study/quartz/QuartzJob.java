package com.maxlong.study.quartz;

import com.maxlong.study.quartzZookeeper.ZookeeperFactory;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2017/5/27 16:07
 *          类说明
 */
@Slf4j
public class QuartzJob {

    @Autowired
    private ZookeeperFactory zookeeperFactory;

    protected void execute() throws JobExecutionException {
        try {
            zookeeperFactory.registerSubQueueNode();
            if(zookeeperFactory.getMonopolyLock()){
                log.info(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))+"★★★★★★★★★★★");
            }else {
                log.info(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))+"@@@@@@@@@@@@@@@@@@@");
            }

//            zookeeperFactory.deleteSubQueueNode();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
