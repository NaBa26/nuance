package com.ecommerce.nuance.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private long orderId;
    @Column(name="user_id")
    private long userId; 
    @Column(name="total_amount")
    private double totalAmount; 
    @Column(name="status")
    private String status;
    @Column(name="order_date")
    private Timestamp orderDate;
    
    public Order() {
		super();
	}

	public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order [orderId=" + orderId + ", userId=" + userId + ", totalAmount=" + totalAmount + ", status="
                + status + ", orderDate=" + orderDate + "]";
    }
}
