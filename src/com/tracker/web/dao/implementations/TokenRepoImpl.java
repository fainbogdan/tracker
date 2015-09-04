package com.tracker.web.dao.implementations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tracker.web.dao.interfaces.TokenRepo;
import com.tracker.web.models.VerificationToken;

public class TokenRepoImpl implements TokenRepo {

private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getCurrentSession() {	
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void save(VerificationToken token) {
		getCurrentSession().save(token);
	}

}
