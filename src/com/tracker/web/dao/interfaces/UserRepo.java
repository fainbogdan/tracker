package com.tracker.web.dao.interfaces;

import com.tracker.web.models.User;

public interface UserRepo {

	public User findUserByUsername(String username);
	public User save(User user);
	public User update(User user);
	public User accountActivation(User user);
}
