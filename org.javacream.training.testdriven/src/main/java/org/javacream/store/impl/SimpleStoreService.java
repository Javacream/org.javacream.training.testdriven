package org.javacream.store.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.javacream.store.api.StoreService;

public class SimpleStoreService implements StoreService {

	private HashMap<String, Properties> store;
	private List<String> categories;
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public void init(){
		store = new HashMap<String, Properties>();
		categories.forEach(category -> store.put(category, PropertiesUtil.getProperties(category + "-store.properties")));
		
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
