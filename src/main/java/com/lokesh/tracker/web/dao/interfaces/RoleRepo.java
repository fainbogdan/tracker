package com.lokesh.tracker.web.dao.interfaces;

import com.lokesh.tracker.web.models.Role;

public interface RoleRepo {
	public int save(Role role);
	public Role getRole(int id);
}
