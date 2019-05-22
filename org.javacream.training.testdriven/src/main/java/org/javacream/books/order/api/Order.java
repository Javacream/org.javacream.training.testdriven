package org.javacream.books.order.api;

public class Order {

	public Order(long orderId, String isbn, String customerName, Double totalPrice) {
		super();
		this.orderId = orderId;
		this.isbn = isbn;
		this.customerName = customerName;
		this.totalPrice = totalPrice;
		this.status = OrderStatus.UNAVAILABLE;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public long getOrderId() {
		return orderId;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", isbn=" + isbn + ", customerName=" + customerName + ", totalPrice="
				+ totalPrice + ", status=" + status + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + (int) (orderId ^ (orderId >>> 32));
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((totalPrice == null) ? 0 : totalPrice.hashCode());
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
		Order other = (Order) obj;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (orderId != other.orderId)
			return false;
		if (status != other.status)
			return false;
		if (totalPrice == null) {
			if (other.totalPrice != null)
				return false;
		} else if (!totalPrice.equals(other.totalPrice))
			return false;
		return true;
	}
	public String getIsbn() {
		return isbn;
	}
	public String getCustomerName() {
		return customerName;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	private long orderId;
	private String isbn;
	private String customerName;
	private Double totalPrice;
	private OrderStatus status;
}
