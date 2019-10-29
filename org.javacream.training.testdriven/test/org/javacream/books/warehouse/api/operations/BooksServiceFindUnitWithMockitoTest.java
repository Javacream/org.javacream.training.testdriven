package org.javacream.books.warehouse.api.operations;

import java.util.HashMap;

import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.api.StoreService;
import org.javacream.books.warehouse.business.MapBooksService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class BooksServiceFindUnitWithMockitoTest {

	private static String ISBN = "ISBN1";

	private static final String TITLE = "Title1";

	private BooksService booksService;

	private String UNKNOWN_ISBN = "UNKNOWN ISBN";


	@Before
	public void init() {
		MapBooksService mapBooksService = new MapBooksService();
		HashMap<String, Book> books = new HashMap<>();
		Book book = new Book();
		book.setTitle(TITLE);
		books.put(ISBN, book);
		mapBooksService.setBooks(books);
		StoreService mocked = Mockito.mock(StoreService.class);
		Mockito.when(mocked.getStock(Mockito.anyString(), Mockito.anyString())).thenReturn(0);
		mapBooksService.setStoreService(mocked);
		booksService = mapBooksService;
	}

	@Test
	public void testValidIsbnRetrievesBookWithExpectedTitle() {
		Book book = booksService.findBookByIsbn(ISBN);
		Assert.assertEquals("Title must be " + TITLE, TITLE, book.getTitle());
		Assert.assertFalse("Availabilty must be false ", book.isAvailable());
	}
	

	@Test(expected=IllegalArgumentException.class) 
	public void testUnknownIsbnIsInvalid(){
		booksService.findBookByIsbn(UNKNOWN_ISBN);
	}

}
