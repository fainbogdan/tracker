package com.tracker.web.dao.interfaces;

import com.tracker.web.models.User;

public interface UserRepo {

	public User findUserByUsername(String username);
	public String save(User user);
}
