package com.lokesh.tracker.web.dao.implementations;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lokesh.tracker.web.dao.interfaces.UserRepo;
import com.lokesh.tracker.web.models.User;

@Repository
@Transactional
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

	@SuppressWarnings("unchecked")
	@Override
	public User findUserByEmail(String email) {
		Session session=getCurrentSession();
		Criteria criteria=session.createCriteria(User.class);
		Criterion criterion=Restrictions.eq("email", email);
		criteria.add(criterion);
		List<User> users=new ArrayList<User>(criteria.list());
		if(users.size()>0)
			return users.get(0);
		else
			return null;
	}

	@Override
	public User resetpassword(User user,String password) {
		Session session=getCurrentSession();
		User existingUser=(User) session.get(User.class, user.getUsername());
		existingUser.setPassword(password);
		existingUser.setRepassword(password);
		session.flush();
		return existingUser;
	}

}
