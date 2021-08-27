package com.order.service.view.service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.error.Error;
import com.order.exception.NoOrderException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import com.order.service.add.service.OrderServiceRepository;
import com.order.service.entity.OrderService;
import com.order.service.request.OrderItems;
import com.order.service.view.request.OrderItem;
import com.order.service.view.request.OrderViewRequest;
import com.order.service.view.response.OrderViewResponse;

@Service("orderServiceView")
public class OrderServiceView {

	@Autowired
	Environment env;
	@Autowired
	OrderServiceRepository orderServiceRepository;

	public OrderViewResponse handleRequest(OrderViewRequest t, HttpServletRequest req, Error err) {
		OrderViewResponse response = new OrderViewResponse();
		Optional<OrderService> orderForm = null;
		OrderItems orderItems = new OrderItems();
		try {
			if (t.getCustomerId() != 0) {
				orderForm = orderServiceRepository.findById(t.getCustomerId());
				if (orderForm.isPresent()) {
					response.setCustomerName(orderForm.get().getCustomerName());
					response.setOrderDate(orderForm.get().getOrderDate());
					response.setShippingddress(orderForm.get().getShippingAddress());
					response.setTotalItems(orderForm.get().getTotalItems());
					orderItems = viewOrderItems(t, orderItems);
					response.setOrderItems(orderItems);
					response.setStatus(200);
				} else {
					throw new NoOrderException();
				}
			} else {
				err = new Error();
				err.setErrorDescription("Customer Id is Mandatory");
				response.setError(err);
			}
		} catch (Exception e) {
			err = new Error();
			err.setErrorDescription("Internal Error");
			response.setError(err);
		}
		return response;

	}

	private OrderItems viewOrderItems(OrderViewRequest orderForm, OrderItems orderItems) {
		String url = null;
		OrderItem orderItem = new OrderItem();
		try {
			orderItem.setCustomerId(orderForm.getCustomerId());
			ObjectMapper obj = new ObjectMapper();
			String input = obj.writeValueAsString(orderItem);
			url = "http://10.44.73.164:8094/orderItem/viewOrderItem";
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
			HttpPost request = new HttpPost(builder.toUriString());
			request.setHeader("Content-type", "application/json");
			StringEntity stringEntity = new StringEntity(input, ContentType.APPLICATION_JSON);
			request.setEntity(stringEntity);
			HttpClient client = HttpClientBuilder.create().disableRedirectHandling().build();
			HttpResponse response = client.execute(request);
			String message = EntityUtils.toString(response.getEntity());
			JSONObject json = new JSONObject(message);
			orderItems.setProductCode(json.getString("productCode"));
			orderItems.setProductName(json.getString("productName"));
			orderItems.setQuantity(json.getInt("quantity"));
			orderItems.setCustomerId(orderForm.getCustomerId());
		} catch (Exception e) {
			throw new NoOrderException();
		}

		return orderItems;
	}
}
