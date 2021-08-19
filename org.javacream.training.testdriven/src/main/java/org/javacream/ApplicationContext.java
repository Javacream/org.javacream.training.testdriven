package org.javacream;

import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.impl.MapBooksService;

public class ApplicationContext {

	private static MapBooksService booksService;
	public static BooksService booksService() {
		return booksService;
	}
	static {
		booksService = new MapBooksService();
	}
	
	
	
}
