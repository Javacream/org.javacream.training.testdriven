package org.javacream.training.books.warehouse.api.integration;

import org.javacream.ApplicationContext;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BooksService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MapBooksServiceFindBookByIsbnIntegrationTest {

	private BooksService booksService;
	private String VALID_ISBN = "";
	private String INVALID_ISBN = "$%$&$";
	private String TITLE = "Java";
	@Before public void init() {
		booksService = ApplicationContext.booksService();
		VALID_ISBN = booksService.newBook(TITLE);
	}
	
	@Test public void theCreatedIsbnFindsBook() {
		Book book = booksService.findBookByIsbn(VALID_ISBN);
		Assert.assertNotNull(book);
	}
	@Test public void theCreatedIsbnFindsBookWithTitleJava() {
		Book book = booksService.findBookByIsbn(VALID_ISBN);
		Assert.assertEquals(TITLE, book.getTitle());
	}

	@Test(expected=IllegalArgumentException.class) public void invalidIsbnThrowsIllegalArgument() {
		booksService.findBookByIsbn(INVALID_ISBN);
	}
}
