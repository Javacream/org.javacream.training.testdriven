package org.javacream.mockito;

import org.javacream.books.warehouse.api.Book;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class MockitoTest {

	@Test public void mockitoMocksClasses() {
		final String TITLE = "Mockito in Action";
		Book book = Mockito.mock(Book.class);
		Mockito.when(book.getTitle()).thenReturn(TITLE);
		Assert.assertEquals(TITLE, book.getTitle());
		Assert.assertNull(book.getIsbn());
		Mockito.verify(book).getTitle();
		Mockito.verify(book).getIsbn();
	}
}
