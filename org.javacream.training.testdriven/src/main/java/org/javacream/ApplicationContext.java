package org.javacream;

import java.util.HashMap;

import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.impl.CounterIsbnGenerator;
import org.javacream.books.warehouse.impl.MapBooksService;
import org.javacream.store.api.StoreService;
import org.javacream.store.impl.SimpleStoreService;

public class ApplicationContext {

	private static MapBooksService booksService;
	private static SimpleStoreService storeService;
	public static BooksService booksService() {
		return booksService;
	}

	public static StoreService storeService() {
		return storeService;
	}

	static {
		booksService = new MapBooksService();
		HashMap<String, Book> books = new HashMap<>();
		CounterIsbnGenerator counterIsbnGenerator = new CounterIsbnGenerator();
		storeService = new SimpleStoreService();
		
		booksService.setBooks(books);
		booksService.setIsbnGenerator(counterIsbnGenerator);
		booksService.setStoreService(storeService);
		
		counterIsbnGenerator.setSuffix("-de");
	}
	
	
	
}
