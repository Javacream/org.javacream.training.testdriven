package org.javacream.training.books.warehouse.api;

import java.util.HashMap;

import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.impl.MapBooksService;
import org.javacream.store.api.StoreService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MapBooksServiceFindBookByIsbnUnitTest {

	private MapBooksService booksService;
	private String VALID_ISBN = "";
	private String INVALID_ISBN = "$%$&$";
	private String TITLE = "Java";
	@Before public void init() {
		booksService = new MapBooksService();
		HashMap<String, Book> testData = new HashMap<>();
		Book testBook  = new Book();
		testBook.setIsbn(VALID_ISBN);
		testBook.setTitle(TITLE);
		testData.put(VALID_ISBN, testBook);
		booksService.setStoreService(new TestStoreService());
		booksService.setBooks(testData);
	}
	
	@Test public void theCreatedIsbnFindsAvailableBook() {
		Book book = booksService.findBookByIsbn(VALID_ISBN);
		Assert.assertTrue(book.isAvailable());
	}

	@Test public void theCreatedIsbnFindsBook() {
		Book book = booksService.findBookByIsbn(VALID_ISBN);
		Assert.assertNotNull(book);
	}
	@Test public void theCreatedIsbnFindsBookWithTitleJava() {
		Book book = booksService.findBookByIsbn(VALID_ISBN);
		Assert.assertEquals(TITLE, book.getTitle());
	}

	@Test(expected=IllegalArgumentException.class) public void invalidIsbnThrowsIllegalArgument() {
		booksService.findBookByIsbn(INVALID_ISBN);
	}

	class TestStoreService implements StoreService{

		@Override
		public int getStock(String category, String id) {
			return 42;
		}
		
	}
}


