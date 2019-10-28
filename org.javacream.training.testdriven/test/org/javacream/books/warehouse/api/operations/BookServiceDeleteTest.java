package org.javacream.books.warehouse.api.operations;

import org.javacream.application.ApplicationContext;
import org.javacream.books.warehouse.api.BooksService;
import org.junit.Before;
import org.junit.Test;

public class BookServiceDeleteTest {

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
	public void testValidIsbnDeletesBook() {

		booksService.deleteBookByIsbn(ISBN);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testNullIsbnIsInvalid(){
		booksService.deleteBookByIsbn(null);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testUnknownIsbnIsInvalid(){
		booksService.deleteBookByIsbn(UNKNOWN_ISBN);
	}

}
