package org.javacream.books.warehouse.api.operations;

import java.util.HashMap;

import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.api.IsbnGenerator;
import org.javacream.books.warehouse.business.MapBooksService;
import org.javacream.util.testdecorator.TracingDecorator;
import org.javacream.util.testdecorator.record_play.XmlRecordingDecorator;
import org.javacream.util.testdriver.GenericDummy;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class MapBooksServiceNewBookUnitTest {

	private static final String TITLE = "Title";
	private static final String TWO_LETTER_TITLE = "AB";

	private static BooksService booksService;

	@BeforeClass
	public static void testBooksService() {
		
		MapBooksService mapBooksService = new MapBooksService();
		IsbnGenerator isbnGenerator = GenericDummy.createDummy(IsbnGenerator.class);
		isbnGenerator = TracingDecorator.createDecorator(isbnGenerator);
		isbnGenerator = XmlRecordingDecorator.createDecorator(isbnGenerator, "c:/_training/invocations.xml");
		HashMap<String, Book> testdata = new HashMap<>();

		mapBooksService.setIsbnGenerator(isbnGenerator);
		mapBooksService.setBooks(testdata);
		booksService = mapBooksService;
	}

	@Test
	public void testValidTitleGeneratesBook() {

		booksService.newBook(TITLE);
	}

	@Test
	public void testValidTitleGeneratesExpectedIsbn() {

		Assert.assertEquals(GenericDummy.Defaults.STRING, booksService.newBook(TITLE));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testTwoLetterTitleIsInvalid() {
		booksService.newBook(TWO_LETTER_TITLE);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testNullTitleIsInvalid() {
		booksService.newBook(null);
	}

}
