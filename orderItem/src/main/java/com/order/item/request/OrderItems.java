package com.order.item.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderItems {

	String productCode;
	String productName;
	int quantity;
	int customerId;

}
