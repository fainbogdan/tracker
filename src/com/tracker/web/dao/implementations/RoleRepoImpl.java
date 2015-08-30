package com.tracker.web.dao.implementations;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tracker.web.dao.interfaces.RoleRepo;
import com.tracker.web.models.Role;

@Repository
public class RoleRepoImpl implements RoleRepo {

private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public int save(Role role) {
		int id=(int) sessionFactory.getCurrentSession().save(role);
		return id;
	}

	@Override
	public Role getRole(int id) {
		return (Role) sessionFactory.getCurrentSession().get(Role.class, id);
	}

}
