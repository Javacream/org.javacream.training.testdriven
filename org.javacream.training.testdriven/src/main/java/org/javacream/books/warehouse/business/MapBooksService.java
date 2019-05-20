package org.javacream.books.warehouse.business;

import java.util.Map;

import org.javacream.books.warehouse.Book;


public class MapBooksService{

	private Map<String, Book> books;

	public void setBooks(Map<String, Book> books) {
		this.books = books;
	}

	public void setIsbnGenerator(CounterIsbnGenerator isbnGenerator) {
		this.isbnGenerator = isbnGenerator;
	}

	public void setStoreService(SimpleStoreService storeService) {
		this.storeService = storeService;
	}

	private CounterIsbnGenerator isbnGenerator;

	private SimpleStoreService storeService;

//	{
//		books = new HashMap<String, Book>();
//		isbnGenerator = new CounterIsbnGenerator();
//		storeService = new SimpleStoreService();
//	}


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
	
	

}
