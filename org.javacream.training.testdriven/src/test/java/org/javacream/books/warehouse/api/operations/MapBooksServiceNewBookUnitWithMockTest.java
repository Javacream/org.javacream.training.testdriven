package org.javacream.books.warehouse.api.operations;

import java.util.HashMap;

import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.api.util.IsbnGeneratorMockUtil;
import org.javacream.books.warehouse.business.MapBooksService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class MapBooksServiceNewBookUnitWithMockTest {

	private static final String ISBN = "ISBN-1-2-3";
	private static final String TITLE = "Title";
	private static final String TWO_LETTER_TITLE = "AB";

	private static BooksService booksService;

	@BeforeClass
	public static void testBooksService() {
		
		MapBooksService mapBooksService = new MapBooksService();
		HashMap<String, Book> testdata = new HashMap<>();

		mapBooksService.setIsbnGenerator(IsbnGeneratorMockUtil.build(ISBN));
		mapBooksService.setBooks(testdata);
		booksService = mapBooksService;
	}

	@Test
	public void testValidTitleGeneratesBook() {

		booksService.newBook(TITLE);
	}

	@Test
	public void testValidTitleGeneratesExpectedIsbn() {

		Assert.assertEquals(ISBN, booksService.newBook(TITLE));
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
