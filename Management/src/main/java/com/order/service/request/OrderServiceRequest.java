package com.order.service.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderServiceRequest {

	String customerName;
	String orderDate;
	String shippingddress;
	OrderItems orderItems;
	int totalItems;

}
