package com.tracker.web.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
	public void save(Event event) {
		getCurrentSession().save(event);
	}

	@Override
	public Event getEvent(int id) {
		Event event=(Event) getCurrentSession().get(Event.class, id);
		return event;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> getEvents() {
		LocalDateTime week_start=new LocalDateTime().withDayOfWeek(DateTimeConstants.MONDAY);
		LocalDateTime week_end =new LocalDateTime().withDayOfWeek(DateTimeConstants.SUNDAY);
		
		Criteria criteria=getCurrentSession().createCriteria(Event.class);
		Criterion exp_start =Restrictions.between("expected_start", week_start, week_end);
		Criterion exp_end=Restrictions.between("expected_end", week_start, week_end);
		Criterion act_start=Restrictions.between("actual_start", week_start, week_end);
		Criterion act_end=Restrictions.between("actual_end", week_start, week_end);
		
		Disjunction expression=Restrictions.or(exp_start, exp_end, act_start, act_end);
		criteria.add(expression);
		return (List<Event>) criteria.list();
	}

}
