package org.javacream.books.warehouse.api.operations;

import java.util.HashMap;

import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.api.IsbnGenerator;
import org.javacream.books.warehouse.business.MapBooksService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class BooksServiceNewBookUnitTest {

	private static String isbn1;
	private static String isbn2;

	private static final String TITLE1 = "Title1";
	private static final String TITLE2 = "Title2";
	private static final String TWO_LETTER_TITLE = "AB";

	private static BooksService booksService;
	private static final String ISBN = "4711-12345-de";
	@BeforeClass
	public static void testBooksService() {
		//booksService = ApplicationContext.booksService();
		MapBooksService mapBooksService = new MapBooksService();
		booksService = mapBooksService;
		
		class TestIsbnGenerator implements IsbnGenerator{

			@Override
			public String nextIsbn() {
				return ISBN;
			}
			
		}
		
		mapBooksService.setIsbnGenerator(new TestIsbnGenerator());
		mapBooksService.setBooks(new HashMap<String, Book>());
	}

	@Test
	public void testValidTitleGeneratesValidIsbn() {

		String isbn = booksService.newBook(TITLE1);
		Assert.assertEquals(ISBN, isbn);
	}

	//@Test
	public void testGeneratedIsbnsAreUnique() {

		isbn1 = booksService.newBook(TITLE1);
		isbn2 = booksService.newBook(TITLE2);

		Assert.assertNotSame("Isbn must be unique", isbn1, isbn2);
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
