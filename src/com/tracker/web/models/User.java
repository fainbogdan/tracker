package com.tracker.web.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String first_name;
	private String last_name;
	private String email;
	private String location;
	private String phone;
	private Date created_at;
	private Date updated_at;
	
	@OneToMany(mappedBy="creator")
	private Collection<Event> events=new ArrayList<Event>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}	
	
	public Date getCreated_at() {
		return created_at;
	}

	@PrePersist
	public void setCreated_at() {
		this.created_at = new Date();
	}


	public Date getUpdated_at() {
		return updated_at;
	}
	
	@PreUpdate
	public void setUpdated_at() {
		this.updated_at = new Date();
	}

	public Collection<Event> getEvents() {
		return events;
	}

	public void setEvents(Collection<Event> events) {
		this.events = events;
	}
	
}
