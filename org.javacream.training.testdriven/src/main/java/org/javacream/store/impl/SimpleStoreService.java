package org.javacream.store.impl;

import java.util.HashMap;
import java.util.Properties;

import org.javacream.store.api.StoreService;

public class SimpleStoreService implements StoreService {

	private HashMap<String, Properties> store;

	{
		store = new HashMap<String, Properties>();
		store.put("books", PropertiesUtil.getProperties("books-store.properties"));
	}

	@Override
	public int getStock(String category, String id) {
		try {
			return Integer.parseInt(store.get(category).get(id).toString());
		} catch (Exception e) {
			return 0;
		}
	}

}
