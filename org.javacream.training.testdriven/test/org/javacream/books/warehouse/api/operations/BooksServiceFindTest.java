package org.javacream.books.warehouse.api.operations;

import org.javacream.application.ApplicationContext;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BooksService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BooksServiceFindTest {

	private static String ISBN;

	private static final String TITLE = "Title1";

	private BooksService booksService;

	private String UNKNOWN_ISBN = "UNKNOWN ISBN";


	@Before
	public void init() {
		booksService = ApplicationContext.booksService();
		ISBN = booksService.newBook(TITLE);
	}

	@Test
	public void testValidIsbnRetrievesBookWithExpectedTitle() {

		Book book = booksService.findBookByIsbn(ISBN);
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
