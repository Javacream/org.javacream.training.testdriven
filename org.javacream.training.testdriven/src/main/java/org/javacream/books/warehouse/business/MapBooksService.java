package org.javacream.books.warehouse.business;

import java.util.Map;

import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.api.IsbnGenerator;
import org.javacream.books.warehouse.api.StoreService;


public class MapBooksService implements BooksService{

	private Map<String, Book> books;

	public void setBooks(Map<String, Book> books) {
		this.books = books;
	}

	public void setIsbnGenerator(IsbnGenerator isbnGenerator) {
		this.isbnGenerator = isbnGenerator;
	}

	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}

	private IsbnGenerator isbnGenerator;

	private StoreService storeService;

//	{
//		books = new HashMap<String, Book>();
//		isbnGenerator = new CounterIsbnGenerator();
//		storeService = new SimpleStoreService();
//	}


	/* (non-Javadoc)
	 * @see org.javacream.books.warehouse.business.BooksService#newBook(java.lang.String)
	 */
	@Override
	public String newBook(String title) {
		if (title == null){
			throw new IllegalArgumentException("title was null");
		}
		if (title.trim().length() < 3){
			throw new IllegalArgumentException("title to short: " + title);
		}
		String isbn = isbnGenerator.nextIsbn();
		//String isbn = "ISBN-" + Math.random();
		Book book = new Book();
		book.setTitle(title);
		book.setIsbn(isbn);
		books.put(isbn, book);
//		book.setIsbn(isbnGenerator.nextIsbn());
//		books.put(book.getIsbn(), book);

		return book.getIsbn();
	}

	/* (non-Javadoc)
	 * @see org.javacream.books.warehouse.business.BooksService#findBookByIsbn(java.lang.String)
	 */
	@Override
	public Book findBookByIsbn(String isbn) {
		if (isbn == null){
			throw new IllegalArgumentException("isbn was null");
		}
		Book book = (Book) books.get(isbn);
		if (book == null){
			throw new IllegalArgumentException("book not found");
		}
		int stock = storeService.getStock("Books", isbn);
		book.setAvailable(stock > 0);
		return book;
	}
	
	

}
