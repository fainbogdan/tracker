package com.tracker.web.dao.implementations;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tracker.web.dao.interfaces.TokenRepo;
import com.tracker.web.models.User;
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
	public VerificationToken save(VerificationToken token) {
		int id=(int) getCurrentSession().save(token);
		return getToken(id);
	}
	
	@Override
	public VerificationToken update(VerificationToken token) {
		Session session=getCurrentSession();
		Criteria criteria=session.createCriteria(VerificationToken.class);
		Criterion criterion=Restrictions.eq("user",token.getUser());
		criteria.add(criterion);
		VerificationToken tokenToBeUpdated=(VerificationToken) criteria.list().get(0);
		tokenToBeUpdated.setToken(token.getToken());
		session.flush();
		return tokenToBeUpdated;
	}

	
	@Override
	public VerificationToken getToken(int id){
		Session session=getCurrentSession();
		return (VerificationToken) session.get(VerificationToken.class, id);
	}
	
	@Override
	public VerificationToken getTokenByUser(User user){
		Session session=getCurrentSession();
		Criteria criteria=session.createCriteria(VerificationToken.class);
		Criterion criterion=Restrictions.eq("user", user);
		criteria.add(criterion);
		@SuppressWarnings("unchecked")
		List<VerificationToken> tokens=criteria.list();
		if(tokens.size()>0)
			return tokens.get(0);
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public VerificationToken getTokenByValue(String tokenValue) {
		Session session=getCurrentSession();
		Criteria criteria=session.createCriteria(VerificationToken.class);
		Criterion criterion=Restrictions.eq("token", tokenValue);
		criteria.add(criterion);
		List<VerificationToken> tokens=criteria.list();
		if(tokens.size()>0)
			return tokens.get(0);
		else
			return null;
	}

}
