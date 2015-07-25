package com.tracker.web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tracker.web.models.Checklist;

@Repository
public class ChecklistRepoImpl implements ChecklistRepo{

	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getCurrentSession() {	
		return sessionFactory.getCurrentSession();
	}
	

	@Override
	public void save(Checklist checklist) {
		getCurrentSession().save(checklist);
	}
	
	@Override
	public Checklist update(Checklist ch) {
		Session session=getCurrentSession();
		Checklist checklist=(Checklist) session.get(Checklist.class, ch.getId());
		checklist.setName(ch.getName());
		checklist.setDetails(ch.getDetails());
		session.flush();
		return checklist;
	}

	@Override
	public Checklist delete(int id) {
		 Session session=getCurrentSession();
		 Checklist checklist=(Checklist) session.get(Checklist.class, id);
		 session.delete(checklist);
		 return checklist;
	}
}
