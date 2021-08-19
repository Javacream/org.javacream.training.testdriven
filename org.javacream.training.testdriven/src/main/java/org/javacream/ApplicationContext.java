package org.javacream;

import java.util.HashMap;

import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.impl.CounterIsbnGenerator;
import org.javacream.books.warehouse.impl.MapBooksService;
import org.javacream.books.warehouse.impl.SimpleStoreService;

public class ApplicationContext {

	private static MapBooksService booksService;
	public static BooksService booksService() {
		return booksService;
	}
	static {
		booksService = new MapBooksService();
		HashMap<String, Book> books = new HashMap<>();
		CounterIsbnGenerator counterIsbnGenerator = new CounterIsbnGenerator();
		SimpleStoreService storeService = new SimpleStoreService();
		
		
	}
	
	
	
}
