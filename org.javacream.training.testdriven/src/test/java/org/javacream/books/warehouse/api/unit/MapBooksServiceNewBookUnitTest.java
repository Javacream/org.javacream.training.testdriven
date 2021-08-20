package org.javacream.books.warehouse.api.unit;

import java.util.HashMap;

import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.impl.CounterIsbnGenerator;
import org.javacream.books.warehouse.impl.MapBooksService;
import org.javacream.test.util.decorators.Decorator;
import org.javacream.test.util.decorators.LogDecoratorCallback;
import org.javacream.test.util.decorators.PerformanceDecoratorCallback;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class MapBooksServiceNewBookUnitTest {

	private BooksService booksService;
	private CounterIsbnGenerator isbnGenerator;
	private static final String ISBN = "ISBN42";
	@Before public void init() {
		MapBooksService booksService = new MapBooksService();
		booksService.setBooks(new HashMap<String, Book>());

		isbnGenerator = Mockito.mock(CounterIsbnGenerator.class);
		Mockito.when(isbnGenerator.nextIsbn()).thenReturn(ISBN);
		booksService.setIsbnGenerator(isbnGenerator);
		BooksService performanceDecorated = Decorator.decorate(booksService, new PerformanceDecoratorCallback());
		BooksService logDecorated = Decorator.decorate(performanceDecorated, new LogDecoratorCallback());
		
		this.booksService = logDecorated;
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
