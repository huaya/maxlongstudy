package com.maxlong.study;

import com.maxlong.study.service.UserService;
import com.maxlong.study.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2018/6/17 0:49
 * 类说明: spring bean 默认是单例，多线程更改bean属性值，影响后续线程所有值,
 * scope="prototype"设置不会影响
 */
@Slf4j
public class SpringSington {

    private static CountDownLatch countDownLatch = new CountDownLatch(100);

    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        applicationContext.start();

        new Thread(){

            @Override
            public void run(){
                applicationContext.start();

            }
        }.start();

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i<100; i++){
            final int threadIndex = i;
            executorService.execute(() -> {
                UserService userService = (UserService) applicationContext.getBean("userService");

//                User user = new User(userService.getUser().getName(),userService.getUser().getAge());
                User user = userService.getUser();
                if(threadIndex == 15){
                    user.setAge("30");
                    user.setName("dsdsdsd");
                }

                log.info("name:{},age:{}", userService.getUser().getName(), userService.getUser().getAge());
                countDownLatch.countDown();
            });

        }
        countDownLatch.await();
        executorService.shutdown();

    }
}
