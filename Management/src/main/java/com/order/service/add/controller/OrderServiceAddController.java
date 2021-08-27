package com.order.service.add.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.order.error.Error;
import com.order.service.add.service.OrderServiceAdd;
import com.order.service.request.OrderServiceRequest;
import com.order.service.response.OrderServiceResponse;

@RestController
@Component
@PropertySource({ ("classpath:application.properties") })
public class OrderServiceAddController {

	@Autowired
	OrderServiceAdd orderServiceAdd;

	@RequestMapping(path = "/addOrder", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public OrderServiceResponse addOrderService(@RequestBody OrderServiceRequest orderServiceRequest,
			HttpServletRequest request) {
		OrderServiceResponse response = null;
		Error err = null;
		response = orderServiceAdd.handleRequest(orderServiceRequest, request, err);
		return response;
	}

}
