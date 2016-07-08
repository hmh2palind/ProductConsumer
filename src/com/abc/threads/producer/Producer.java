package com.abc.threads.producer;

import org.apache.log4j.Logger;

public class Producer extends Thread{
	private static Logger logger = Logger.getLogger(Producer.class);
	
	private ICubbyHole cubbyhole;
	private int number;

	public Producer(ICubbyHole c, int number) {
		cubbyhole = c;
		this.number = number;
	}

	public void run() {				
		logger.info("Producer is starting ....");
//		for (int i = 0; i < 10; i++) {
//			cubbyhole.put(i);
//			System.out.println("Producer #" + this.number + " put: " + i);
//			try {
//				sleep((int) (Math.random() * 100));
//			} catch (InterruptedException e) {
//				logger.error("Exception", e);
//			}
//		}
		logger.info("Producer finished");
		
		cubbyhole.testThread(0);
	}
}
