package com.lokesh.tracker.web.service.implementations;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lokesh.tracker.web.dao.interfaces.TokenRepo;
import com.lokesh.tracker.web.models.User;
import com.lokesh.tracker.web.models.VerificationToken;
import com.lokesh.tracker.web.service.interfaces.TokenService;

@Service
@Transactional
public class TokenServiceImpl implements TokenService {

	private TokenRepo tokenRepo;

	@Autowired
	public void setTokenRepo(TokenRepo tokenRepo) {
		this.tokenRepo = tokenRepo;
	}
	
	@Override
	public VerificationToken saveToken(User user){
		VerificationToken token=new VerificationToken();
		token.setToken(UUID.randomUUID().toString());
		token.setUser(user);
		VerificationToken updatedToken=tokenRepo.save(token);
		return updatedToken;
	}
	
	@Override
	public VerificationToken update(User user) {
		VerificationToken token=new VerificationToken();
		token.setToken(UUID.randomUUID().toString());
		token.setUser(user);
		VerificationToken updatedToken=tokenRepo.update(token);
		return updatedToken;
	}
	
	@Override
	public VerificationToken getTokenByUser(User user){
		return tokenRepo.getTokenByUser(user);
	}

	@Override
	public VerificationToken getTokenByValue(String tokenValue) {
		return tokenRepo.getTokenByValue(tokenValue);
	}
}
