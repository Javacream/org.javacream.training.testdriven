package org.javacream.books.warehouse.api.operations;

import org.javacream.application.ApplicationContext;
import org.javacream.books.warehouse.api.IsbnGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IsbnGeneratorNextIsbnTest {

	private IsbnGenerator isbnGenerator;

	@Before public void init(){
		isbnGenerator = ApplicationContext.isbnGenerator();
	}

	@Test
	public void testNextIsbnHasExpectedFormat() {
		String isbn = isbnGenerator.nextIsbn();
		Assert.assertTrue("ISBN must have special format", isbn.indexOf("-") > 0);
	}
	@Test
	public void testNextIsbnRetrievesNonNullIsbn() {
		String isbn = isbnGenerator.nextIsbn();
		Assert.assertNotNull("generated ISBN must not be null!", isbn);
	}
	
	@Test
	public void testIsbnsMustBeUnique() {
		String isbn1 = isbnGenerator.nextIsbn();
		String isbn2 = isbnGenerator.nextIsbn();
		Assert.assertFalse(isbn1.equals(isbn2));
	}

}
