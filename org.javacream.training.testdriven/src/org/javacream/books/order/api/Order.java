package org.javacream.books.order.api;

public class Order {
		private long id;
		private String isbn;
		private int number;
		private double totalPrice;
		private String customer;
		private OrderStatus status;
		public Order(long id, String isbn, int number, double totalPrice, String customer, OrderStatus status) {
			super();
			this.id = id;
			this.isbn = isbn;
			this.number = number;
			this.totalPrice = totalPrice;
			this.customer = customer;
			this.status = status;
		}
		public long getId() {
			return id;
		}
		public String getIsbn() {
			return isbn;
		}
		public int getNumber() {
			return number;
		}
		public double getTotalPrice() {
			return totalPrice;
		}
		public String getCustomer() {
			return customer;
		}
		public OrderStatus getStatus() {
			return status;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((customer == null) ? 0 : customer.hashCode());
			result = prime * result + (int) (id ^ (id >>> 32));
			result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
			result = prime * result + number;
			result = prime * result + ((status == null) ? 0 : status.hashCode());
			long temp;
			temp = Double.doubleToLongBits(totalPrice);
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
			Order other = (Order) obj;
			if (customer == null) {
				if (other.customer != null)
					return false;
			} else if (!customer.equals(other.customer))
				return false;
			if (id != other.id)
				return false;
			if (isbn == null) {
				if (other.isbn != null)
					return false;
			} else if (!isbn.equals(other.isbn))
				return false;
			if (number != other.number)
				return false;
			if (status != other.status)
				return false;
			if (Double.doubleToLongBits(totalPrice) != Double.doubleToLongBits(other.totalPrice))
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "Order [id=" + id + ", isbn=" + isbn + ", number=" + number + ", totalPrice=" + totalPrice
					+ ", customer=" + customer + ", status=" + status + "]";
		}

}
