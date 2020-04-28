package com.mmu.base.cms.controller;

import static org.springframework.http.ResponseEntity.ok;
import java.util.HashMap;
import java.util.Map;

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
		try {
			String username = data.getUsername();
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
			User user = this.users.findByUsername(username);
			String token = jwtTokenProvider.createToken(username, user.getRoles());
			Map<Object, Object> model = new HashMap<>();
			model.put("id", user.getId());
			model.put("username", username);
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
		User userExists = userService.findUserByUsername(user.getUsername());
		if (userExists != null) {
			throw new BadCredentialsException("User with username: " + user.getUsername() + " already exists");
		}
		userService.saveUser(user);
		Map<Object, Object> model = new HashMap<>();
		model.put("message", "User registered successfully");
		return ok(model);
	}
}
