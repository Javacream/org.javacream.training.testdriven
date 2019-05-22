package org.javacream.billing;

public class Invoice {

	private long id;
	private String customer;
	private double price;
	private boolean payed;
	public Invoice(long id, String customer, double price) {
		super();
		this.id = id;
		this.customer = customer;
		this.price = price;
	}
	@Override
	public String toString() {
		return "Invoice [id=" + id + ", customer=" + customer + ", price=" + price + ", payed=" + payed + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (payed ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Invoice other = (Invoice) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (id != other.id)
			return false;
		if (payed != other.payed)
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		return true;
	}
	public void setPayed(boolean payed) {
		this.payed = payed;
	}
	public long getId() {
		return id;
	}
	public String getCustomer() {
		return customer;
	}
	public double getPrice() {
		return price;
	}
	public boolean isPayed() {
		return payed;
	}
}
