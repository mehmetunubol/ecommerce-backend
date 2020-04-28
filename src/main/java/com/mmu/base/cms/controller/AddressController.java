package com.mmu.base.cms.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmu.base.cms.domain.User;
import com.mmu.base.cms.domain.UserAddress;
import com.mmu.base.cms.repo.UserAddressRepository;
import com.mmu.base.cms.repo.UserRepository;
import com.mmu.base.cms.services.UserAddressService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/user/address")
public class AddressController {

	@Autowired
	private UserAddressService addressService;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserAddressRepository addressRepository;

	@SuppressWarnings("rawtypes")
	@PostMapping("/create")
	public ResponseEntity create(@RequestBody UserAddress data) {
		try {
			Optional<User> user = userRepository.findById(data.getUserId());
			if (!user.isPresent()) {
				throw new Exception("User not found");
			}
			data.setCreatedAt(new Date());
			addressService.save(data);
			Map<Object, Object> model = new HashMap<>();
			model.put("message", "User address added successfully");
			return ok(model);
		} catch (Exception e) {
			Map<Object, Object> model = new HashMap<>();
			model.put("message", e.getMessage());
			return ResponseEntity.badRequest().body(model);
		}

	}

	@SuppressWarnings("rawtypes")
	@PostMapping("/update")
	public ResponseEntity update(@RequestBody UserAddress data) {
		try {
			Optional<UserAddress> existsAddress = addressRepository.findById(data.getId());
			if (!existsAddress.isPresent()) {
				throw new Exception("Address is not found");

			}
			// Keep creation date of the address
			data.setCreatedAt(existsAddress.get().getCreatedAt());
			addressService.update(data);

			Map<Object, Object> response = new HashMap<>();
			response.put("message", "User address added successfully");
			return ok(response);
		} catch (Exception e) {
			Map<Object, Object> response = new HashMap<>();
			response.put("message", e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}

	}

}
