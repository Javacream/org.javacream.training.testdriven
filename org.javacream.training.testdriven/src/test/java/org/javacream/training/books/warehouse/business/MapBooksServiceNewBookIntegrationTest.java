package org.javacream.training.books.warehouse.business;

import org.javacream.books.warehouse.business.MapBooksService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MapBooksServiceNewBookIntegrationTest {

	private MapBooksService mapBooksService;

	@Before public void init() {
		mapBooksService = new MapBooksService();
	}
	@Test public void bookIsGenerated() {
		String isbn = mapBooksService.newBook("NEW");
		Assert.assertNotNull(isbn);
	}
}
