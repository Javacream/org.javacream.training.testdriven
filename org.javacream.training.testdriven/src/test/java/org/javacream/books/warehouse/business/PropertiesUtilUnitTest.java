package org.javacream.books.warehouse.business;

import java.util.Properties;

import org.javacream.books.warehouse.business.PropertiesUtil;
import org.junit.Assert;
import org.junit.Test;

public class PropertiesUtilUnitTest {

	@Test
	public void testBooksProperties(){
		Properties props = PropertiesUtil.getProperties("books-store.properties");
		Assert.assertNotNull(props);
	}
}
