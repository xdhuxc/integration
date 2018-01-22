package com.xdhuxc.rabbitmq;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang.SerializationUtils;

import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

public class QueueConsumer extends EndPoint implements Runnable, Consumer {

	public QueueConsumer(String endPointName) throws IOException, TimeoutException{
		super(endPointName);		
	}
	
	public void run() {
		try {
			//开始消费消息，自动识别消息。
			channel.basicConsume(endPointName, true,this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 当消费者注册时被调用。
	 */
	public void handleConsumeOk(String consumerTag) {
		System.out.println("Consumer "+consumerTag +" registered");		
	}

	public void handleCancel(String consumerTag) {}
	public void handleCancelOk(String consumerTag) {}
	public void handleRecoverOk(String consumerTag) {}
	public void handleShutdownSignal(String consumerTag, ShutdownSignalException arg1) {}

    /**
     *  当新消息产生时被调用。
     */
	public void handleDelivery(String consumerTag, Envelope env, com.rabbitmq.client.AMQP.BasicProperties props, byte[] body)
			throws IOException {
		Map map = (HashMap)SerializationUtils.deserialize(body);
	    System.out.println("Message Number "+ map.get("message number") + " received.");
	}
}
