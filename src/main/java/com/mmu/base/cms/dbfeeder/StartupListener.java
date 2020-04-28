package com.mmu.base.cms.dbfeeder;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.mmu.base.cms.domain.User;
import com.mmu.base.cms.domain.UserRole;
import com.mmu.base.cms.repo.UserRoleRepository;
import com.mmu.base.cms.services.CustomUserDetailsService;
import com.mmu.base.cms.services.UserRoleService;

@Component
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger LOGGER = LoggerFactory.getLogger(StartupListener.class);

	@Autowired
	private CustomUserDetailsService userService;

	@Autowired
	private UserRoleService roleService;

	@Autowired
	private UserRoleRepository roleRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		LOGGER.debug("onApplicationEvent");
		seedUserRoles();
		seedUsersTableDefaultdata();

	}

	private void seedUsersTableDefaultdata() {
		User userExists = userService.findUserByUsername("admin");
		if (userExists != null) {
			return;
		}

		User user = new User();
		user.setEmail("admin@admin.com");
		user.setPassword("adminpassword");
		user.setUsername("admin");
		user.setFullname("Admin User");
		user.setCreatedAt(new Date());
		UserRole userRole = roleRepository.findByUserRole("ADMIN");
		user.setRoles(new HashSet<>(Arrays.asList(userRole)));
		userService.saveUser(user);
		;
	}

	private void seedUserRoles() {
		if (roleService.findRole("ADMIN") == null) {
			UserRole role = new UserRole("ADMIN");
			roleService.save(role);
		}
		if (roleService.findRole("CUSTOMER") == null) {
			UserRole role = new UserRole("CUSTOMER");
			roleService.save(role);
		}
	}

}
