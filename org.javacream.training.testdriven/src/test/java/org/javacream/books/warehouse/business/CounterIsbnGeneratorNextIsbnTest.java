package org.javacream.books.warehouse.business;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CounterIsbnGeneratorNextIsbnTest {

	private CounterIsbnGenerator isbnGenerator;

	private static final String SUFFIX = "-TEST-SUFFIX";
	@Before public void init(){
		isbnGenerator = new CounterIsbnGenerator();
		isbnGenerator.setSuffix(SUFFIX);
	}

	@Test
	public void testNextIsbnHasExpectedFormat() {
		String isbn = isbnGenerator.nextIsbn();
		Assert.assertTrue("ISBN must have special format", isbn.indexOf("-") > 0);
	}
	@Test
	public void testNextIsbnRetrievesNonNummIsbn() {
		String isbn = isbnGenerator.nextIsbn();
		Assert.assertNotNull("generated ISBN must not be null!", isbn);
	}
	
	@Test
	public void testSuffix(){
		String isbn = isbnGenerator.nextIsbn();
		Assert.assertTrue("ISBN must end with " + SUFFIX, isbn.endsWith(SUFFIX));
	}

}
