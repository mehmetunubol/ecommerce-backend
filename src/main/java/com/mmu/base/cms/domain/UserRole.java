package com.mmu.base.cms.domain;

import org.springframework.data.annotation.Id;

public class UserRole {
	@Id
	private String id;
	// @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups =
	// true)

	private String userRole;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String role) {
		this.userRole = role;
	}
}
