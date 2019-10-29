package org.javacream.books.warehouse.business;

import java.util.HashMap;
import java.util.Properties;

import org.javacream.books.warehouse.api.StoreService;

public class PropertiesStoreService implements StoreService {

	private HashMap<String, Properties> store;
	private PropertiesUtil propertiesUtil;
	
	public void initialize(String... storeNames) {
		store = new HashMap<String, Properties>();
		for (String storeName : storeNames) {
			store.put(storeName, propertiesUtil.getProperties(storeName + "-store.properties"));
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

	public void setPropertiesUtil(PropertiesUtil propertiesUtil) {
		this.propertiesUtil = propertiesUtil;
	}

}
