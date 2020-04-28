package com.mmu.base.cms.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmu.base.cms.domain.User;
import com.mmu.base.cms.repo.UserRepository;
import com.mmu.base.cms.security.JwtTokenProvider;
import com.mmu.base.cms.services.CustomUserDetailsService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Autowired
	UserRepository users;

	@Autowired
	private CustomUserDetailsService userService;

	@SuppressWarnings("rawtypes")
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody User data) {
		LOGGER.debug("Login request is received");
		try {
			String email = data.getEmail();
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, data.getPassword()));
			User user = users.findByEmail(email);
			LOGGER.error("User" + user);
			user.setCreatedAt(new Date());
			String token = jwtTokenProvider.createToken(email, user.getRoles());
			Map<Object, Object> model = new HashMap<>();
			model.put("id", user.getId());
			model.put("email", email);
			model.put("enabled", user.isEnabled());
			model.put("token", token);
			return ok(model);
		} catch (AuthenticationException e) {
			System.out.println("Invalid email/password supplied" + e + e.toString());
			throw new BadCredentialsException("Invalid email/password supplied");
		}
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("/register")
	public ResponseEntity register(@RequestBody User user) {
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			throw new BadCredentialsException("User with email: " + user.getEmail() + " already exists");
		}
		user.setUsername(user.getEmail());
		userService.saveUser(user);
		Map<Object, Object> model = new HashMap<>();
		model.put("message", "User registered successfully");
		return ok(model);
	}

}
