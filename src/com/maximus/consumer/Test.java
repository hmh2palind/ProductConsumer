package com.maximus.consumer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.maximus.producerconsumer.Consumer;

public class Test {
	public static void main(String[] args) throws Exception {
		Consumer consumer = new ConsumerImpl(10);

		File file = new File ("producer.dat");
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(file)));

		String line = "";

		while ((line = br.readLine()) != null) {
			System.out.println("Producer producing: " + line);
			consumer.consume(new PrintJob(line));
		}

		consumer.finishConsumption();
	
		while(true);
	}
}

class PrintJob implements Item {
	private String line;

	public PrintJob(String s) {
		line = s;
	}

	public void process() {
		System.out.println(Thread.currentThread().getName() + " consuming :"
				+ line);
	}
}