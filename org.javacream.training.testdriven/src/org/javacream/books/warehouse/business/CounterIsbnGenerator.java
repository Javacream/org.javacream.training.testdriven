package org.javacream.books.warehouse.business;

import org.javacream.books.warehouse.api.IsbnGenerator;

public class CounterIsbnGenerator implements IsbnGenerator{

	private int counter;
	private String suffix;
	
	/* (non-Javadoc)
	 * @see org.javacream.books.warehouse.business.IsbnGenerator#nextIsbn()
	 */
	@Override
	public String nextIsbn() {
		return counter++ + "-123-12345-1" + suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

}
