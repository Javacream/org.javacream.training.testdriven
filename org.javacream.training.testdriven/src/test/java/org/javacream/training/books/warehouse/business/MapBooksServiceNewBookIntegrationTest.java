package org.javacream.training.books.warehouse.business;

import org.javacream.books.warehouse.impl.MapBooksService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MapBooksServiceNewBookIntegrationTest {

	private MapBooksService mapBooksService;

	@Before public void init() {
		mapBooksService = new MapBooksService();
	}
	
	@Test public void anNonEmptyTitleCreatesIsbn() {
		String isbn = mapBooksService.newBook("NEW");
		Assert.assertNotNull(isbn);
	}

	@Test(expected=IllegalArgumentException.class) public void nullTitleThrowsIllegalArgument() {
		mapBooksService.newBook(null);
	}
	@Test(expected=IllegalArgumentException.class) public void emptyTitleThrowsIllegalArgument() {
		mapBooksService.newBook("");
	}
}
