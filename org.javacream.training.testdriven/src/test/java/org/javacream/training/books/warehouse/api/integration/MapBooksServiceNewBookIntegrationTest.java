package org.javacream.training.books.warehouse.api.integration;

import org.javacream.ApplicationContext;
import org.javacream.books.warehouse.api.BooksService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MapBooksServiceNewBookIntegrationTest {

	private BooksService booksService;

	@Before public void init() {
		booksService = ApplicationContext.booksService();
	}
	
	@Test public void anNonEmptyTitleCreatesIsbn() {
		String isbn = booksService.newBook("NEW");
		Assert.assertNotNull(isbn);
	}

	@Test(expected=IllegalArgumentException.class) public void nullTitleThrowsIllegalArgument() {
		booksService.newBook(null);
	}
	@Test(expected=IllegalArgumentException.class) public void emptyTitleThrowsIllegalArgument() {
		booksService.newBook("");
	}
}
