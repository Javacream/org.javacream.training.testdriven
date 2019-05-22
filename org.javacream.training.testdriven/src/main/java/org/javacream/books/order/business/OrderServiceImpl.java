package org.javacream.books.order.business;

import org.javacream.billing.InvoiceService;
import org.javacream.books.order.api.Order;
import org.javacream.books.order.api.OrderService;
import org.javacream.books.order.api.OrderStatus;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.api.StoreService;
import org.javacream.customer.CustomerService;

public class OrderServiceImpl implements OrderService {

	private CustomerService customerService;
	private InvoiceService invoiceService;
	private BooksService booksService;
	private StoreService storeService;
	private OrderIdGenerator orderIdGenerator;

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public void setInvoiceService(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
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
	public Order order(String isbn, int amount, String customerName) {
		if (customerName == null || customerName.length() < 3 || !customerService.isActive(customerName)) {
			throw new IllegalArgumentException("illegal customerName,  was " + customerName);
		}
		if (isbn == null) {
			throw new IllegalArgumentException("illegal isbn null");
		}
		if (amount <= 0) {
			throw new IllegalArgumentException("illegal amount, was " + amount);
		}
		long orderId = orderIdGenerator.nextId();
		Double totalPrice = null;
		OrderStatus orderStatus;
		try {
			Book book = booksService.findBookByIsbn(isbn);
			totalPrice = amount * book.getPrice();
			int stock = storeService.getStock("books", isbn);
			if (stock >= amount) {
				orderStatus = OrderStatus.OK;
				invoiceService.create(customerName, totalPrice);
			}else {
				orderStatus = OrderStatus.PENDING;
			}
		} catch (IllegalArgumentException iae) {
			orderStatus = OrderStatus.UNAVAILABLE;
		}
		Order order = new Order(orderId, isbn, customerName, totalPrice);
		order.setStatus(orderStatus);
		return order;
	}

}
