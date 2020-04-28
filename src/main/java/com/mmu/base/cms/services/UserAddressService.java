package com.mmu.base.cms.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmu.base.cms.domain.User;
import com.mmu.base.cms.domain.UserAddress;
import com.mmu.base.cms.repo.UserAddressRepository;

@Service
public class UserAddressService {

	@Autowired
	private UserAddressRepository userAddressRepository;

	public UserAddress findUserByUser(User user) {
		return userAddressRepository.findByUserId(user);
	}

	public void save(UserAddress address) {
		address.setCreatedAt(new Date());
		userAddressRepository.save(address);
	}

	public void update(UserAddress address) {
		userAddressRepository.save(address);
	}

	public void delete(UserAddress address) {
		address.setCreatedAt(new Date());
		userAddressRepository.delete(address);
	}
}
