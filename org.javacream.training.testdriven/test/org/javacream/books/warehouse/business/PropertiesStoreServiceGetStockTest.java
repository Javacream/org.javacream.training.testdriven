package org.javacream.books.warehouse.business;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

import org.javacream.books.warehouse.api.StoreService;
import org.javacream.books.warehouse.business.PropertiesStoreService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PropertiesStoreServiceGetStockTest {

	private StoreService simpleStoreService;
	private static final String TEST_ISBN1 = "1-123-12345-1-TEST";
	private static final Integer TEST_ISBN1_STOCK = 10;
	private static final String TEST_ISBN2 = "2-123-12345-1-TEST";
	private static final Integer TEST_ISBN2_STOCK = 0;
	private static final String TEST_ISBN3 = "3-123-12345-1-TEST";
	private static final Integer TEST_ISBN3_STOCK = 20;
	private static final String TEST_ISBN4 = "4-123-12345-1-TEST";
	private static final Integer TEST_ISBN4_STOCK = 40;
	private static final String CATEGORY = "books";
	private final String UNKNWON_CATEGORY = "_�$%_";
	private final String UNKNWON_ITEM = "_�4$%6_";
	private static final String PROPERTIES_FILE_NAME="classes/org/javacream/books/warehouse/business/" + CATEGORY + "-store.properties";


	@BeforeClass
	public static void initEnvironment() throws Exception {
		Properties props = new Properties();
		props.setProperty(TEST_ISBN1, TEST_ISBN1_STOCK.toString());
		props.setProperty(TEST_ISBN2, TEST_ISBN2_STOCK.toString());
		props.setProperty(TEST_ISBN3, TEST_ISBN3_STOCK.toString());
		props.setProperty(TEST_ISBN4, TEST_ISBN4_STOCK.toString());
		FileOutputStream fos = new FileOutputStream(PROPERTIES_FILE_NAME);
		props.store(fos, "From SimpleStoreServiceTest");
		fos.flush();
		fos.close();
		
	}
	@Before
	public void setUp(){
		simpleStoreService = new PropertiesStoreService(CATEGORY);
	}

	//@AfterClass
	public static void cleanEnvironment() throws Exception {
		File f = new File(PROPERTIES_FILE_NAME);
		f.delete();

	}

	@Test
	public void testSimpleStoreService() {
		Integer result = simpleStoreService.getStock(CATEGORY, TEST_ISBN1);
		Assert.assertEquals(TEST_ISBN1_STOCK, result);
	}

	@Test
	public void testSimpleStoreServiceInitialization() {
		Assert.assertNotNull("simpleStoreService was null", simpleStoreService);
	}

	@Test
	public void testSimpleStoreServiceUnknownCategory() {
		Assert.assertEquals(0,
				simpleStoreService.getStock(UNKNWON_CATEGORY, TEST_ISBN1));
	}

	@Test
	public void testSimpleStoreServiceUnknownItem() {
		Assert.assertEquals(0,
				simpleStoreService.getStock(CATEGORY, UNKNWON_ITEM));
	}

	@Test
	public void testSimpleStoreServiceNullCategory() {
		Assert.assertEquals(0, simpleStoreService.getStock(null, TEST_ISBN1));
	}

	@Test
	public void testSimpleStoreServiceNullItem() {
		Assert.assertEquals(0, simpleStoreService.getStock(CATEGORY, null));
	}
}
