package org.javacream.books.order.business;

import org.javacream.billing.BillingService;
import org.javacream.books.order.api.Order;
import org.javacream.books.order.api.OrderService;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.api.StoreService;
import org.javacream.customer.CustomerService;

public class OrderServiceImpl implements OrderService{

	@Override
	public Order order(String isbn, int number, String customerName) {
		return null;
	}

	private BooksService booksService;
	private StoreService storeService;
	private CustomerService customerService;
	private BillingService billingService;
	public void setBooksService(BooksService booksService) {
		this.booksService = booksService;
	}
	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	public void setBillingService(BillingService billingService) {
		this.billingService = billingService;
	}
}
