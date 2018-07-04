package com.maxlong.study;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main( String[] args ){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        context.start();
        context.registerShutdownHook();
        logger.info("启动成功。。。。。");
        Object lock = new Object();
        try {
            synchronized (lock){
                while (true){
                   lock.wait();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
