package com.tracker.web.dao.interfaces;

import java.util.List;

import com.tracker.web.models.Event;
import com.tracker.web.models.User;

public interface UserRepo {

	public User findUserByUsername(String username);
	public User save(User user);
	public User update(User user);
	public User accountActivation(User user);
	public User findUserByEmail(String email);
	public User resetpassword(User user,String string);
}
