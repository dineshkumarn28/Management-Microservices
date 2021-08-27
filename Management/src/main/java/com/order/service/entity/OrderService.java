package com.order.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "Order_Service")
public class OrderService {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cust_Id")
	private int custId;

	@Column(name = "cust_Name")
	private String customerName;

	@Column(name = "cust_Shipping")
	private String shippingAddress;

	@Column(name = "cust_total")
	private int totalItems;

	@Column(name = "cust_date")
	private String orderDate;

}