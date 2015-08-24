package com.tracker.web.dao;

import com.tracker.web.models.User;

public interface UserRepo {

	public User findUserByUsername(String username);
}
