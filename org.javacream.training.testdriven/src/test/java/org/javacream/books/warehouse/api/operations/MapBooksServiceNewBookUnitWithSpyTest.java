package org.javacream.books.warehouse.api.operations;

import java.util.HashMap;

import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.api.IsbnGenerator;
import org.javacream.books.warehouse.business.CounterIsbnGenerator;
import org.javacream.books.warehouse.business.MapBooksService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

public class MapBooksServiceNewBookUnitWithSpyTest {

	private static final String ISBN = "0-123-12345-1-de";
	private static final String TITLE = "Title";

	private static BooksService booksService;
	private static IsbnGenerator isbnGenerator;

	@BeforeClass
	public static void testBooksService() {
		
		MapBooksService mapBooksService = new MapBooksService();
		HashMap<String, Book> testdata = new HashMap<>();
		CounterIsbnGenerator counterIsbnGenerator = new CounterIsbnGenerator();
		counterIsbnGenerator.setSuffix("-de");
		isbnGenerator = Mockito.spy(counterIsbnGenerator);
		mapBooksService.setIsbnGenerator(isbnGenerator);
		mapBooksService.setBooks(testdata);
		booksService = mapBooksService;
	}

	@Test
	public void testValidTitleGeneratesExpectedIsbn() {
		Assert.assertEquals(ISBN, booksService.newBook(TITLE));
		Mockito.verify(isbnGenerator, Mockito.times(1)).nextIsbn();
	}

}
