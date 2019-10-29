package org.javacream.books.order.business;

import java.util.Random;

public class OrderIdGenerator {
	private Random random;
	
	public OrderIdGenerator(long seed){
		random = new Random(seed);
	}
	public long nextId() {
		return random.nextLong();
	}
}