package com.xdhuxc.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class EndPoint {
	protected Channel channel;
    protected Connection connection;
    protected String endPointName;
	
    public EndPoint(String endpointName) throws IOException, TimeoutException{
         this.endPointName = endpointName;
		
         //创建ConnectionFactory
         ConnectionFactory connectionFactory = new ConnectionFactory();

        /**
         * 设置RabbitMQ的基本信息。
         */
        /**
         * RabbitMQ 的权限管理。
         * 在 RabbitMQ 中可以虚拟消息服务器 VirtualHost，每个VirtualHost相当于一个相对独立的RabbitMQ服务器，
         * 每个 VirtualHost 之间是相互隔离的，exchange、queue、message之间不能互通。
         */
        connectionFactory.setVirtualHost("/");
        connectionFactory.setHost("172.20.1.161");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin123");
		 
         //获取Connection对象
         connection = connectionFactory.newConnection();
	    
         //创建Channel对象
         channel = connection.createChannel();

        /**
         * 为此channel声明一个queue，如果queue不存在，则会创建一个。
         */
         channel.queueDeclare(endpointName, false, false, false, null);
    }
	
	
    /**
     *  关闭channel 和 connection。并非必须，因为隐含是自动调用的。
     * @throws IOException
     * @throws TimeoutException 
     */
     public void close() throws IOException, TimeoutException{
         if (null != channel) {
             this.channel.close();
         }
         if (null != connection) {
            this.connection.close();
         }
     }
}
