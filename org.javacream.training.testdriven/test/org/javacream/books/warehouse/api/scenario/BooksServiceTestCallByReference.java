package org.javacream.books.warehouse.api.scenario;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.HashMap;

import org.javacream.application.ApplicationContext;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BooksService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BooksServiceTestCallByReference {

	private BooksService booksService;
	private static final String TITLE = "TESTING";
	
	@Before
	public void init() {
		booksService = ApplicationContext.booksService();
	}

	@Test
	public void testBooksSequence() {
		doTest(booksService);
	}

	private void doTest(BooksService booksService) {

		try {
			String isbn = booksService.newBook(TITLE);
			Book book = booksService.findBookByIsbn(isbn);
			Assert.assertEquals(TITLE, book.getTitle());
			Assert.assertEquals(0, book.getPrice(), 1e-9);
			book.setPrice(99.99);
			Book book2 = booksService.findBookByIsbn(isbn);
			Assert.assertEquals(0, book2.getPrice(), 1e-9);
			
		} catch (IllegalArgumentException bookException) {
			bookException.printStackTrace();
			fail(bookException.getMessage());
		}

	}

}
