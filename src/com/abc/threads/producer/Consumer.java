package com.abc.threads.producer;

import org.jboss.logging.Logger;

public class Consumer extends Thread{
	private static Logger logger = Logger.getLogger(Consumer.class);
	private ICubbyHole cubbyhole;
	private int number;

	public Consumer(ICubbyHole c, int number) {
		cubbyhole = c;
		this.number = number;
	}

	public void run() {
		logger.info("Consumer is starting ...");
//		int value = 0;
//		for (int i = 0; i < 10; i++) {
//			value = cubbyhole.get();
//			System.out.println("Consumer #" + this.number + " got: " + value);
//		}
		logger.info("Consumer finished.");
		
		cubbyhole.testThread(1);
	}
}
