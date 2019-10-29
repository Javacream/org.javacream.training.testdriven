package org.javacream.mockito;

import org.javacream.books.warehouse.api.IsbnGenerator;
import org.javacream.books.warehouse.api.StoreService;
import org.junit.Test;
import org.mockito.Mockito;

public class MockitoTest {

	@Test
	public void testIsbnGenerator() {
		IsbnGenerator proxy = Mockito.mock(IsbnGenerator.class);
		Mockito.when(proxy.nextIsbn()).thenReturn("ISBN42");
		System.out.println(proxy.getClass().getName());
		System.out.println(proxy.nextIsbn());
	}

	@Test
	public void testStoreService() {
		StoreService proxy = Mockito.mock(StoreService.class);
		Mockito.when(proxy.getStock("Eg", "Al")).thenReturn(42);
		System.out.println(proxy.getClass().getName());
		System.out.println(proxy.getStock("Eg", "Al"));
	}

}
