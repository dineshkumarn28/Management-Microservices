package com.order.service.add.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.order.service.entity.OrderService;

@Repository("orderServiceRepository")
public interface OrderServiceRepository extends JpaRepository<OrderService, Integer> {

}
