package com.lokesh.tracker.web.dao.implementations;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.lucene.search.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lokesh.tracker.web.dao.interfaces.ReportRepo;
import com.lokesh.tracker.web.models.Event;

@Repository
@Transactional
public class ReportRepoImpl implements ReportRepo {

	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Event> search(String keyword) {
		Session session=getCurrentSession();
		FullTextSession fullTextSession=Search.getFullTextSession(session);
		QueryBuilder queryBuilder=fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Event.class).get();
		Query luceneQuery=queryBuilder.keyword().onFields("name","description").matching(keyword).createQuery();
		org.hibernate.Query hibernateQuery=fullTextSession.createFullTextQuery(luceneQuery, Event.class);
		return hibernateQuery.list();
	}

}
