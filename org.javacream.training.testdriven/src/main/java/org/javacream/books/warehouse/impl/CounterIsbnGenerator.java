package org.javacream.books.warehouse.impl;


public class CounterIsbnGenerator{

	private int counter;
	private String suffix;
	
	public String nextIsbn() {
		return counter++ + "-123-12345-1" + suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

}
