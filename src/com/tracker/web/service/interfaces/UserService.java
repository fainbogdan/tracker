package com.tracker.web.service.interfaces;

import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.tracker.web.models.User;
import com.tracker.web.models.VerificationToken;

public interface UserService extends UserDetailsService {

	public User register(User user, HttpServletRequest request, HttpServletResponse response) throws MessagingException;
	public User accountActivation(VerificationToken token);
	public User accountRecovery(Map<String, String> inputs, HttpServletRequest request, HttpServletResponse response) throws MessagingException;
	public User resetpassword(Map<String, String> inputs);
	public User findUserByEmail(String string);
}
