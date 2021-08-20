package org.javacream.books.order.api;

import org.javacream.billing.BillingService;
import org.javacream.customer.CustomerService;
import org.javacream.store.api.StoreService;
import org.mockito.Mockito;

public class OrderTestData {
	public static final String VALID_CUSTOMER_NAME_WITH_VALID_LIMIT = "Hugo";
	public static final String VALID_CUSTOMER_NAME_WITH_INVALID_LIMIT = "Fritz";
	public static final String INVALID_CUSTOMER_NAME = "Emil";
	public static final String VALID_ISBN_WITH_ENOUGH_STOCK = "ISBN1";
	public static final String VALID_ISBN_WITH_INSUFFICIENT_STOCK = "ISBN2";
	public static final String INVALID_ISBN = "ISBN0";
	public static final int ENOUGH_STOCK = 10;
	public static final int INSUFFICIENT_STOCK = 20;
	public static final int DEFAULT_STOCK = 15;

	public StoreService storeServiceMock() {
		StoreService mock = Mockito.mock(StoreService.class);
		Mockito.when(mock.getStock(Mockito.anyString(), Mockito.anyString())).thenReturn(DEFAULT_STOCK);
		return mock;
	}
	public CustomerService customerServiceMock() {
		CustomerService customerService = Mockito.mock(CustomerService.class);
		Mockito.when(customerService.findCustomerByName(INVALID_CUSTOMER_NAME)).thenThrow(IllegalArgumentException.class);
		Mockito.when(customerService.findCustomerByName(VALID_CUSTOMER_NAME_WITH_VALID_LIMIT)).thenReturn(VALID_CUSTOMER_NAME_WITH_VALID_LIMIT);
		Mockito.when(customerService.findCustomerByName(VALID_CUSTOMER_NAME_WITH_INVALID_LIMIT)).thenReturn(VALID_CUSTOMER_NAME_WITH_INVALID_LIMIT);
		return customerService;
	}
	
	public BillingService billingServiceMock() {
		BillingService billingService = Mockito.mock(BillingService.class);
		
		return billingService;
	}
}
