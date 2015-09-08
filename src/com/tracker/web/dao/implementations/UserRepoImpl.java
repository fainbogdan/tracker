package com.tracker.web.dao.implementations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tracker.web.dao.interfaces.UserRepo;
import com.tracker.web.models.User;
import com.tracker.web.models.VerificationToken;

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
	public User save(User user) {
		String username=(String) getCurrentSession().save(user);
		return findUserByUsername(username);
	}
	
	@Override
	public User update(User user) {
		return null;
	}
	
	@Override
	public User accountActivation(User user){
		Session session=getCurrentSession();
		User user2=(User) session.get(User.class, user.getUsername());
		user2.setEnabled(true);
		user2.setRepassword(user.getPassword());
		session.flush();
		return user2;
	}

}
