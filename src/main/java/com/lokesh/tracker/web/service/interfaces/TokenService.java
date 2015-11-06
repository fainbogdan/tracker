package com.lokesh.tracker.web.service.interfaces;

import com.lokesh.tracker.web.models.User;
import com.lokesh.tracker.web.models.VerificationToken;

public interface TokenService {
	public VerificationToken saveToken(User user);
	public VerificationToken update(User user);
	public VerificationToken getTokenByUser(User user);
	public VerificationToken getTokenByValue(String tokenValue);
}
