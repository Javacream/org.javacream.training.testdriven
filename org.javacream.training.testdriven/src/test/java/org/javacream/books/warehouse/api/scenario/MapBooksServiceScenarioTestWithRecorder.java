package org.javacream.books.warehouse.api.scenario;

import java.util.HashMap;

import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.impl.CounterIsbnGenerator;
import org.javacream.books.warehouse.impl.MapBooksService;
import org.javacream.store.api.StoreService;
import org.javacream.store.impl.SimpleStoreService;
import org.javacream.test.util.decorators.record_play.XmlRecordingDecorator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MapBooksServiceScenarioTestWithRecorder {

	private BooksService booksService;

	@Before public void init() {
		MapBooksService booksService = new MapBooksService();
		booksService.setBooks(new HashMap<String, Book>());
		booksService.setIsbnGenerator(new CounterIsbnGenerator());
		SimpleStoreService simpleStoreService = new SimpleStoreService();
		StoreService recorder = XmlRecordingDecorator.decorate(simpleStoreService, "store.xml");
		booksService.setStoreService(recorder);
		this.booksService = booksService;
	}
	@Test public void generatedBookIsFound() {
		final String TITLE = "TESTDRIVEN";
		String isbn = booksService.newBook(TITLE);
		Assert.assertNotNull(isbn);
		Book book = booksService.findBookByIsbn(isbn);
		Assert.assertEquals(TITLE, book.getTitle());
		booksService.findBookByIsbn(isbn);
		String isbn2 = booksService.newBook("TITLE");
		booksService.findBookByIsbn(isbn2);
		
	}
}

