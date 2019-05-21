package org.javacream.books.warehouse.api.util;

import org.javacream.books.warehouse.api.IsbnGenerator;
import org.junit.Test;

public class MockUsageTest {

	@Test public void testUsage() {
		int size = 5;
		IsbnGenerator isbnGenerator = IsbnGeneratorMockUtil.sequence(size);
		for (int i = 0; i < size; i++) {
			System.out.println(isbnGenerator.nextIsbn());
		}
	}
}
