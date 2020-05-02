package com.mmu.base.cms.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmu.base.cms.domain.Order;
import com.mmu.base.cms.repo.OrderRepository;
import com.mmu.base.cms.repo.UserRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;

	public void save(Order order) {
		order.setCreatedAt(new Date());
		orderRepository.save(order);
	}

	public void update(Order order) {
		orderRepository.save(order);
	}

	public void delete(Order order) {
		orderRepository.delete(order);
	}
	
	public Order getOrderById(String orderId) {
		try {
			Optional<Order> order = orderRepository.findById(orderId);
			return order.get();
		} catch (Exception e) {
			throw e;
		}
	}

	public ArrayList<Order> getOrderByUserId(String userId) {
		try {
			ArrayList<Order> order = orderRepository.findByUserId(userId);
			return order;
		} catch (Exception e) {
			throw e;
		}
	}
}
