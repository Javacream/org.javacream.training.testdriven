package org.javacream.books.warehouse.api.scenario;

import java.util.HashMap;

import org.javacream.ApplicationContext;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.impl.CounterIsbnGenerator;
import org.javacream.books.warehouse.impl.MapBooksService;
import org.javacream.store.api.StoreService;
import org.javacream.test.util.decorators.Decorator;
import org.javacream.test.util.decorators.LogDecoratorCallback;
import org.javacream.test.util.decorators.record_play.XmlPlayer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MapBooksServiceScenarioTestWithPlayer {

	private BooksService booksService;

	@Before public void init() {
		MapBooksService booksService = new MapBooksService();
		booksService.setBooks(new HashMap<String, Book>());
		booksService.setIsbnGenerator(new CounterIsbnGenerator());
		StoreService player = XmlPlayer.createPlayer(StoreService.class, "store.xml");
		StoreService logging = Decorator.decorate(player, new LogDecoratorCallback());
		booksService.setStoreService(logging);
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

