package org.javacream.customer;

import java.util.List;

public interface CustomerService {

	String create(String firstname, String lastname);
	void remove(String customer);
	List<String> findCustomersByLastname(String lastname);
	boolean isActive(String customer);
}
