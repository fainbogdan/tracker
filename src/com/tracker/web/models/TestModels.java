package com.tracker.web.models;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class TestModels {

	public static void main(String[] args) {
		
		System.out.println("run");
		User user=new User();
		user.setFirst_name("lokesh");
		user.setLast_name("cherukuri");
		user.setPhone("4324134679");
		user.setEmail("lc4377@rit.edu");
		user.setUsername("username");
		user.setPassword("password");
		user.setRepassword("password");
		user.setEnabled(true);
		user.setGrp("dev");
		user.setDpt("isd");
		
		Role role=new Role();
		role.setRole("user");
		role.setUser(user);
		
		List<Role> roles=new ArrayList<Role>();
		roles.add(role);
		user.setRoles(roles);
		
		VerificationToken token=new VerificationToken();
		token.setToken("dgfhj");
		
		Configuration configuration = new Configuration().configure();
	    StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
	    SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.save(role);
		session.getTransaction().commit();
		session.close();
		
	}

}
