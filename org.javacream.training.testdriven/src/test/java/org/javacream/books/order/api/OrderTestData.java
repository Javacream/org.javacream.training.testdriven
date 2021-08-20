package org.javacream.books.order.api;

import org.javacream.billing.BillingService;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.customer.CustomerService;
import org.javacream.store.api.StoreService;
import org.mockito.Mockito;

public class OrderTestData {
	public static final String VALID_CUSTOMER_NAME_WITH_VALID_LIMIT = "Hugo";
	public static final String VALID_CUSTOMER_NAME_WITH_INVALID_LIMIT = "Fritz";
	public static final String INVALID_CUSTOMER_NAME = "Emil";
	public static final String VALID_ISBN = "ISBN1";
	public static final String INVALID_ISBN = "ISBN0";
	public static final int SUFFICIENT_STOCK = 10;
	public static final int INSUFFICIENT_STOCK = 20;
	public static final int DEFAULT_STOCK = 15;
	public static final double VALID_LIMIT = 1000;
	public static final double INVALID_LIMIT = 1;
	public static final double BOOK_PRICE = 9.99;
	

	public static StoreService storeServiceMock() {
		StoreService mock = Mockito.mock(StoreService.class);
		Mockito.when(mock.getStock(Mockito.anyString(), Mockito.anyString())).thenReturn(DEFAULT_STOCK);
		return mock;
	}
	public static CustomerService customerServiceMock() {
		CustomerService customerService = Mockito.mock(CustomerService.class);
		Mockito.when(customerService.findCustomerByName(INVALID_CUSTOMER_NAME)).thenThrow(IllegalArgumentException.class);
		Mockito.when(customerService.findCustomerByName(VALID_CUSTOMER_NAME_WITH_VALID_LIMIT)).thenReturn(VALID_CUSTOMER_NAME_WITH_VALID_LIMIT);
		Mockito.when(customerService.findCustomerByName(VALID_CUSTOMER_NAME_WITH_INVALID_LIMIT)).thenReturn(VALID_CUSTOMER_NAME_WITH_INVALID_LIMIT);
		return customerService;
	}
	
	public static BillingService billingServiceMock() {
		BillingService billingService = Mockito.mock(BillingService.class);
		Mockito.when(billingService.getLimitForCustomer(VALID_CUSTOMER_NAME_WITH_VALID_LIMIT)).thenReturn(VALID_LIMIT);
		Mockito.when(billingService.getLimitForCustomer(VALID_CUSTOMER_NAME_WITH_INVALID_LIMIT)).thenReturn(INVALID_LIMIT);
		return billingService;
	}

	public static BooksService booksServiceMock() {
		BooksService booksService = Mockito.mock(BooksService.class);
		Book b1 = new Book();
		b1.setIsbn(VALID_ISBN);
		b1.setPrice(BOOK_PRICE);
		Mockito.when(booksService.findBookByIsbn(VALID_ISBN)).thenReturn(b1);
		Mockito.when(booksService.findBookByIsbn(INVALID_ISBN)).thenThrow(IllegalArgumentException.class);
		return booksService;
	}
}
