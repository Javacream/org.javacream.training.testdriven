package org.javacream.books.warehouse.api.util;

import org.javacream.books.warehouse.api.IsbnGenerator;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

public class IsbnGeneratorMockUtil {

	public static IsbnGenerator build(String isbn) {
		IsbnGenerator isbnGenerator = Mockito.mock(IsbnGenerator.class);
		Mockito.when(isbnGenerator.nextIsbn()).thenReturn(isbn);
		return isbnGenerator;

	}

	public static IsbnGenerator sequence(int size) {
		IsbnGenerator isbnGenerator = Mockito.mock(IsbnGenerator.class);
		OngoingStubbing<String> invocation = Mockito.when(isbnGenerator.nextIsbn()).thenReturn("ISBN-" + 0);
		for (int i = 1; i < size; i++) {
			invocation = invocation.thenReturn("ISBN-" + i); 
		}
		return isbnGenerator;
	}

}
