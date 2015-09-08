package com.tracker.web.service.interfaces;

import com.tracker.web.models.User;
import com.tracker.web.models.VerificationToken;

public interface TokenService {
	public VerificationToken saveToken(User user);
	public VerificationToken getToken(String tokenValue);
}
