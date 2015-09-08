package com.tracker.web.service.implementations;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tracker.web.dao.interfaces.TokenRepo;
import com.tracker.web.models.User;
import com.tracker.web.models.VerificationToken;
import com.tracker.web.service.interfaces.TokenService;

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
		tokenRepo.save(token);
		return token;
	}
	
	@Override
	public VerificationToken getToken(String tokenValue){
		return tokenRepo.getToken(tokenValue);
	}
}
