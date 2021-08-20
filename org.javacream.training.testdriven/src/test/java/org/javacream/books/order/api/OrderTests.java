package org.javacream.books.order.api;

import static org.javacream.books.order.api.OrderTestData.INSUFFICIENT_STOCK;
import static org.javacream.books.order.api.OrderTestData.INVALID_CUSTOMER_NAME;
import static org.javacream.books.order.api.OrderTestData.INVALID_ISBN;
import static org.javacream.books.order.api.OrderTestData.SUFFICIENT_STOCK;
import static org.javacream.books.order.api.OrderTestData.VALID_CUSTOMER_NAME_WITH_INVALID_LIMIT;
import static org.javacream.books.order.api.OrderTestData.VALID_CUSTOMER_NAME_WITH_VALID_LIMIT;
import static org.javacream.books.order.api.OrderTestData.VALID_ISBN;
import static org.javacream.books.order.api.OrderTestData.billingServiceMock;
import static org.javacream.books.order.api.OrderTestData.booksServiceMock;
import static org.javacream.books.order.api.OrderTestData.customerServiceMock;
import static org.javacream.books.order.api.OrderTestData.storeServiceMock;

import org.javacream.books.order.impl.OrderIdGenerator;
import org.javacream.books.order.impl.OrderServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class OrderTests {
	private OrderService orderService;
	
	@Before public void init() {
		OrderServiceImpl orderService = new OrderServiceImpl();
		orderService.setOrderIdGenerator(new OrderIdGenerator(0));
		orderService.setCustomerService(customerServiceMock());
		orderService.setBillingService(billingServiceMock());
		orderService.setStoreService(storeServiceMock());
		orderService.setBooksService(booksServiceMock());
		
		this.orderService = orderService;//Decorator.decorate(orderService, new LogDecoratorCallback());
	}
	
	@Test public void validParamsCreateOrder(){
		Order result = orderService.order(VALID_ISBN, SUFFICIENT_STOCK, VALID_CUSTOMER_NAME_WITH_VALID_LIMIT);
		Assert.assertEquals(OrderStatus.OK, result.getStatus());
	}
	@Test public void insufficientStockCreatesPendingOrder(){
		Order result = orderService.order(VALID_ISBN, INSUFFICIENT_STOCK, VALID_CUSTOMER_NAME_WITH_VALID_LIMIT);
		Assert.assertEquals(OrderStatus.PENDING, result.getStatus());
	}
	@Test public void invalidIsbnCreatesUnavailableOrder(){
		Order result = orderService.order(INVALID_ISBN, SUFFICIENT_STOCK, VALID_CUSTOMER_NAME_WITH_VALID_LIMIT);
		Assert.assertEquals(OrderStatus.UNAVAILABLE, result.getStatus());
	}
	@Test (expected=IllegalArgumentException.class) public void unknownCustomerThrowsException(){
		orderService.order(VALID_ISBN, SUFFICIENT_STOCK, INVALID_CUSTOMER_NAME);
	}
	@Test (expected=IllegalArgumentException.class) public void exceededLimitThrowsException(){
		orderService.order(VALID_ISBN, SUFFICIENT_STOCK, VALID_CUSTOMER_NAME_WITH_INVALID_LIMIT);
	}

	@Test (expected=IllegalArgumentException.class) public void nullIsbnThrowsException(){
		orderService.order(null, SUFFICIENT_STOCK, VALID_CUSTOMER_NAME_WITH_VALID_LIMIT);
	}
	@Test (expected=IllegalArgumentException.class) public void nullNumberThrowsException(){
		orderService.order(VALID_ISBN, 0, VALID_CUSTOMER_NAME_WITH_VALID_LIMIT);
	}
	@Test (expected=IllegalArgumentException.class) public void nullCustomerThrowsException(){
		orderService.order(VALID_ISBN, SUFFICIENT_STOCK, null);
	}
	@Test (expected=IllegalArgumentException.class) public void invalidCustomerThrowsException(){
		orderService.order(VALID_ISBN, SUFFICIENT_STOCK, INVALID_CUSTOMER_NAME);
	}

}
