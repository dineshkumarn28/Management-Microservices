package com.order.item.view.service;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.order.item.add.service.OrderItemRepository;
import com.order.item.entity.OrderItemEntity;
import com.order.item.view.request.OrderViewRequest;
import com.order.item.view.response.OrderViewResponse;

@Service("orderServiceView")
public class OrderItemView {

	@Autowired
	Environment env;
	@Autowired
	OrderItemRepository orderServiceRepository;

	public OrderViewResponse handleRequest(OrderViewRequest t, HttpServletRequest req) {
		OrderViewResponse response = new OrderViewResponse();
		Optional<OrderItemEntity> orderForm = null;
		orderForm = orderServiceRepository.findById(t.getCustomerId());
		if (orderForm.isPresent()) {
			response.setProductCode(orderForm.get().getProductCode());
			response.setProductName(orderForm.get().getProductName());
			response.setQuantity(orderForm.get().getQuantity());
			response.setStatus(200);
		} else {
			response.setStatus(400);
		}
		return response;

	}

}
