package com.tracker.web.dao.interfaces;

import com.tracker.web.models.VerificationToken;

public interface TokenRepo {
	public void save(VerificationToken token);
}
