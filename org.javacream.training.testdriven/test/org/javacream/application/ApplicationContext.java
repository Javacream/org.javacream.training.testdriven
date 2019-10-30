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
import org.javacream.util.test.decorators.Decorator;
import org.javacream.util.test.decorators.NetworkSimulatorDecoratorCallback;
import org.javacream.util.test.decorators.ProfilingDecoratorCallback;
import org.javacream.util.test.decorators.TracingDecoratorCallback;
import org.javacream.util.test.decorators.record_play.XmlRecordingDecorator;

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
		TracingDecoratorCallback tracingDecoratorCallback = new TracingDecoratorCallback();
		ProfilingDecoratorCallback profilingDecoratorCallbackForBooksService = new ProfilingDecoratorCallback();
		NetworkSimulatorDecoratorCallback networkSimulatorDecoratorCallback = new NetworkSimulatorDecoratorCallback();
		//Setzen der Abhängigkeiten unter Berücksichtigung der Decorators
		mapBooksService.setBooks(books);
		mapBooksService.setIsbnGenerator(counterIsbnGenerator);
		countingStoreService.setDelegate(propertiesStoreService);
		StoreService decoratedStoreService= Decorator.decorate(countingStoreService, tracingDecoratorCallback);
		decoratedStoreService = XmlRecordingDecorator.decorate(decoratedStoreService, "store.xml");
		mapBooksService.setStoreService(decoratedStoreService);
		counterIsbnGenerator.setSuffix("-de");
		propertiesStoreService.setPropertiesUtil(propertiesUtil);
		networkSimulatorDecoratorCallback.setDelay(1l);
		
		//Initialisierung
		propertiesStoreService.initialize("books");
		
		booksService = Decorator.decorate(mapBooksService, tracingDecoratorCallback);
		booksService = Decorator.decorate(booksService, profilingDecoratorCallbackForBooksService);
		booksService = Decorator.decorate(booksService, networkSimulatorDecoratorCallback);
		booksService = XmlRecordingDecorator.decorate(booksService, "books.xml");
		//isbnGenerator = Decorator.decorate(counterIsbnGenerator, networkSimulatorDecoratorCallback);
		isbnGenerator = counterIsbnGenerator;
		storeService = propertiesStoreService;
	}
}
