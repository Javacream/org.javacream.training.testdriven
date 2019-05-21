package org.javacream.books.warehouse.api.scenario;

import java.util.HashMap;

import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.IsbnGenerator;
import org.javacream.books.warehouse.api.StoreService;
import org.javacream.books.warehouse.api.util.IsbnGeneratorMockUtil;
import org.javacream.books.warehouse.business.MapBooksService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class CreateAndFindBookWithMock {

	private static final int NUMBER_OF_BOOKS = 3;

	@Test
	public void createdBookCanBeFoundWithMocks() {

		MapBooksService booksService = new MapBooksService();
		booksService.setBooks(new HashMap<>());
		IsbnGenerator isbnGenerator = createMockForIsbnGenerator();
		booksService.setIsbnGenerator(isbnGenerator);
		StoreService storeService = createMockForStoreService();
		booksService.setStoreService(storeService);
		for (int i = 0; i < NUMBER_OF_BOOKS; i++) {
			final String TITLE = "TITLE" + i;
			String isbn = booksService.newBook(TITLE);
			Book result = booksService.findBookByIsbn(isbn);
			Assert.assertEquals(isbn, result.getIsbn());
			Assert.assertEquals(TITLE, result.getTitle());
			Assert.assertEquals("ISBN-" + i, result.getIsbn());
			if (i%2 == 0) {
				Assert.assertTrue(result.isAvailable());
			}else {
				Assert.assertFalse(result.isAvailable());
			}
		}
		
		Mockito.verify(storeService).getStock("books", "ISBN-0");
		Mockito.verify(storeService).getStock("books", "ISBN-1");
		Mockito.verify(storeService).getStock("books", "ISBN-2");
		
	}

	private IsbnGenerator createMockForIsbnGenerator() {
		return IsbnGeneratorMockUtil.sequence(3);
	}

	private StoreService createMockForStoreService() {
		StoreService storeService = Mockito.mock(StoreService.class);
		Mockito.when(storeService.getStock("books", "ISBN-0")).thenReturn(42);
		Mockito.when(storeService.getStock("books", "ISBN-1")).thenReturn(0);
		Mockito.when(storeService.getStock("books", "ISBN-2")).thenReturn(1);
		return storeService;

	}
}
