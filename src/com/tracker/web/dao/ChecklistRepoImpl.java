package com.tracker.web.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
	public Checklist save(Checklist checklist) {
		Session session=getCurrentSession();
		int id=(int) session.save(checklist);
		return (Checklist) session.get(Checklist.class, id);
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

	@Override
	public String sort(List<Map<String, String>> newOrder) {
		Session session=getCurrentSession();
		for (Iterator iterator = newOrder.iterator(); iterator.hasNext();) {
			Map<String, String> map = (Map<String, String>) iterator.next();
			Checklist checklist=(Checklist) session.get(Checklist.class, Integer.parseInt(map.get("id")) );
			checklist.setItem_order( Integer.parseInt(map.get("item_order")) );
			checklist.setPhase( map.get("phase") );
			session.flush();
		}

		return "recieved";
	}
}
