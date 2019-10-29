package org.javacream.books.warehouse.business;

import java.util.Random;

import org.javacream.books.warehouse.api.IsbnGenerator;

public class RandomIsbnGenerator implements IsbnGenerator{

	private Random random;
	private String suffix;

	{
		random = new Random(this.hashCode() + System.currentTimeMillis());
	}
	@Override
	public String nextIsbn() {
		return Math.abs(random.nextInt()) + "-123-12345-1" + suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

}
