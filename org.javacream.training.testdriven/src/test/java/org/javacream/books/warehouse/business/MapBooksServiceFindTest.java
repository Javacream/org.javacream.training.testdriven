package org.javacream.books.warehouse.business;

import org.javacream.books.warehouse.Book;
import org.javacream.books.warehouse.business.MapBooksService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class MapBooksServiceFindTest {

	private static String isbn;

	private static final String TITLE = "Title1";

	private static MapBooksService booksService;

	private String UNKNOWN_ISBN = "UNKNOWN ISBN";


	@BeforeClass
	public static void testBooksService() {
		booksService = new MapBooksService();
		isbn = booksService.newBook(TITLE);
	}

	@Test
	public void testValidIsbnRetrievesBookWithExpectedTitle() {

		Book book = booksService.findBookByIsbn(isbn);
		Assert.assertEquals("Title must be " + TITLE, TITLE, book.getTitle());
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testNullIsbnIsInvalid(){
		booksService.newBook(null);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testUnknownIsbnIsInvalid(){
		booksService.findBookByIsbn(UNKNOWN_ISBN);
	}

}
