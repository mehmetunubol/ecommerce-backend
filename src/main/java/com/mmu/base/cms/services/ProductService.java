package com.mmu.base.cms.services;

import java.util.List;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mmu.base.cms.domain.Product;
import com.mmu.base.cms.repo.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	public void save(Product product) {
		product.setCreatedAt(new Date());
		productRepository.save(product);
	}

	public void update(Product product) {
		productRepository.save(product);
	}

	public void delete(Product product) {
		productRepository.delete(product);
	}

	public Product getProductById(String productId) {
		try {
			Optional<Product> product = productRepository.findById(productId);
			return product.get();
		} catch (Exception e) {
			throw e;
		}
	}

	public List<Product> getProductAll() {
		try {
			List<Product> products = productRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
			return products;
		} catch (Exception e) {
			throw e;
		}
	}
}
