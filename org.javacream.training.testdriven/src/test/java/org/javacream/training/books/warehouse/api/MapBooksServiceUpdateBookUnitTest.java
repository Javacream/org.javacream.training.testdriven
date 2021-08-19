package org.javacream.training.books.warehouse.api;

import java.util.HashMap;
import java.util.Map;

import org.javacream.ApplicationContext;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.test.SpecifiedBy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


@SpecifiedBy(url="https://github.com/Javacream/org.javacream.training.testdriven/blob/integrata_18.8.2021/specs/l%C3%B6scheBuch.txt")
public class MapBooksServiceUpdateBookUnitTest {

	private BooksService booksService;
	private String VALID_ISBN = "";
	private String INVALID_ISBN = "$%$&$";
	private String TITLE = "Java";
	private Map<String, Object> VALID_OPTIONS = new HashMap<>();
	private Map<String, Object> EMPTY_OPTIONS = new HashMap<>();
	
	@Before public void init() {
		booksService = ApplicationContext.booksService();
		VALID_ISBN = booksService.newBook(TITLE);
		VALID_OPTIONS.put("title", "CHANGED");
		VALID_OPTIONS.put("price", 9.99);
	}
	
	@Test public void updateBookWithValidIsbnAndTitle() {
		booksService.updateBook(VALID_ISBN, VALID_OPTIONS);
		Book book = booksService.findBookByIsbn(VALID_ISBN);
		Assert.assertEquals(VALID_OPTIONS.get("title"), book.getTitle());
	}

	@Test(expected=IllegalArgumentException.class) public void invalidIsbnThrowsIllegalArgument() {
		booksService.updateBook(INVALID_ISBN, VALID_OPTIONS);
	}
	@Test public void emptyOptionsDoNothing() {
		booksService.updateBook(VALID_ISBN, EMPTY_OPTIONS);
	}
	@Test public void nullOptionsDoNothing() {
		booksService.updateBook(VALID_ISBN, null);
	}
}
