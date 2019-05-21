package org.javacream.books.warehouse.business;

import org.javacream.books.warehouse.Book;

public interface BooksService {

	String newBook(String title);

	Book findBookByIsbn(String isbn);

}