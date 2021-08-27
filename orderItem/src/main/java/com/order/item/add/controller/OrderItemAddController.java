package com.order.item.add.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.order.item.add.service.OrderItemAdd;
import com.order.item.request.OrderItems;
import com.order.item.response.OrderItemResponse;

@RestController
@Component
@PropertySource({ ("classpath:application.properties") })
public class OrderItemAddController {

	@Autowired
	OrderItemAdd orderServiceAdd;

	@PostMapping(path = "/addOrderItem", produces = "application/json", consumes = "application/json")
	public OrderItemResponse addOrderService(@RequestBody OrderItems orderItems, HttpServletRequest request) {
		OrderItemResponse response = null;
		response = orderServiceAdd.handleRequest(orderItems, request);
		return response;
	}

}
