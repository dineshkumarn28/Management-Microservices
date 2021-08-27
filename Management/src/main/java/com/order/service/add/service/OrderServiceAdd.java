package com.order.service.add.service;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.error.Error;
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
import com.order.service.entity.OrderService;
import com.order.service.request.OrderItems;
import com.order.service.request.OrderServiceRequest;
import com.order.service.response.OrderServiceResponse;

@Service("orderServiceAdd")
public class OrderServiceAdd {

	@Autowired
	Environment env;
	@Autowired
	OrderServiceRepository orderServiceRepository;
	@Autowired
	OrderServiceAddValidation orderServiceAddValidation;

	public OrderServiceResponse handleRequest(OrderServiceRequest t, HttpServletRequest req, Error err) {
		OrderServiceResponse response = new OrderServiceResponse();
		OrderService orderForm = new OrderService();
		OrderItems orderItems = new OrderItems();
		try {
			err = orderServiceAddValidation.validateOrder(t, req, err);
			if (err == null) {
				orderForm.setCustomerName(t.getCustomerName());
				orderForm.setOrderDate(t.getOrderDate());
				orderForm.setShippingAddress(t.getShippingddress());
				orderForm.setTotalItems(t.getTotalItems());
				orderForm = orderServiceRepository.save(orderForm);
				if (orderForm.getCustId() != 0) {
					orderItems.setCustomerId(orderForm.getCustId());
					int status = saveOrderItems(t, orderItems);
					if (status != 0) {
						response.setCustomerId(orderForm.getCustId());
						response.setStatus(200);
					}
				}
			} else {
				response.setError(err);
			}
		} catch (Exception e) {
			err = new Error();
			err.setErrorDescription("Internal Error");
			response.setError(err);
		}
		return response;

	}

	private int saveOrderItems(OrderServiceRequest orderForm, OrderItems orderItems) {
		String url = null;
		int productId = 0;
		try {
			orderItems.setProductCode(orderForm.getOrderItems().getProductCode());
			orderItems.setProductName(orderForm.getOrderItems().getProductName());
			orderItems.setQuantity(orderForm.getOrderItems().getQuantity());
			url = "http://10.44.73.164:8094/orderItem/addOrderItem";
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
			ObjectMapper obj = new ObjectMapper();
			String input = obj.writeValueAsString(orderItems);
			HttpPost request = new HttpPost(builder.toUriString());
			request.setHeader("Content-type", "application/json");
			StringEntity stringEntity = new StringEntity(input, ContentType.APPLICATION_JSON);
			request.setEntity(stringEntity);
			HttpClient client = HttpClientBuilder.create().disableRedirectHandling().build();
			HttpResponse response = client.execute(request);
			String message = EntityUtils.toString(response.getEntity());
			JSONObject json = new JSONObject(message);
			productId = json.getInt("productId");
		} catch (Exception e) {
			productId = 0;
		}
		return productId;
	}

}
