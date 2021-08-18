package org.javacream.books.warehouse.business;

import java.util.HashMap;
import java.util.Map;

import org.javacream.books.warehouse.Book;
import org.javacream.test.SpecifiedBy;


public class MapBooksService{

	private static Map<String, Book> books;

	private CounterIsbnGenerator isbnGenerator;

	private SimpleStoreService storeService;

	{
		books = new HashMap<String, Book>();
		isbnGenerator = new CounterIsbnGenerator();
		storeService = new SimpleStoreService();
	}

	static void setBooks(Map<String, Book> books){
		MapBooksService.books = books;
	}
	
	public String newBook(String title) {
		if (title == null){
			throw new IllegalArgumentException("title was null");
		}
		if (title.trim().length() < 3){
			throw new IllegalArgumentException("title to short: " + title);
		}
		String isbn = isbnGenerator.nextIsbn();
		Book book = new Book();
		book.setTitle(title);
		book.setIsbn(isbn);
		books.put(isbn, book);
//		book.setIsbn(isbnGenerator.nextIsbn());
//		books.put(book.getIsbn(), book);

		return book.getIsbn();
	}

	public Book findBookByIsbn(String isbn) {
		if (isbn == null){
			throw new IllegalArgumentException("isbn was null");
		}
		Book book = (Book) books.get(isbn);
		if (book == null){
			throw new IllegalArgumentException("book not found");
		}
		book.setAvailable(storeService.getStock("Books", isbn) > 0);
		return book;
	}
	
	@SpecifiedBy(url="https://github.com/Javacream/org.javacream.training.testdriven/blob/integrata_18.8.2021/specs/l%C3%B6scheBuch.txt")
	public void deleteBookByIsbn(String isbn) {
		if (isbn == null){
			throw new IllegalArgumentException("isbn was null");
		}
		Book book = books.remove(isbn);
		if (book == null){
			throw new IllegalArgumentException("Kein Buch zu ISBN gefunden");
		}
	}
	@SpecifiedBy(url="https://raw.githubusercontent.com/Javacream/org.javacream.training.testdriven/integrata_18.8.2021/specs/updateBook.jpg")
	public void updateBook(String isbn, Map<String, Object> options) {
		if (options == null || options.isEmpty()) {
			return;
		}
		Book book = findBookByIsbn(isbn);
		Double newPrice = (Double)options.get("price");
		String newTitle = (String)options.get("title");
		if (newPrice != null) {
			book.setPrice(newPrice);
		}
		if (newTitle != null) {
			book.setTitle(newTitle);
		}
	}
}
