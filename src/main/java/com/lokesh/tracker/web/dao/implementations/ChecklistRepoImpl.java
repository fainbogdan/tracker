package com.lokesh.tracker.web.dao.implementations;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lokesh.tracker.web.dao.interfaces.ChecklistRepo;
import com.lokesh.tracker.web.models.Checklist;

@Repository
@Transactional
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
	public Checklist getChecklist(int id) {
		return (Checklist) getCurrentSession().get(Checklist.class, id);
	}

	@Override
	public Checklist save(Checklist checklist) {
		Session session=getCurrentSession();
		int id=(int) session.save(checklist);
		return getChecklist(id);
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
	public Checklist updateState(Checklist ch) {
		Session session=getCurrentSession();
		Checklist checklist=(Checklist) session.get(Checklist.class, ch.getId());
		checklist.setCompleted(ch.getCompleted());
		checklist.setCompleted_on(ch.getCompleted_on());
		if(ch.getSkipped_note()!=null)
			checklist.setSkipped_note(ch.getSkipped_note());
		checklist.setFinisher(ch.getFinisher());
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
		for (Iterator<Map<String, String>> iterator = newOrder.iterator(); iterator.hasNext();) {
			Map<String, String> map = (Map<String, String>) iterator.next();
			Checklist checklist=(Checklist) session.get(Checklist.class, Integer.parseInt(map.get("id")) );
			checklist.setItem_order( Integer.parseInt(map.get("item_order")) );
			checklist.setPhase( map.get("phase") );
			session.flush();
		}

		return "recieved";
	}	
	
	
	@Override
	public boolean arePreviousItemsDone(Checklist ch) {
		Checklist checklist=getChecklist(ch.getId());
		Criteria criteria=getCurrentSession().createCriteria(Checklist.class);
		Criterion criterion1=Restrictions.eq("event", checklist.getEvent());
		Criterion criterion2=Restrictions.isNull("completed");
		Criterion criterion3=Restrictions.lt("item_order", checklist.getItem_order());
		Conjunction conjunction=Restrictions.conjunction(criterion1,criterion2,criterion3);
		criteria.add(conjunction);
		if(criteria.list().size()==0)
			return true;
		else
			return false;
	}

}
