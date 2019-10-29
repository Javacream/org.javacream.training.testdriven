package org.javacream.util.test;

import org.javacream.books.warehouse.api.IsbnGenerator;
import org.javacream.books.warehouse.api.StoreService;
import org.junit.Test;

public class GenericDummyTest {

	
	@Test public void testIsbnGenerator() {
		IsbnGenerator proxy = GenericDummy.createDummy(IsbnGenerator.class);
		System.out.println(proxy.getClass().getName());
		System.out.println(proxy.nextIsbn());
	}

	@Test public void testStoreService() {
		StoreService proxy = GenericDummy.createDummy(StoreService.class);
		System.out.println(proxy.getClass().getName());
		System.out.println(proxy.getStock("Eg", "Al"));
	}

}
