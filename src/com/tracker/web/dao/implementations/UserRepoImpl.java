package com.tracker.web.dao.implementations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tracker.web.dao.interfaces.UserRepo;
import com.tracker.web.models.User;

@Repository
public class UserRepoImpl implements UserRepo {

	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getCurrentSession()
	{
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public User findUserByUsername(String username) {
		
		return (User) getCurrentSession().get(User.class, username);
	}

	@Override
	public String save(User user) {
		getCurrentSession().save(user);
		return user.getUsername();
	}

}