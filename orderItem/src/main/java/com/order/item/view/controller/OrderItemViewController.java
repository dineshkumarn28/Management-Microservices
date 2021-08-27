package com.order.item.view.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.order.item.view.request.OrderViewRequest;
import com.order.item.view.response.OrderViewResponse;
import com.order.item.view.service.OrderItemView;

@RestController
@PropertySource({ ("classpath:application.properties") })
public class OrderItemViewController {

	@Autowired
	OrderItemView orderServiceView;

	@RequestMapping(path = "/viewOrderItem", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public OrderViewResponse viewOrderService(@RequestBody OrderViewRequest orderViewRequest,
			HttpServletRequest request) {
		OrderViewResponse response = null;
		response = orderServiceView.handleRequest(orderViewRequest, request);
		return response;
	}

}
