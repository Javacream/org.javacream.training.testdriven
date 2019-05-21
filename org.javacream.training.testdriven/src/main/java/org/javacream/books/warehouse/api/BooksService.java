package org.javacream.books.warehouse.api;

public interface BooksService {

	String newBook(String title);

	Book findBookByIsbn(String isbn);

}