package org.javacream.books.warehouse.api.scenario;

import java.util.HashMap;

import org.javacream.application.Context;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.api.IsbnGenerator;
import org.javacream.books.warehouse.api.StoreService;
import org.javacream.books.warehouse.business.MapBooksService;
import org.javacream.util.testdriver.GenericDummy;
import org.junit.Assert;
import org.junit.Test;


public class CreateAndFindBookTest {

	
	@Test public void createdBookCanBeFound(){
		BooksService booksService = Context.getBooksService();
		final String TITLE = "TITLE";
		String isbn = booksService.newBook(TITLE);
		Book result = booksService.findBookByIsbn(isbn);
		Assert.assertEquals(isbn, result.getIsbn());
		Assert.assertEquals(TITLE, result.getTitle());
	}

	@Test public void createdBookCanBeFoundWithDummies(){
		MapBooksService booksService = new MapBooksService();
		booksService.setBooks(new HashMap<>());
		IsbnGenerator isbnGenerator = GenericDummy.createDummy(IsbnGenerator.class);
		booksService.setIsbnGenerator(isbnGenerator);
		StoreService storeService = GenericDummy.createDummy(StoreService.class);
		booksService.setStoreService(storeService);;
		final String TITLE = "TITLE";
		String isbn = booksService.newBook(TITLE);
		Book result = booksService.findBookByIsbn(isbn);
		Assert.assertEquals(isbn, result.getIsbn());
		Assert.assertEquals(TITLE, result.getTitle());
	}

}
