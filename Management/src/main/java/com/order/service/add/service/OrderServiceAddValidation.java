package com.order.service.add.service;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import com.order.error.Error;
import com.order.service.request.OrderItems;
import com.order.service.request.OrderServiceRequest;

@Service("orderServiceAddValidation")
public class OrderServiceAddValidation {

	public Error validateOrder(Object t, HttpServletRequest req, Error err) {
		if ((err = validateCustomerName(((OrderServiceRequest) t), err)) == null) {
			if ((err = validateOrderDate(((OrderServiceRequest) t), err)) == null) {
				if ((err = validateShipping(((OrderServiceRequest) t), err)) == null) {
					if ((err = validateTotalItems(((OrderServiceRequest) t), err)) == null) {
						if ((err = validateOrderItems(((OrderServiceRequest) t).getOrderItems(), err)) == null) {
							err = null;
						}
					}
				}
			}
		}
		return err;

	}

	private Error validateCustomerName(OrderServiceRequest request, Error err) {
		if (request.getCustomerName().isEmpty()) {
			err = new Error();
			err.setErrorDescription("Customer Name is Empty");
		}
		return err;

	}

	private Error validateOrderDate(OrderServiceRequest request, Error err) {
		if (request.getOrderDate().isEmpty()) {
			err = new Error();
			err.setErrorDescription("OrderDate is Empty");
		}
		return err;

	}

	private Error validateShipping(OrderServiceRequest request, Error err) {
		if (request.getShippingddress().isEmpty()) {
			err = new Error();
			err.setErrorDescription("Shipping Address is Empty");
		}
		return err;

	}

	private Error validateTotalItems(OrderServiceRequest request, Error err) {
		if (request.getTotalItems() == 0) {
			err = new Error();
			err.setErrorDescription("TotalItems is Empty");
		}
		return err;

	}

	private Error validateOrderItems(OrderItems request, Error err) {
		if (request != null) {
			if (request.getQuantity() == 0) {
				err = new Error();
				err.setErrorDescription("Quantity is Empty");
			}
			if (request.getProductCode().isEmpty()) {
				err = new Error();
				err.setErrorDescription("Product Code is Empty");
			}
			if (request.getProductName().isEmpty()) {
				err = new Error();
				err.setErrorDescription("Product Name is Empty");
			}
		} else {
			err = new Error();
			err.setErrorDescription("Order Item is Mandatory");
		}
		return err;

	}

}
