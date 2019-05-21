package org.javacream.books.warehouse.api.operations;

import java.util.HashMap;

import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.api.IsbnGenerator;
import org.javacream.books.warehouse.business.MapBooksService;
import org.javacream.util.testdriver.GenericDummy;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class MapBooksServiceNewBookUnitTest {

	private static final String TITLE = "Title";
	private static final String TWO_LETTER_TITLE = "AB";

	private static BooksService booksService;

	@BeforeClass
	public static void testBooksService() {
		
		MapBooksService mapBooksService = new MapBooksService();
		IsbnGenerator counterIsbnGenerator = GenericDummy.createDummy(IsbnGenerator.class);
		HashMap<String, Book> testdata = new HashMap<>();

		mapBooksService.setIsbnGenerator(counterIsbnGenerator);
		mapBooksService.setBooks(testdata);
		booksService = mapBooksService;
	}

	@Test
	public void testValidTitleGeneratesBook() {

		booksService.newBook(TITLE);
	}

	@Test
	public void testValidTitleGeneratesExpectedIsbn() {

		Assert.assertEquals("Hugo", booksService.newBook(TITLE));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testTwoLetterTitleIsInvalid() {
		booksService.newBook(TWO_LETTER_TITLE);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testNullTitleIsInvalid() {
		booksService.newBook(null);
	}

}
