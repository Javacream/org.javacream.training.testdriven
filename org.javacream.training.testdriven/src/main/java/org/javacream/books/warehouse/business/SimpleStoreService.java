package org.javacream.books.warehouse.business;

import java.util.HashMap;
import java.util.Properties;

public class SimpleStoreService {

	private HashMap<String, Properties> store;

	{
		store = new HashMap<String, Properties>();
		store.put("books", PropertiesUtil.getProperties("books-store.properties"));
	}

	public int getStock(String category, String id) {
		try {
			return Integer.parseInt(store.get(category).get(id).toString());
		} catch (Exception e) {
			return 0;
		}
	}

}
