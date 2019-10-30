package org.javacream.books.warehouse.decorators;

import org.javacream.books.warehouse.api.StoreService;

public class CountingStoreService implements StoreService {

	private int callCounter = 0;
	private StoreService delegate;

	public void setDelegate(StoreService delegate) {
		this.delegate = delegate;
	}

	public int getStock(String category, String id) {
		callCounter++;
		System.out.println("CallCounter for StoreService: " +callCounter);
		return delegate.getStock(category, id);
	}
}
