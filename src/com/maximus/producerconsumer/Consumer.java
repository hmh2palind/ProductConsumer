package com.maximus.producerconsumer;

import com.maximus.consumer.Item;

public interface Consumer {
	public boolean consume(Item j);

	public void finishConsumption();
}