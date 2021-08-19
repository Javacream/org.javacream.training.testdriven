package org.javacream.training.books.warehouse.api.unit;

import java.util.HashMap;

import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.impl.MapBooksService;
import org.javacream.store.api.StoreService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class MapBooksServiceFindBookByIsbnUnitTestWithMock {

	private MapBooksService booksService;
	private String VALID_ISBN = "ISBN1";
	private String VALID_ISBN2 = "ISBN2";
	private String INVALID_ISBN = "$%$&$";
	private String TITLE = "Java";
	@Before public void init() {
		booksService = new MapBooksService();
		HashMap<String, Book> testData = new HashMap<>();
		Book testBook  = new Book();
		testBook.setIsbn(VALID_ISBN);
		testBook.setTitle(TITLE);
		testData.put(VALID_ISBN, testBook);
		Book testBook2  = new Book();
		testBook.setIsbn(VALID_ISBN2);
		testBook.setTitle(TITLE);
		testData.put(VALID_ISBN2, testBook2);
		StoreService mock = Mockito.mock(StoreService.class);
		Mockito.when(mock.getStock("Books", VALID_ISBN)).thenReturn(1);
		Mockito.when(mock.getStock("Books", VALID_ISBN2)).thenReturn(0);
		
		booksService.setStoreService(mock);
		booksService.setBooks(testData);
	}
	
	@Test public void validIsbnFindsAvailableBook() {
		Book book = booksService.findBookByIsbn(VALID_ISBN);
		Assert.assertTrue(book.isAvailable());
	}
	@Test public void validIsbn2FindsUnavailableBook() {
		Book book = booksService.findBookByIsbn(VALID_ISBN2);
		Assert.assertFalse(book.isAvailable());
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


}


