package org.javacream.application;

import java.util.HashMap;

import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.api.IsbnGenerator;
import org.javacream.books.warehouse.api.StoreService;
import org.javacream.books.warehouse.business.CounterIsbnGenerator;
import org.javacream.books.warehouse.business.MapBooksService;
import org.javacream.books.warehouse.business.PropertiesStoreService;

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
		PropertiesStoreService propertiesStoreService = new PropertiesStoreService("books");
		HashMap<String, Book> books = new HashMap<>();
		
		//Setzen der Abhängigkeiten
		mapBooksService.setBooks(books);
		mapBooksService.setIsbnGenerator(counterIsbnGenerator);
		mapBooksService.setStoreService(propertiesStoreService);
		counterIsbnGenerator.setSuffix("-de");
		
		booksService = mapBooksService;
		isbnGenerator = counterIsbnGenerator;
		storeService = propertiesStoreService;
	}
}
