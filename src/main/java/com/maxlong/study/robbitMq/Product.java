package com.maxlong.study.robbitMq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2017/4/10 10:20
 *          类说明
 */
public class Product {

    private final static String EXCHANGE_NAME = "abacus.notice.exchange.webgate.dev.mxl";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("172.28.250.43");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("admin");
        factory.setPassword("admin");
        // 创建connection
        Connection conn = factory.newConnection();
        // 创建channel
        Channel channel = conn.createChannel();

        // 声明该channel是fanout类型
        channel.exchangeDeclare(EXCHANGE_NAME, "direct",true,false,null);
        Date nowDate = new Date();
        String msg = nowDate.getTime() + " have log sth...";
        // 将消息发送给exchange
        channel.basicPublish(EXCHANGE_NAME, "abacus.notice.routekey.webgate.dev.mxl", null, msg.getBytes());
        System.out.println(nowDate + " 已经生成一条日志...");
        channel.close();
        conn.close();
    }
}
