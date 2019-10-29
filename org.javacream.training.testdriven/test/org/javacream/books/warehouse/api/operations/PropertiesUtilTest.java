package org.javacream.books.warehouse.api.operations;

import java.util.Properties;

import org.javacream.books.warehouse.business.PropertiesUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PropertiesUtilTest {
	private PropertiesUtil propertiesUtil;
	
	@Before public void init() {
		propertiesUtil = new PropertiesUtil();
	}
	@Test
	public void testBooksProperties() {
		Properties props = propertiesUtil.getProperties("books-store.properties");
		Assert.assertNotNull(props);
	}
}
