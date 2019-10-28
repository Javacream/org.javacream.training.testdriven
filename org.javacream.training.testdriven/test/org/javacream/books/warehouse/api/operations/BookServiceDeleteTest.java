package org.javacream.books.warehouse.api.operations;

import java.util.HashMap;
import java.util.Map;

import org.javacream.application.ApplicationContext;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BooksService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BookServiceDeleteTest {

	private static String ISBN;

	private static final String TITLE = "Title1";
	private static final String CHANGED_TITLE = "CHANGED";
	private static final Double CHANGED_PRICE = 18.88;
	
	private Map<String, Object> UPDATE_PRICE;
	private Map<String, Object> UPDATE_TITLE;
	private Map<String, Object> UPDATE_PRICE_AND_TITLE;
	private Map<String, Object> UPDATE_NONE;
	
	private BooksService booksService;

	private String UNKNOWN_ISBN = "UNKNOWN ISBN";


	@Before
	public void init() {
		booksService = ApplicationContext.booksService();
		ISBN = booksService.newBook(TITLE);
		
		UPDATE_NONE = new HashMap<>();
		UPDATE_PRICE = new HashMap<String, Object>();
		UPDATE_PRICE.put("price", CHANGED_PRICE);
		UPDATE_TITLE = new HashMap<String, Object>();
		UPDATE_TITLE.put("title", CHANGED_TITLE);
		UPDATE_PRICE_AND_TITLE = new HashMap<String, Object>();
		UPDATE_PRICE_AND_TITLE.put("price", CHANGED_PRICE);
		UPDATE_PRICE_AND_TITLE.put("title", "CHANGED");
		
	}

	@Test
	public void testValidIsbnWithNoneParametersDoesNotChangeBook() {

		booksService.updateBook(ISBN, UPDATE_NONE);
		Book book = booksService.findBookByIsbn(ISBN);
		Assert.assertEquals(TITLE, book.getTitle());
		Assert.assertEquals(0d, book.getPrice(), 1e-9);
	}
	@Test
	public void testValidIsbnWithPriceParametersUpdatesPrice() {
		booksService.updateBook(ISBN, UPDATE_PRICE);
		Book book = booksService.findBookByIsbn(ISBN);
		Assert.assertEquals(TITLE, book.getTitle());
		Assert.assertEquals(CHANGED_PRICE, book.getPrice(), 1e-9);
	}
	@Test
	public void testValidIsbnWithTitleParametersUpdatesTitle() {
		booksService.updateBook(ISBN, UPDATE_TITLE);
		Book book = booksService.findBookByIsbn(ISBN);
		Assert.assertEquals(CHANGED_TITLE, book.getTitle());
		Assert.assertEquals(0d, book.getPrice(), 1e-9);
	}
	@Test
	public void testValidIsbnWithTitleAndPriceParametersUpdatesTitleAndPrice() {
		booksService.updateBook(ISBN, UPDATE_PRICE_AND_TITLE);
		Book book = booksService.findBookByIsbn(ISBN);
		Assert.assertEquals(CHANGED_TITLE, book.getTitle());
		Assert.assertEquals(CHANGED_PRICE, book.getPrice(), 1e-9);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testNullIsbnIsInvalid(){
		booksService.updateBook(null, UPDATE_NONE);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void testUnknownIsbnIsInvalid(){
		booksService.updateBook(UNKNOWN_ISBN, UPDATE_NONE);
	}

}
