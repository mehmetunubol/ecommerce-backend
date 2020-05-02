package com.mmu.base.cms.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.mmu.base.cms.domain.Product;

@CrossOrigin
public interface ProductRepository extends MongoRepository<Product, String> {

}