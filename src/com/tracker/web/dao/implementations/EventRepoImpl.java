package com.tracker.web.dao.implementations;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tracker.web.dao.interfaces.EventRepo;
import com.tracker.web.models.Checklist;
import com.tracker.web.models.Event;

@Repository
public class EventRepoImpl implements EventRepo {

	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getCurrentSession() {	
		return sessionFactory.getCurrentSession();
	}

	@Override
	public int save(Event event) {
		int id=(int) getCurrentSession().save(event);
		return id;
	}

	@Override
	public Event getEvent(int id) {
		Event event=(Event) getCurrentSession().get(Event.class, id);
		return event;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> getEvents() {
		LocalDateTime week_start=new LocalDateTime().minusMonths(6);
		LocalDateTime week_end =new LocalDateTime().plusMonths(6);
		
		Criteria criteria=getCurrentSession().createCriteria(Event.class);
		Criterion exp_start =Restrictions.between("expected_start", week_start, week_end);
		Criterion exp_end=Restrictions.between("expected_end", week_start, week_end);
		
		Disjunction expression=Restrictions.disjunction(exp_start, exp_end);
		criteria.add(expression);
		return (List<Event>) criteria.list();
	}
	
	@Override
	public Event eventStart(Event event) {
		Session session=getCurrentSession();
		Event updatedEvent=(Event) session.get(Event.class, event.getId());
		updatedEvent.setActual_start(new LocalDateTime());
		session.flush();
		return updatedEvent;
	}

	@Override
	public boolean canEventStart(Event event) {
		Session session=getCurrentSession();
		Event updatedEvent=(Event) session.get(Event.class, event.getId());
		Criteria criteria=session.createCriteria(Checklist.class);
		Criterion criterion1=Restrictions.eq("phase", "setup");
		Criterion criterion2=Restrictions.isNull("completed");
		Criterion criterion3=Restrictions.eq("event", updatedEvent);
		Conjunction conjunction=Restrictions.conjunction(criterion1,criterion2,criterion3);
		criteria.add(conjunction);
		if(criteria.list().size()==0)
			return true;
		else
			return false;
	}

	@Override
	public Event eventEnd(Event event) {
		Session session=getCurrentSession();
		Event updatedEvent=(Event) session.get(Event.class, event.getId());
		updatedEvent.setActual_end(new LocalDateTime());
		session.flush();
		return updatedEvent;
	}
	
	
	@Override
	public boolean canEventEnd(Event event) {
		Session session=getCurrentSession();
		Event updatedEvent=(Event) session.get(Event.class, event.getId());
		Criteria criteria=session.createCriteria(Checklist.class);
		Criterion criterion1=Restrictions.eq("phase", "execute");
		Criterion criterion2=Restrictions.isNull("completed");
		Criterion criterion3=Restrictions.eq("event", updatedEvent);
		Conjunction conjunction=Restrictions.conjunction(criterion1,criterion2,criterion3);
		criteria.add(conjunction);
		if(criteria.list().size()==0)
			return true;
		else
			return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> getEventsForToday() {
		Session session=getCurrentSession();
		Criteria criteria=session.createCriteria(Event.class);
		
		LocalDateTime day_start=new LocalDateTime().withTime(0, 0, 0, 0);
		LocalDateTime day_end =new LocalDateTime().withTime(23, 59, 59, 0);
		Criterion criterion1 =Restrictions.between("expected_start", day_start, day_end);
		Criterion criterion2=Restrictions.between("expected_end", day_start, day_end);
		Criterion criterion3=Restrictions.lt("expected_start", day_start);
		Criterion criterion4=Restrictions.gt("expected_end",day_end);
		Conjunction conjunction=Restrictions.conjunction(criterion3,criterion4);
		
		Disjunction disjunction=Restrictions.disjunction(criterion1,criterion2,conjunction);
		criteria.add(disjunction);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> getEventsForMonth() {
		Session session=getCurrentSession();
		LocalDateTime month_start=new LocalDateTime().withDayOfMonth(1);
		LocalDateTime month_end=new LocalDateTime(month_start.dayOfMonth().withMaximumValue());
		Criterion exp_start =Restrictions.between("expected_start", month_start, month_end);
		Criterion exp_end=Restrictions.between("expected_end", month_start, month_end);
		Criterion criterion1=Restrictions.lt("expected_start", month_start);
		Criterion criterion2=Restrictions.gt("expected_end", month_end);
		Conjunction conjunction=Restrictions.conjunction(criterion1,criterion2);
		Disjunction disjunction=Restrictions.disjunction(exp_start,exp_end,conjunction);
		Criteria criteria=session.createCriteria(Event.class);
		criteria.add(disjunction);
		return criteria.list();
	}

}
