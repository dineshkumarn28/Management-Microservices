package com.order.item.view.response;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderViewResponse {

	String productCode;
	String productName;
	int quantity;
	int status;
}
