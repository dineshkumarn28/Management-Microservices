package com.order.service.view.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.order.error.Error;
import com.order.service.view.request.OrderViewRequest;
import com.order.service.view.response.OrderViewResponse;
import com.order.service.view.service.OrderServiceView;

@RestController
@PropertySource({ ("classpath:application.properties") })
public class OrderServiceViewController {

	@Autowired
	OrderServiceView orderServiceView;

	@RequestMapping(path = "/viewOrder", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public OrderViewResponse viewOrderService(@RequestBody OrderViewRequest orderViewRequest,
			HttpServletRequest request) {
		OrderViewResponse response = null;
		Error err = null;
		response = orderServiceView.handleRequest(orderViewRequest, request, err);
		return response;
	}

}
