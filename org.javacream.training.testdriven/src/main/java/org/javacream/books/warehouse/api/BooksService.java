package org.javacream.books.warehouse.api;

import java.util.Map;

import org.javacream.test.SpecifiedBy;

public interface BooksService {

	String newBook(String title);

	Book findBookByIsbn(String isbn);

	@SpecifiedBy(url="https://github.com/Javacream/org.javacream.training.testdriven/blob/integrata_18.8.2021/specs/l%C3%B6scheBuch.txt")
	void deleteBookByIsbn(String isbn);

	@SpecifiedBy(url="https://raw.githubusercontent.com/Javacream/org.javacream.training.testdriven/integrata_18.8.2021/specs/updateBook.jpg")
	void updateBook(String isbn, Map<String, Object> options);

}