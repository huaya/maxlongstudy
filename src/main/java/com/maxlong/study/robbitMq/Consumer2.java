package com.maxlong.study.robbitMq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeoutException;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2017/4/10 10:50
 *          类说明
 */
public class Consumer2 {


    private final static String EXCHANGE_NAME = "abacus.notice.exchange.webgate.dev.mxl";

    public static void main(String[] args) throws IOException, ShutdownSignalException, ConsumerCancelledException, InterruptedException, TimeoutException {
        // 获取不同的pid,方便标识不同的消费者
        String name = ManagementFactory.getRuntimeMXBean().getName();
        String pid = name.split("@")[0];
        // 创建连接和channel
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("172.28.250.43");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("admin");
        Connection conn = factory.newConnection();
        Channel channel = conn.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "direct",true);
        // 由RabbitMQ自行创建的临时队列,唯一且随消费者的中止而自动删除的队列
        String queueName = channel.queueDeclare().getQueue();
        // binding
        channel.queueBind("", EXCHANGE_NAME, "abacus.notice.routekey.webgate.dev.mxl");

        System.out.println(pid + "已经创建,正在等待消息...");

        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 指定队列消费者
        channel.basicConsume(queueName, true, consumer);

        while (true) {
            // Delivery : 封装了消息,消息的载体
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String recieveMsg = new String(delivery.getBody());
            System.out.println(pid + "接收到了消息: " + recieveMsg);
        }
    }
}
