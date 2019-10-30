package org.javacream.application;

import java.util.HashMap;

import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.api.IsbnGenerator;
import org.javacream.books.warehouse.api.StoreService;
import org.javacream.books.warehouse.business.CounterIsbnGenerator;
import org.javacream.books.warehouse.business.MapBooksService;
import org.javacream.books.warehouse.business.PropertiesStoreService;
import org.javacream.books.warehouse.business.PropertiesUtil;
import org.javacream.books.warehouse.decorators.CountingStoreService;
import org.javacream.util.test.decorators.TracingDecorator;

public abstract class ApplicationContext {

	private static BooksService booksService;
	private static IsbnGenerator isbnGenerator;
	private static StoreService storeService;
	public static BooksService booksService() {
		return booksService;
	}
	public static IsbnGenerator isbnGenerator() {
		return isbnGenerator;
	}
	public static StoreService storeService() {
		return storeService;
	}
	
	static {
		
		//Erzeugen der Fachobjekte
		MapBooksService mapBooksService = new MapBooksService();
		CounterIsbnGenerator counterIsbnGenerator = new CounterIsbnGenerator();
		PropertiesStoreService propertiesStoreService = new PropertiesStoreService();
		HashMap<String, Book> books = new HashMap<>();
		PropertiesUtil propertiesUtil = new PropertiesUtil();

		//Erzeugen der Decorators
		CountingStoreService countingStoreService = new CountingStoreService();
		//Setzen der Abhängigkeiten unter Berücksichtigung der Decorators
		mapBooksService.setBooks(books);
		mapBooksService.setIsbnGenerator(counterIsbnGenerator);
		countingStoreService.setDelegate(propertiesStoreService);
		StoreService decoratedStoreService= TracingDecorator.decorate(countingStoreService, StoreService.class);
		mapBooksService.setStoreService(decoratedStoreService);
		counterIsbnGenerator.setSuffix("-de");
		propertiesStoreService.setPropertiesUtil(propertiesUtil);
		
		//Initialisierung
		propertiesStoreService.initialize("books");
		
		booksService = TracingDecorator.decorate(mapBooksService, BooksService.class);
		isbnGenerator = counterIsbnGenerator;
		storeService = propertiesStoreService;
	}
}
