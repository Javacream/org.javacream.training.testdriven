package org.javacream.application;

import java.util.HashMap;

import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.api.IsbnGenerator;
import org.javacream.books.warehouse.api.StoreService;
import org.javacream.books.warehouse.business.CounterIsbnGenerator;
import org.javacream.books.warehouse.business.MapBooksService;
import org.javacream.books.warehouse.business.PropertiesStoreService;

public abstract class Context {

	private static MapBooksService mapBooksService;
	private static CounterIsbnGenerator counterIsbnGenerator;
	private static PropertiesStoreService propertiesStoreService;
	public static BooksService getBooksService() {
		return mapBooksService;
	}
	public static IsbnGenerator getIsbnGenerator() {
		return counterIsbnGenerator;
	}
	public static StoreService getStoreService() {
		return propertiesStoreService;
	}
	
	static {
		
		//Erzeugen der Fachobjekte
		mapBooksService = new MapBooksService();
		counterIsbnGenerator = new CounterIsbnGenerator();
		propertiesStoreService = new PropertiesStoreService("books");
		HashMap<String, Book> books = new HashMap<>();
		
		//Setzen der Abhängigkeiten
		mapBooksService.setBooks(books);
		mapBooksService.setIsbnGenerator(counterIsbnGenerator);
		mapBooksService.setStoreService(propertiesStoreService);
		counterIsbnGenerator.setSuffix("-de");
	}
}
