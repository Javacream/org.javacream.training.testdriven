package org.javacream.training.books.warehouse.business;

import org.javacream.books.warehouse.Book;
import org.javacream.books.warehouse.business.MapBooksService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MapBooksServiceFindBookByIsbnIntegrationTest {

	private MapBooksService mapBooksService;
	private String VALID_ISBN = "";
	private String INVALID_ISBN = "$%$&$";
	private String TITLE = "Java";
	@Before public void init() {
		mapBooksService = new MapBooksService();
		VALID_ISBN = mapBooksService.newBook(TITLE);
	}
	
	@Test public void theCreatedIsbnFindsBook() {
		Book book = mapBooksService.findBookByIsbn(VALID_ISBN);
		Assert.assertNotNull(book);
	}
	@Test public void theCreatedIsbnFindsBookWithTitleJava() {
		Book book = mapBooksService.findBookByIsbn(VALID_ISBN);
		Assert.assertEquals(TITLE, book.getTitle());
	}

	@Test(expected=IllegalArgumentException.class) public void invalidIsbnThrowsIllegalArgument() {
		mapBooksService.findBookByIsbn(INVALID_ISBN);
	}
}
