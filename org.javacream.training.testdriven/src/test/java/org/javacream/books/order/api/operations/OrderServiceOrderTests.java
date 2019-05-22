package org.javacream.books.order.api.operations;

import org.javacream.billing.InvoiceService;
import org.javacream.books.order.api.Order;
import org.javacream.books.order.api.OrderService;
import org.javacream.books.order.api.OrderStatus;
import org.javacream.books.order.business.OrderIdGenerator;
import org.javacream.books.order.business.OrderServiceImpl;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.api.StoreService;
import org.javacream.customer.CustomerService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class OrderServiceOrderTests {
	private static final String ISBN = "ISBN";
	private static final String CUSTOMER = "CUSTOMER";
	private static final int AMOUNT = 10;
	private static final int NEGATIVE_AMOUNT = -1;
	private static final double BOOK_PRICE = 9.99;
	private static final Double EXPECTED_TOTAL_PRICE = 99.90;

	private interface OrderServiceCreator {
		OrderService create();
	}

	@Test
	public void validIsbnAndCustomerCreatesOrder() {
		OrderServiceCreator orderServiceCreator = () -> {
			OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
			StoreService storeService = Mockito.mock(StoreService.class);
			OrderIdGenerator orderIdGenerator = Mockito.mock(OrderIdGenerator.class);
			BooksService booksService = Mockito.mock(BooksService.class);
			CustomerService customerService = Mockito.mock(CustomerService.class);
			InvoiceService invoiceService = Mockito.mock(InvoiceService.class);
			Mockito.when(booksService.findBookByIsbn(Mockito.anyString())).thenReturn(new Book());
			Mockito.when(customerService.isActive(Mockito.anyString())).thenReturn(true);
			Mockito.when(storeService.getStock(Mockito.anyString(), Mockito.anyString())).thenReturn(42);
			Mockito.when(orderIdGenerator.nextId()).thenReturn(0l);
			orderServiceImpl.setOrderIdGenerator(orderIdGenerator);
			orderServiceImpl.setStoreService(storeService);
			orderServiceImpl.setBooksService(booksService);
			orderServiceImpl.setCustomerService(customerService);
			orderServiceImpl.setInvoiceService(invoiceService);
			return orderServiceImpl;
		};
		OrderService orderService = orderServiceCreator.create();
		Order order = orderService.order(ISBN, AMOUNT, CUSTOMER);
		Assert.assertNotNull(order);
		Assert.assertEquals(OrderStatus.OK, order.getStatus());
	}

	@Test
	public void availableIsbnAndCustomerCreatesOrderWithExpectedPrice() {
		Book book = new Book();
		book.setPrice(BOOK_PRICE);
		OrderServiceCreator orderServiceCreator = () -> {
			OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
			StoreService storeService = Mockito.mock(StoreService.class);
			OrderIdGenerator orderIdGenerator = Mockito.mock(OrderIdGenerator.class);
			BooksService booksService = Mockito.mock(BooksService.class);
			CustomerService customerService = Mockito.mock(CustomerService.class);
			InvoiceService invoiceService = Mockito.mock(InvoiceService.class);
			Mockito.when(booksService.findBookByIsbn(Mockito.anyString())).thenReturn(book);
			Mockito.when(customerService.isActive(Mockito.anyString())).thenReturn(true);
			Mockito.when(storeService.getStock(Mockito.anyString(), Mockito.anyString())).thenReturn(42);
			Mockito.when(orderIdGenerator.nextId()).thenReturn(0l);
			orderServiceImpl.setOrderIdGenerator(orderIdGenerator);
			orderServiceImpl.setStoreService(storeService);
			orderServiceImpl.setBooksService(booksService);
			orderServiceImpl.setCustomerService(customerService);
			orderServiceImpl.setInvoiceService(invoiceService);
			return orderServiceImpl;
		};
		OrderService orderService = orderServiceCreator.create();
		Order order = orderService.order(ISBN, AMOUNT, CUSTOMER);
		Assert.assertNotNull(order);
		Assert.assertEquals(OrderStatus.OK, order.getStatus());
		Assert.assertEquals(EXPECTED_TOTAL_PRICE, order.getTotalPrice(), 1e-12);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nullIsbn() {
		OrderService orderService = simpleOrderService.create();
		orderService.order(null, AMOUNT, CUSTOMER);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nullCustomer() {
		OrderService orderService = simpleOrderService.create();
		orderService.order(ISBN, AMOUNT, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void negativeAmount() {
		OrderService orderService = simpleOrderService.create();
		orderService.order(ISBN, NEGATIVE_AMOUNT, CUSTOMER);
	}

	private OrderServiceCreator simpleOrderService = () -> {
		OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
		StoreService storeService = Mockito.mock(StoreService.class);
		OrderIdGenerator orderIdGenerator = Mockito.mock(OrderIdGenerator.class);
		BooksService booksService = Mockito.mock(BooksService.class);
		CustomerService customerService = Mockito.mock(CustomerService.class);
		InvoiceService invoiceService = Mockito.mock(InvoiceService.class);
		orderServiceImpl.setOrderIdGenerator(orderIdGenerator);
		orderServiceImpl.setStoreService(storeService);
		orderServiceImpl.setBooksService(booksService);
		orderServiceImpl.setCustomerService(customerService);
		orderServiceImpl.setInvoiceService(invoiceService);
		return orderServiceImpl;

	};

	private OrderService createOrderServiceForValidIsbnAndCustomerCreatesOrder() {
		OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
		StoreService storeService = Mockito.mock(StoreService.class);
		OrderIdGenerator orderIdGenerator = Mockito.mock(OrderIdGenerator.class);
		BooksService booksService = Mockito.mock(BooksService.class);
		CustomerService customerService = Mockito.mock(CustomerService.class);
		InvoiceService invoiceService = Mockito.mock(InvoiceService.class);
		Mockito.when(booksService.findBookByIsbn(Mockito.anyString())).thenReturn(new Book());
		Mockito.when(customerService.isActive(Mockito.anyString())).thenReturn(true);
		Mockito.when(storeService.getStock(Mockito.anyString(), Mockito.anyString())).thenReturn(42);
		Mockito.when(orderIdGenerator.nextId()).thenReturn(0l);
		orderServiceImpl.setOrderIdGenerator(orderIdGenerator);
		orderServiceImpl.setStoreService(storeService);
		orderServiceImpl.setBooksService(booksService);
		orderServiceImpl.setCustomerService(customerService);
		orderServiceImpl.setInvoiceService(invoiceService);
		return orderServiceImpl;
	}
}
