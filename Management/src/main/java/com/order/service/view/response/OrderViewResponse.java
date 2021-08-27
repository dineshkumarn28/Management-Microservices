package com.order.service.view.response;

import com.order.error.Error;
import com.order.service.request.OrderItems;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderViewResponse {

	String customerName;
	String orderDate;
	String shippingddress;
	OrderItems orderItems;
	int totalItems;
	int status;
	Error error;
}
