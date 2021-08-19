package org.javacream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.impl.CounterIsbnGenerator;
import org.javacream.books.warehouse.impl.MapBooksService;
import org.javacream.store.api.StoreService;
import org.javacream.store.impl.PropertiesUtil;
import org.javacream.store.impl.SimpleStoreService;

public class ApplicationContext {

	private static BooksService booksService;
	private static StoreService storeService;
	public static BooksService booksService() {
		return booksService;
	}

	public static StoreService storeService() {
		return storeService;
	}

	static {
		MapBooksService mapBooksService = new MapBooksService();
		HashMap<String, Book> books = new HashMap<>();
		CounterIsbnGenerator counterIsbnGenerator = new CounterIsbnGenerator();
		SimpleStoreService simpleStoreService = new SimpleStoreService();
		simpleStoreService.setPropertiesUtil(new PropertiesUtil());
		List<String> categories = new ArrayList<>();
		categories.add("books");
		
		mapBooksService.setBooks(books);
		mapBooksService.setIsbnGenerator(counterIsbnGenerator);
		mapBooksService.setStoreService(simpleStoreService);
		
		simpleStoreService .setCategories(categories);
		simpleStoreService .init();
		counterIsbnGenerator.setSuffix("-de");
		
		booksService = mapBooksService;
		storeService = simpleStoreService;
	}
	
}
