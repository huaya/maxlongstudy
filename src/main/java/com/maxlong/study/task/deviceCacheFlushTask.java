package com.maxlong.study.task;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;


/**
 * @author 作者 E-mail:ruanjianlxm@sina.com
 * @version 创建时间：2015年8月4日 下午4:44:39
 * 类说明
 */
public class deviceCacheFlushTask implements MessageListener{

    public void onMessage(Message message) {

        try {
            String receiveMsg =new String(message.getBody(),"utf-8");
            System.out.println("receiveMsg:"+receiveMsg);
        } catch (Exception e1) {
            e1.printStackTrace();
            return ;
        }
    }
}