package com.order.service.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.order.error.Error;

@Getter
@Setter
@ToString
public class OrderServiceResponse {

	Error error;
	int customerId;
	int status;

}
