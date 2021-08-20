package org.javacream.store.app;

import org.javacream.ApplicationContext;
import org.javacream.store.api.StoreService;

public class SimpleStoreApplication {
	private StoreService storeService;
	public static void main(String[] args) {
		new SimpleStoreApplication();
	}
	
	{
		storeService = ApplicationContext.storeService();
		System.out.println(storeService.getStock("books", "TEST-ISBN"));
	}

}
