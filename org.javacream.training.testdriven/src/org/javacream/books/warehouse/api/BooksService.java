package org.javacream.books.warehouse.api;

import java.util.Map;

import org.javacream.util.test.SpecifiedBy;

public interface BooksService {

	String newBook(String title);

	Book findBookByIsbn(String isbn);

	@SpecifiedBy(url = "LöscheBuch.txt")
	void deleteBookByIsbn(String isbn);

	@SpecifiedBy(url = "updateBook.jpg")
	void updateBook(String isbn, Map<String, Object> options);

}