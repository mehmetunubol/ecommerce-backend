package com.mmu.base.cms.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mmu.base.cms.domain.UserRole;

//@CrossOrigin
public interface UserRoleRepository extends MongoRepository<UserRole, String>{
	UserRole findByUserRole(String role);
}
