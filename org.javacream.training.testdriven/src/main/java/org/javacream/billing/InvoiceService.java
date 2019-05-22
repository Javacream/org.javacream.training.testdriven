package org.javacream.billing;

public interface InvoiceService {

	Invoice create(String customer, double totalPrice);
	Invoice findById(long id);
	void removeById(long id);
	void pay(long id);
	
}
