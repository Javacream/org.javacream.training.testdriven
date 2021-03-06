package org.javacream.books.warehouse.business;

import java.util.HashMap;

import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.business.MapBooksService;
import org.javacream.books.warehouse.business.PropertiesStoreService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MapBooksServiceFindTest {

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
		PropertiesStoreService propertiesStoreService = new PropertiesStoreService();
		propertiesStoreService.setPropertiesUtil(new PropertiesUtil());
		propertiesStoreService.initialize("books");
		booksService.setStoreService(propertiesStoreService);
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

}
