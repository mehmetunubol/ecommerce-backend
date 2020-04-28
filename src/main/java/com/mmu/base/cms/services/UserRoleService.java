package com.mmu.base.cms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmu.base.cms.domain.UserRole;
import com.mmu.base.cms.repo.UserRoleRepository;

@Service
public class UserRoleService {

	@Autowired
	private UserRoleRepository userRoleRepository;

	public UserRole findRole(String role) {
		return userRoleRepository.findByUserRole(role);
	}

	public void save(UserRole role) {
		userRoleRepository.save(role);
	}
}
