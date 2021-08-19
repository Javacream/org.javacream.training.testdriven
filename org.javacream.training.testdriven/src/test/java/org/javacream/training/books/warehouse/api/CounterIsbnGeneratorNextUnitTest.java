package org.javacream.training.books.warehouse.api;

import org.javacream.books.warehouse.impl.CounterIsbnGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CounterIsbnGeneratorNextUnitTest {

	private CounterIsbnGenerator counterIsbnGenerator;
	
	@Before public void init() {
		counterIsbnGenerator = new CounterIsbnGenerator();
		counterIsbnGenerator.setSuffix("-dk");
	}
	
	@Test public void isbnIsGenerated() {
		String isbn = counterIsbnGenerator.nextIsbn();
		Assert.assertNotNull(isbn);
	}
}
