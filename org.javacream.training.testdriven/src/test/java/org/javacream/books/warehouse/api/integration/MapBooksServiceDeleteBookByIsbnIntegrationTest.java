package org.javacream.books.warehouse.api.integration;

import org.javacream.ApplicationContext;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.test.SpecifiedBy;
import org.junit.Before;
import org.junit.Test;

@SpecifiedBy(url="https://github.com/Javacream/org.javacream.training.testdriven/blob/integrata_18.8.2021/specs/l%C3%B6scheBuch.txt")
public class MapBooksServiceDeleteBookByIsbnIntegrationTest {

	private BooksService booksService;
	private String VALID_ISBN = "";
	private String INVALID_ISBN = "$%$&$";
	private String TITLE = "Java";
	@Before public void init() {
		booksService = ApplicationContext.booksService();
		VALID_ISBN = booksService.newBook(TITLE);
	}
	
	@Test public void theCreatedIsbnDeletesBook() {
		booksService.deleteBookByIsbn(VALID_ISBN);
	}

	@Test(expected=IllegalArgumentException.class) public void invalidIsbnThrowsIllegalArgument() {
		booksService.deleteBookByIsbn(INVALID_ISBN);
	}
}
