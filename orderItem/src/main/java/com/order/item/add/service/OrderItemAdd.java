package com.order.item.add.service;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.order.item.entity.OrderItemEntity;
import com.order.item.entity.OrderService;
import com.order.item.request.OrderItems;
import com.order.item.response.OrderItemResponse;

@Service("orderServiceAdd")
public class OrderItemAdd {

	@Autowired
	Environment env;
	@Autowired
	OrderItemRepository orderServiceRepository;

	public OrderItemResponse handleRequest(OrderItems t, HttpServletRequest req) {
		OrderItemResponse response = new OrderItemResponse();
		OrderItemEntity orderForm = new OrderItemEntity();
		orderForm.setProductCode(t.getProductCode());
		orderForm.setProductName(t.getProductName());
		orderForm.setQuantity(t.getQuantity());
		orderForm.setCustId(t.getCustomerId());
		orderForm = orderServiceRepository.save(orderForm);
		response.setProductId(orderForm.getProductId());
		return response;

	}

}
