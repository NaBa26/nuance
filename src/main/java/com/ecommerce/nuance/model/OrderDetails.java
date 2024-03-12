package com.ecommerce.nuance.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_details")
public class OrderDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_detail_id")
	private long orderDetailId;
	@Column(name="order_id")
	private int orderId;
	@Column(name="book_id")
	private int bookId;
	@Column(name="quantity")
	private int quantity;
	@Column(name="subtotal")
	private double subtotal;
	
	public OrderDetails() {
		super();
	}
	public long getOrder_detail_id() {
		return orderDetailId;
	}
	public void setOrder_detail_id(int orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
	public int getOrder_id() {
		return orderId;
	}
	public void setOrder_id(int orderId) {
		this.orderId = orderId;
	}
	public int getBook_id() {
		return bookId;
	}
	public void setBook_id(int bookId) {
		this.bookId = bookId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	@Override
	public String toString() {
		return "OrderDetails [orderDetailId=" + orderDetailId + ", orderId=" + orderId + 
				", bookId=" + bookId
				+ ", quantity=" + quantity + ", subtotal=" + subtotal + "]";
	}
}
