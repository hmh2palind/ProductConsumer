// File Name : ThreadClassDemo.java
public class ThreadClassDemo {
	public static void main(String[] args) {
		PrintDemo PD = new PrintDemo();

		ThreadDemo T1 = new ThreadDemo("Thread - 1 ", PD);
		ThreadDemo T2 = new ThreadDemo("Thread - 2 ", PD);

		T1.start();
		T2.start();

		// wait for threads to end
		try {
			T1.join();
			T2.join();
		} catch (Exception e) {
			System.out.println("Interrupted");
		}

		/*Runnable hello = new DisplayMessage("Hello");
		Thread thread1 = new Thread(hello);
		thread1.setDaemon(true);
		thread1.setName("hello");
		System.out.println("Starting hello thread...");
		thread1.start();

		Runnable bye = new DisplayMessage("Goodbye");
		Thread thread2 = new Thread(bye);
		thread2.setPriority(Thread.MIN_PRIORITY);
		thread2.setDaemon(true);
		System.out.println("Starting goodbye thread...");
		thread2.start();

		System.out.println("Starting thread3...");
		Thread thread3 = new GuessANumber(27);
		thread3.start();
		try {
			thread3.join();
		} catch (InterruptedException e) {
			System.out.println("Thread interrupted.");
		}
		System.out.println("Starting thread4...");
		Thread thread4 = new GuessANumber(75);

		thread4.start();
		System.out.println("main() is ending...");*/
	}
}