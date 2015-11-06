package com.lokesh.tracker.web.dao.interfaces;

import com.lokesh.tracker.web.models.User;
import com.lokesh.tracker.web.models.VerificationToken;

public interface TokenRepo {
	public VerificationToken save(VerificationToken token);
	public VerificationToken getTokenByUser(User user);
	public VerificationToken getTokenByValue(String tokenValue);
	VerificationToken getToken(int id);
	public VerificationToken update(VerificationToken token);
}
