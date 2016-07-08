package com.tutorial.thread;

public class ThreadTest extends Thread {
	private int n;
	
	public ThreadTest(int n) {
		this.n = n;
	}
	
	public static void main(String[] args) {
		ThreadTest t1 = new ThreadTest(1);
		ThreadTest t2 = new ThreadTest(10);
		ThreadTest t3 = new ThreadTest(100);
		ThreadTest t4 = new ThreadTest(1000);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		System.out.println(Thread.currentThread().getName() + ": is running");
	}

	@Override
	public void run() {
		Table.printTable(n);
	}
	
	
}

class Table {
	synchronized static void printTable(int n) {
		for (int i = 1; i <= 10; i++) {
			System.out.println(n * i);
			try {
				Thread.sleep(4);
			} catch (Exception e) {
			}
		}
	}
}

class ShareData {
	private static ShareData data;
	
	private ShareData(){}
	
	public static ShareData getInstance()
	{
		synchronized (ShareData.class) {
			if (data == null) {
				data = new ShareData();
			}
		}
		return data;
	}
	
	private boolean running = false;

	public void execute() {
		String name = Thread.currentThread().getName();
		
		synchronized (this) {
			if (running) {
				System.out.println(name + ": This method is processing. Blocking");
				return;
			}
			running = true;
		}

		try {
			System.out.println(name + ": The processing ....... ");
		} finally {
			running = false;
		}

		System.out.println(name + ": End the processing ....... ");
	}
}
/**
 * Get instance of this singleton
 *
 * @return instance
 */
/*public static RuleCache getInstance()
{
    if (instance != null)
        return instance;

    synchronized (RuleCache.class)
    {
        if (instance == null)
        {
            instance = new RuleCache();
        }
    }

    return instance;
}*/