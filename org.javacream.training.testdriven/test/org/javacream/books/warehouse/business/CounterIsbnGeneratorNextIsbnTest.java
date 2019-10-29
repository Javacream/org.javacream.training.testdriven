package org.javacream.books.warehouse.business;

import org.javacream.books.warehouse.business.CounterIsbnGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CounterIsbnGeneratorNextIsbnTest {

	private CounterIsbnGenerator isbnGenerator;

	private static final String SUFFIX = "-TEST";
	@Before public void init(){
		isbnGenerator = new CounterIsbnGenerator();
		isbnGenerator.setSuffix(SUFFIX);
	}

	@Test
	public void testGeneratedIsbnHasProvidedSuffix(){
		String isbn = isbnGenerator.nextIsbn();
		Assert.assertTrue("ISBN must end with " + SUFFIX, isbn.endsWith(SUFFIX));
	}
	@Test
	public void testFirstPartOfIsbnIsIncremented(){
		String isbn1 = isbnGenerator.nextIsbn();
		String isbn2 = isbnGenerator.nextIsbn();
		int isbnFirstPartNumber1 = Integer.parseInt(isbn1.substring(0, isbn1.indexOf("-")));
		int isbnFirstPartNumber2 = Integer.parseInt(isbn2.substring(0, isbn2.indexOf("-")));
		Assert.assertTrue(isbnFirstPartNumber2 == (isbnFirstPartNumber1+ 1));
	}

}
