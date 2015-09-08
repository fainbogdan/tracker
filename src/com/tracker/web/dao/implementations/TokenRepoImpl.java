package com.tracker.web.dao.implementations;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tracker.web.dao.interfaces.TokenRepo;
import com.tracker.web.models.VerificationToken;

@Repository
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
	public int save(VerificationToken token) {
		int id=(int) getCurrentSession().save(token);
		return id;
	}
	
	@Override
	public VerificationToken getToken(String tokenValue){
		Session session=getCurrentSession();
		Criteria criteria=session.createCriteria(VerificationToken.class);
		Criterion criterion=Restrictions.eq("token", tokenValue);
		criteria.add(criterion);
		return (VerificationToken) criteria.list().get(0);
	}

}
