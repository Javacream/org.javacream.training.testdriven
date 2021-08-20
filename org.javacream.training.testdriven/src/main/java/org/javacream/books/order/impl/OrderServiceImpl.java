package org.javacream.books.order.impl;

import org.javacream.billing.BillingService;
import org.javacream.books.order.api.Order;
import org.javacream.books.order.api.OrderService;
import org.javacream.books.order.api.OrderStatus;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.customer.CustomerService;
import org.javacream.store.api.StoreService;

public class OrderServiceImpl implements OrderService {

	private CustomerService customerService;
	private BillingService billingService;
	private BooksService booksService;
	private StoreService storeService;
	private OrderIdGenerator orderIdGenerator;

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public void setInvoiceService(BillingService billingService) {
		this.billingService = billingService;
	}

	public void setBooksService(BooksService booksService) {
		this.booksService = booksService;
	}

	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}

	public void setOrderIdGenerator(OrderIdGenerator orderIdGenerator) {
		this.orderIdGenerator = orderIdGenerator;
	}

	@Override
	public Order order(String isbn, int number, String customerName) {
		if (customerName == null || (customerService.findCustomerByName(customerName) == null)) {
			throw new IllegalArgumentException("illegal customerName,  was " + customerName);
		}
		if (isbn == null) {
			throw new IllegalArgumentException("illegal isbn null");
		}
		if (number <= 0) {
			throw new IllegalArgumentException("illegal amount, was " + number);
		}
		long orderId = orderIdGenerator.nextId();
		Double totalPrice = null;
		OrderStatus orderStatus;
		try {
			Book book = booksService.findBookByIsbn(isbn);
			totalPrice = number * book.getPrice();
			if (billingService.getLimitForCustomer(customerName) < totalPrice) {
				throw new IllegalArgumentException("Limit exceeded");
			}
			int stock = storeService.getStock("books", isbn);
			if (stock >= number) {
				orderStatus = OrderStatus.OK;
			}else {
				orderStatus = OrderStatus.PENDING;
			}
		} catch (IllegalArgumentException iae) {
			orderStatus = OrderStatus.UNAVAILABLE;
		}
		Order order = new Order(orderId, isbn, number, totalPrice, customerName, orderStatus);
		return order;
	}

}
