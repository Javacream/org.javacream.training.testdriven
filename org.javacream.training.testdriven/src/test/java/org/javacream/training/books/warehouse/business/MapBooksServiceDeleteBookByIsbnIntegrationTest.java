package org.javacream.training.books.warehouse.business;

import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.impl.MapBooksService;
import org.javacream.test.SpecifiedBy;
import org.junit.Before;
import org.junit.Test;

@SpecifiedBy(url="https://github.com/Javacream/org.javacream.training.testdriven/blob/integrata_18.8.2021/specs/l%C3%B6scheBuch.txt")
public class MapBooksServiceDeleteBookByIsbnIntegrationTest {

	private BooksService mapBooksService;
	private String VALID_ISBN = "";
	private String INVALID_ISBN = "$%$&$";
	private String TITLE = "Java";
	@Before public void init() {
		mapBooksService = new MapBooksService();
		VALID_ISBN = mapBooksService.newBook(TITLE);
	}
	
	@Test public void theCreatedIsbnDeletesBook() {
		mapBooksService.deleteBookByIsbn(VALID_ISBN);
	}

	@Test(expected=IllegalArgumentException.class) public void invalidIsbnThrowsIllegalArgument() {
		mapBooksService.deleteBookByIsbn(INVALID_ISBN);
	}
}
