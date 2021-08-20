package org.javacream.books.order.api;

import org.javacream.test.util.GenericNotYetImplemented;
import org.junit.Before;
import org.junit.Test;

public class OrderTests {
	private static final String VALID_CUSTOMER_NAME = "Hugo";
	private OrderService orderService;
	
	@Before public void init() {
		orderService = GenericNotYetImplemented.createNotYetImplemented(OrderService.class);
	}
	
	@Test public void validParamsCreateOrder(){
		String customerName = "";
	}
}
