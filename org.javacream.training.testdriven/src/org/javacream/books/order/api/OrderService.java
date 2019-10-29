package org.javacream.books.order.api;

public interface OrderService {

	/*
	 * isbn, number, customer != null
	 * number > 0
	 * IllegalArgumentException, falls customer nicht im CustomerService (String findCustomerByName(String name);) bekannt ist
	 * IllegalArgumentException, BillingService (double getLimitForCustomer(String name);): totalPrice übersteigt das Limit des customers
	 * OrderStatus:
	 * Wenn isbn im BookService nicht gefunden -> UNAVAILABLE, keine Preisberechnung möglich
	 * Wenn isbn im BookService gefunden: Preisberechung
	 * Wenn im StoreService zu wenig Bücher auf Stock, dann PENDING
	 * Sonst: OK
	 */
	Order order(String isbn, int number, String customerName);

}
