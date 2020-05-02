package com.mmu.base.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmu.base.cms.domain.Order;
import com.mmu.base.cms.services.OrderService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	
	@GetMapping("/{id}")
	public Order retrieve(@PathVariable String orderIdString) {
		try {
			Order order = orderService.getOrderById(orderIdString);
			return order;
		} catch (Exception e) {
			return null;
		}
	}
	
}
