package com.tracker.web.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class TestModels {

	public static void main(String[] args) {
		
		System.out.println("run");
		// TODO Auto-generated method stub

		User user=new User();
		Event event=new Event();
		Checklist checklist=new Checklist();
		Configuration configuration = new Configuration().configure();
	    StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
	    SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.save(event);
		session.save(checklist);
		session.getTransaction().commit();
		session.close();
		/*User user=new User();
		user.setFirst_name("lokesh");
		
		Event event1=new Event();
		event1.setName("testing");
		user.getEvents().add(event1);
		event1.setUser(user);
		
		Event event2=new Event();
		event2.setName("testing");
		event2.setUser(user);
		user.getEvents().add(event2);
		
		Checklist checklist1=new Checklist();
		checklist1.setName("approval");
		checklist1.setEvent(event1);
		event1.getChecklists().add(checklist1);
		
		Checklist checklist2=new Checklist();
		checklist2.setName("doactivity");
		checklist2.setEvent(event1);
		event1.getChecklists().add(checklist2);
		
		event2.getChecklists().add(checklist2);
		
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.save(event1);
		session.save(event2);
		session.save(checklist1);
		session.save(checklist2);
		session.getTransaction().commit();
		session.close();
		
		Session session1=sessionFactory.openSession();
		session1.beginTransaction();
		Event event=(Event) session1.get(Event.class, 1);
		System.out.println(event.getName());*/
	}

}
