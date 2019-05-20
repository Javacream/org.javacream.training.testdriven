package org.javacream.application;

import java.util.HashMap;

import org.javacream.books.warehouse.Book;
import org.javacream.books.warehouse.business.CounterIsbnGenerator;
import org.javacream.books.warehouse.business.MapBooksService;
import org.javacream.books.warehouse.business.SimpleStoreService;

public abstract class Context {

	private static MapBooksService mapBooksService;
	private static CounterIsbnGenerator counterIsbnGenerator;
	private static SimpleStoreService simpleStoreService;
	public static MapBooksService getMapBooksService() {
		return mapBooksService;
	}
	public static CounterIsbnGenerator getCounterIsbnGenerator() {
		return counterIsbnGenerator;
	}
	public static SimpleStoreService getSimpleStoreService() {
		return simpleStoreService;
	}
	
	static {
		
		//Erzeugen der Fachobjekte
		mapBooksService = new MapBooksService();
		counterIsbnGenerator = new CounterIsbnGenerator();
		simpleStoreService = new SimpleStoreService();
		HashMap<String, Book> books = new HashMap<>();
		
		//Setzen der Abhängigkeiten
		mapBooksService.setBooks(books);
		mapBooksService.setIsbnGenerator(counterIsbnGenerator);
		mapBooksService.setStoreService(simpleStoreService);
		
		counterIsbnGenerator.setSuffix("-de");
		
	}
}
