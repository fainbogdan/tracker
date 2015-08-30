package com.tracker.web.service.interfaces;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.tracker.web.models.User;

public interface UserService extends UserDetailsService {

	public User register(User user);

}
