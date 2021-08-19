package org.javacream.books.warehouse.api;

import java.util.Map;

import org.javacream.test.SpecifiedBy;

public interface BooksService {

	String newBook(String title);

	Book findBookByIsbn(String isbn);

	void deleteBookByIsbn(String isbn);

	void updateBook(String isbn, Map<String, Object> options);

}