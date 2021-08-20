package org.javacream.books.warehouse.api.scenario;

import org.javacream.ApplicationContext;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BooksService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MapBooksServiceScenarioTest {

	private BooksService booksService;

	@Before public void init() {
		booksService = ApplicationContext.booksService();
	}
	@Test public void generatedBookIsFound() {
		final String TITLE = "TESTDRIVEN";
		String isbn = booksService.newBook(TITLE);
		Assert.assertNotNull(isbn);
		Book book = booksService.findBookByIsbn(isbn);
		Assert.assertEquals(TITLE, book.getTitle());
	}
}

