package com.xdhuxc.rabbitmq;

import java.util.HashMap;

public class Main {

	public Main() throws Exception {
		
		QueueConsumer consumer = new QueueConsumer("queue");
		Thread consumerThread = new Thread(consumer);
		consumerThread.start();
		
		Producer producer = new Producer("queue");
		
		
		for (int i = 0; i < 100000; i++) {
			consumerThread.sleep(1000);
			HashMap message = new HashMap();
			message.put("message number", i);
			producer.sendMessage(message);
			System.out.println("Message Number "+ i +" sent.");
		}
	}
	
	/**
	 * @param args
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws Exception{
	  new Main();
	}
}
