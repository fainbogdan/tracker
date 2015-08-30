package com.tracker.web.dao.interfaces;

import com.tracker.web.models.Role;

public interface RoleRepo {
	public int save(Role role);
	public Role getRole(int id);
}
