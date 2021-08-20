package org.javacream.store.api;

import org.javacream.ApplicationContext;
import org.javacream.store.api.StoreService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SimpleStoreServiceGetStockIntegrationTest {

	private StoreService storeService;

	@Before public void init() {
		storeService = ApplicationContext.storeService();
	}
	@Test
	public void categoryBookAndTestIsbnGetsStock45() {
		final int EXPECTED_STOCK = 45;
		int stock = storeService.getStock("books", "TEST-ISBN");
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
