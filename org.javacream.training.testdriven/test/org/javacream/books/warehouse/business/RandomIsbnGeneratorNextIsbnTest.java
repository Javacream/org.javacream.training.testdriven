package org.javacream.books.warehouse.business;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RandomIsbnGeneratorNextIsbnTest {

	private RandomIsbnGenerator isbnGenerator;

	private static final String SUFFIX = "-TEST";
	@Before public void init(){
		isbnGenerator = new RandomIsbnGenerator();
		isbnGenerator.setSuffix(SUFFIX);
	}

	@Test
	public void testGeneratedIsbnHasProvidedSuffix(){
		String isbn = isbnGenerator.nextIsbn();
		Assert.assertTrue("ISBN must end with " + SUFFIX, isbn.endsWith(SUFFIX));
	}
}
