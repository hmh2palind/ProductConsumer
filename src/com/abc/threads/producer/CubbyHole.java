package com.abc.threads.producer;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.log4j.Logger;

interface ICubbyHole {
	public int get();
	public void put(int value);
	public void testThread(int value);
}

public class CubbyHole implements ICubbyHole{
	private Logger logger = Logger.getLogger(getClass());
	private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private final Lock cacheWriteLock = readWriteLock.writeLock();
	private final Lock cacheLock = readWriteLock.writeLock();
	
	private int content;
	private boolean available = false;
	private static CubbyHole instance = null;
	
	private CubbyHole(){}
	
	public static CubbyHole getInstance(){
		if (instance != null)
			return instance;
		
		synchronized (CubbyHole.class) {
			if (instance == null) {
				instance = new CubbyHole();
			}
		}
		
		return instance;
	}
	
	public void testThread(int value) {
		cacheWriteLock.lock();
		if (value == 0) {
			throw new NullPointerException();
		}
		
		System.out.println("value = " + value);
		cacheWriteLock.unlock();
	}
	
	public synchronized int get() 
	{
		logger.info("starting get content");
		while (available == false) {
			try {
				wait();
			} catch (InterruptedException e) {
				logger.error("Exception is error", e);
			}
		}
		
		available = false;
		notifyAll();
		
		logger.info("finished get content");
		return content;
	}

	public synchronized void put(int value) 
	{
		//XXX
		while (available == true) {
			try {
				wait();
			} catch (InterruptedException e) {
				logger.error("Exception is error", e);
			}
		}
		
		//XXX
		
		content = value;
		
		
		available = true;
		notifyAll();
		
		//XXX
	}
}
