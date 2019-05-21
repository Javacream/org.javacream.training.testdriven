package org.javacream.books.warehouse.business;

import org.javacream.util.testdriver.GenericDummy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AbsurdCounterIsbnGeneratorNextIsbnTest {

	private IsbnGenerator isbnGenerator;

	private static final String SUFFIX = "-TEST-SUFFIX";
	@Before public void init(){
		isbnGenerator = GenericDummy.createDummy(IsbnGenerator.class);
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
	public void testSuffix(){
		String isbn = isbnGenerator.nextIsbn();
		Assert.assertTrue("ISBN must end with " + SUFFIX, isbn.endsWith(SUFFIX));
	}

}
