package com.abc.threads.producer;

public class ProducerConsumerMain {
	public static void main(String[] args) {
		ICubbyHole c = CubbyHole.getInstance();
		Producer p1 = new Producer(c, 1);
		Consumer c1 = new Consumer(c, 1);
		Consumer c2 = new Consumer(c, 2);
		p1.start();
		c1.start();
		c2.start();
	}
}
