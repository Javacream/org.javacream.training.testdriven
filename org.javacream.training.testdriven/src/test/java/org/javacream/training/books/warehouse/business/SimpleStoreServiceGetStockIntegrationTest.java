package org.javacream.training.books.warehouse.business;

import org.javacream.books.warehouse.business.SimpleStoreService;
import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;

public class SimpleStoreServiceGetStockIntegrationTest {

	private SimpleStoreService simpleStoreService;

	@Before public void init() {
		simpleStoreService = new SimpleStoreService();
	}
	@Test
	public void categoryBookAndTestIsbnGetsStock45() {
		final int EXPECTED_STOCK = 45;
		int stock = simpleStoreService.getStock("books", "TEST-ISBN");
		Assert.assertEquals(EXPECTED_STOCK, stock);
	}
	@Test
	public void unknownCategoryGetsStock0() {
		final int EXPECTED_STOCK = 0;
		int stock = simpleStoreService.getStock("%?zzz$%", "TEST-ISBN");
		Assert.assertEquals(EXPECTED_STOCK, stock);
	}
	@Test
	public void unknownIsbnGetsStock0() {
		final int EXPECTED_STOCK = 0;
		int stock = simpleStoreService.getStock("books", "&%");
		Assert.assertEquals(EXPECTED_STOCK, stock);
	}
}
