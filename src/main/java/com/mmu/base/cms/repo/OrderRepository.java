package com.mmu.base.cms.repo;

import java.util.ArrayList;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.mmu.base.cms.domain.Order;


@CrossOrigin
public interface OrderRepository extends MongoRepository<Order, String> {
	ArrayList<Order> findByUserId(@Param("userId") String userId);
}