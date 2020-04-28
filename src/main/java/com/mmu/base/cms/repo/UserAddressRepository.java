package com.mmu.base.cms.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.mmu.base.cms.domain.User;
import com.mmu.base.cms.domain.UserAddress;

@CrossOrigin
public interface UserAddressRepository extends MongoRepository<UserAddress, String> {
	UserAddress findByUserId(@Param("userId") User user);
}