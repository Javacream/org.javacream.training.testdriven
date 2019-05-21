package org.javacream.books.warehouse.business;

import java.util.HashMap;
import java.util.Properties;

public class PropertiesStoreService implements StoreService {

	private HashMap<String, Properties> store;

	public PropertiesStoreService(String... storeNames) {
		store = new HashMap<String, Properties>();
		for (String storeName : storeNames) {
			store.put(storeName, PropertiesUtil.getProperties(storeName + "-store.properties"));
		}
	}

	public void setStore(HashMap<String, Properties> store) {
		this.store = store;
	}

	public int getStock(String category, String id) {
		try {
			return Integer.parseInt(store.get(category).get(id).toString());
		} catch (Exception e) {
			return 0;
		}
	}

}
