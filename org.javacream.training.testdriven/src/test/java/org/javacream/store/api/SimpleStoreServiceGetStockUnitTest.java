package org.javacream.store.api;

import java.util.ArrayList;
import java.util.Properties;

import org.javacream.store.api.StoreService;
import org.javacream.store.impl.PropertiesUtil;
import org.javacream.store.impl.SimpleStoreService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class SimpleStoreServiceGetStockUnitTest {

	private StoreService storeService;
	private PropertiesUtil propertiesUtil;
	private static final String TEST_ISBN = "ISBN42";
	private static final int TEST_STOCK = 42;
	@Before public void init() {
		SimpleStoreService storeService = new SimpleStoreService();
		propertiesUtil = Mockito.mock(PropertiesUtil.class);
		Properties properties = new Properties();
		properties.put(TEST_ISBN, Integer.toString(TEST_STOCK));
		Mockito.when(propertiesUtil.getProperties("books-store.properties")).thenReturn(properties);
		storeService.setPropertiesUtil(propertiesUtil);
		ArrayList<String> categories = new ArrayList<>();
		categories.add("books");
		storeService.setCategories(categories);
		storeService.init();
		this.storeService = storeService;
	}
	@Test
	public void categoryBookAndTestIsbnGetsStock42() {
		final int EXPECTED_STOCK = TEST_STOCK;
		int stock = storeService.getStock("books", TEST_ISBN);
		Mockito.verify(propertiesUtil).getProperties("books-store.properties");
		Assert.assertEquals(EXPECTED_STOCK, stock);
	}
	@Test
	public void unknownCategoryGetsStock0() {
		final int EXPECTED_STOCK = 0;
		int stock = storeService.getStock("%?zzz$%", "TEST-ISBN");
		Assert.assertEquals(EXPECTED_STOCK, stock);
	}
	@Test
	public void unknownIsbnGetsStock0() {
		final int EXPECTED_STOCK = 0;
		int stock = storeService.getStock("books", "&%");
		Assert.assertEquals(EXPECTED_STOCK, stock);
	}
}
