package com.order.item.add.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.item.entity.OrderItemEntity;

@Repository("orderServiceRepository")
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Integer> {

}
