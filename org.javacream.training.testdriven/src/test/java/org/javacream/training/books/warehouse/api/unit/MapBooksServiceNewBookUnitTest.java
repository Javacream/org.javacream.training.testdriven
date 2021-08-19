package org.javacream.training.books.warehouse.api.unit;

import java.util.HashMap;

import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.impl.CounterIsbnGenerator;
import org.javacream.books.warehouse.impl.MapBooksService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class MapBooksServiceNewBookUnitTest {

	private MapBooksService booksService;
	private CounterIsbnGenerator isbnGenerator;
	private static final String ISBN = "ISBN42";
	@Before public void init() {
		booksService = new MapBooksService();
		booksService.setBooks(new HashMap<String, Book>());

		isbnGenerator = Mockito.mock(CounterIsbnGenerator.class);
		Mockito.when(isbnGenerator.nextIsbn()).thenReturn(ISBN);
		booksService.setIsbnGenerator(isbnGenerator);
	}
	
	@Test public void anNonEmptyTitleCreatesIsbn() {
		String isbn = booksService.newBook("NEW");
		Assert.assertEquals(ISBN, isbn);
		Mockito.verify(isbnGenerator).nextIsbn();
	}

	@Test(expected=IllegalArgumentException.class) public void nullTitleThrowsIllegalArgument() {
		booksService.newBook(null);
	}
	@Test(expected=IllegalArgumentException.class) public void emptyTitleThrowsIllegalArgument() {
		booksService.newBook("");
	}
}
