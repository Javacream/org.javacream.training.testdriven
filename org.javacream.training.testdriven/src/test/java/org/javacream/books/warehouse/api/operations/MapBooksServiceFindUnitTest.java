package org.javacream.books.warehouse.api.operations;

import java.util.HashMap;

import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.StoreService;
import org.javacream.books.warehouse.business.MapBooksService;
import org.javacream.util.testdecorator.TracingDecorator;
import org.javacream.util.testdriver.GenericDummy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MapBooksServiceFindUnitTest {

	private static String ISBN = "ISBN1";

	private static final String TITLE = "Title1";

	private Book book;
	private MapBooksService booksService;

	private String UNKNOWN_ISBN = "UNKNOWN ISBN";


	@Before
	public void init() {
		book = new Book();
		book.setTitle(TITLE);
		book.setIsbn(ISBN);
		HashMap<String, Book> testdata = new HashMap<>();
		testdata.put(ISBN, book);
		
		booksService = new MapBooksService();
		booksService.setBooks(testdata);
		StoreService storeService = GenericDummy.createDummy(StoreService.class);
		storeService = TracingDecorator.createDecorator(storeService);
		booksService.setStoreService(storeService);
		//booksService.newBook(TITLE);
	}

	@Test
	public void testValidIsbnRetrievesBookWithExpectedTitle() {

		Book book = booksService.findBookByIsbn(ISBN);
		Assert.assertEquals("Title must be " + TITLE, TITLE, book.getTitle());
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testNullIsbnIsInvalid(){
		booksService.newBook(null);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testUnknownIsbnIsInvalid(){
		booksService.findBookByIsbn(UNKNOWN_ISBN);
	}
	@Test
	public void testValidIsbnRetrievesAvailableBook() {

		Book book = booksService.findBookByIsbn(ISBN);
		Assert.assertEquals("Book must be available", true, book.isAvailable());
	}

}
