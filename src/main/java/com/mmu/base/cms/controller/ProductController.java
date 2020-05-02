package com.mmu.base.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmu.base.cms.domain.Product;
import com.mmu.base.cms.services.ProductService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	
	@GetMapping("/{id}")
	public Product retrieve(@PathVariable String orderIdString) {
		try {
			Product order = productService.getProductById(orderIdString);
			return order;
		} catch (Exception e) {
			return null;
		}
	}
	@GetMapping("/all")
	public List<Product> getAllProducts(@PathVariable String orderIdString) {
		try {
			return productService.getProductAll();
		} catch (Exception e) {
			return null;
		}
	}
}
