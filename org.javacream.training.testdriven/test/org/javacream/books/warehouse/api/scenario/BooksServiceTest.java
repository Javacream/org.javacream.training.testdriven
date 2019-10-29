package org.javacream.books.warehouse.api.scenario;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.HashMap;

import org.javacream.application.ApplicationContext;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BooksService;
import org.junit.Before;
import org.junit.Test;

public class BooksServiceTest {

	private BooksService booksService;
	private static final String UNKNOWN_ISBN = "##ISBN##";
	private static final double PRICE = 9.99;
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
			assertNotNull(isbn);
			Book book = booksService.findBookByIsbn(isbn);
			assertNotNull(book);

			try {
				booksService.findBookByIsbn(UNKNOWN_ISBN);
				fail("IllegalArgumentException must be thrown!");
			} catch (IllegalArgumentException e) {
				// OK
			}
			HashMap<String, Object> updatePrice = new HashMap<>();
			updatePrice.put("price", PRICE);
			booksService.updateBook(isbn, updatePrice);
			book = booksService.findBookByIsbn(isbn);
			assertEquals(PRICE, book.getPrice(), 1e-9);
			booksService.deleteBookByIsbn(isbn);
			try {
				booksService.deleteBookByIsbn(isbn);
				fail("IllegalArgumentException must be thrown!");
			} catch (IllegalArgumentException e) {
				// OK
			}

		} catch (IllegalArgumentException bookException) {
			bookException.printStackTrace();
			fail(bookException.getMessage());
		}

	}

}
