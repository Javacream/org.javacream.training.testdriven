package org.javacream.books.warehouse.business;

import java.util.HashMap;

import org.javacream.books.warehouse.Book;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class MapBooksServiceNewBookTest {

	private static String isbn1;
	private static String isbn2;

	private static final String TITLE1 = "Title1";
	private static final String TITLE2 = "Title2";
	private static final String TWO_LETTER_TITLE = "AB";

	private static BooksService booksService;

	@BeforeClass
	public static void testBooksService() {
		
		MapBooksService mapBooksService = new MapBooksService();
		CounterIsbnGenerator counterIsbnGenerator = new CounterIsbnGenerator();
		HashMap<String, Book> testdata = new HashMap<>();

		mapBooksService.setIsbnGenerator(counterIsbnGenerator);
		mapBooksService.setBooks(testdata);
		counterIsbnGenerator.setSuffix("-test");
		booksService = mapBooksService;
	}

	@Test
	public void testValidTitleGeneratesValidIsbn() {

		isbn1 = booksService.newBook(TITLE1);

		Assert.assertNotNull("Generated ISBN was null!", isbn1);
		Assert.assertTrue("ISBN must have special format", isbn1.indexOf("-") > 0);
	}

	@Test
	public void testGeneratedIsbnsAreUnique() {

		isbn1 = booksService.newBook(TITLE1);
		isbn2 = booksService.newBook(TITLE2);

		Assert.assertNotSame("Isbn must be unique", isbn1, isbn2);
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
