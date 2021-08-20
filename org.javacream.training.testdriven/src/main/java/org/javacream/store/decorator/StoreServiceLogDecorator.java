package org.javacream.store.decorator;

import org.javacream.store.api.StoreService;

public class StoreServiceLogDecorator implements StoreService{

	private StoreService delegate;

	public int getStock(String category, String id) {
		System.out.println("called getStock");
		return delegate.getStock(category, id);
	}

	public void setDelegate(StoreService delegate) {
		this.delegate = delegate;
	}
}
