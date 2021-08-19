package org.javacream.training.books.warehouse.business;

import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.impl.MapBooksService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MapBooksServiceScenarioTest {

	private MapBooksService mapBooksService;

	@Before public void init() {
		mapBooksService = new MapBooksService();
	}
	@Test public void generatedBookIsFound() {
		final String TITLE = "TESTDRIVEN";
		String isbn = mapBooksService.newBook(TITLE);
		Assert.assertNotNull(isbn);
		Book book = mapBooksService.findBookByIsbn(isbn);
		Assert.assertEquals(TITLE, book.getTitle());
	}
}

