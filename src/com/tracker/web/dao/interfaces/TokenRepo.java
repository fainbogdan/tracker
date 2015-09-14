package com.tracker.web.dao.interfaces;

import com.tracker.web.models.User;
import com.tracker.web.models.VerificationToken;

public interface TokenRepo {
	public int save(VerificationToken token);
	public VerificationToken getTokenByUser(User user);
	public VerificationToken getTokenByValue(String tokenValue);
}
