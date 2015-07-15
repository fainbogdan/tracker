package com.tracker.web.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tracker.web.models.Checklist;
import com.tracker.web.models.Event;

@Repository
public class TrackerRepoImpl implements TrackerRepo {

	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getCurrentSession() {	
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(Event event) {
		getCurrentSession().save(event);
	}

	@Override
	public void save(Checklist checklist) {
		getCurrentSession().save(checklist);
	}

	@Override
	public Event getEvent(int id) {
		Event event=(Event) getCurrentSession().get(Event.class, id);
		return event;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> getEvents() {
		return (List<Event>) getCurrentSession().createCriteria(Event.class).list();
	}
}
