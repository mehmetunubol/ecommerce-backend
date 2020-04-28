package com.mmu.base.cms.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.mmu.base.cms.domain.User;

@CrossOrigin
public interface UserRepository extends MongoRepository<User, String> {
	User findByUsername(@Param("name") String name);
}