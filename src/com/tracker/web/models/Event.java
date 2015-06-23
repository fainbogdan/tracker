package com.tracker.web.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="events")
public class Event {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String description;
	
	private String event_type;
	
	@DateTimeFormat(pattern="MM/dd/yyyy")
	@NotNull
	private Date expected_start;
	
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private Date expected_end;
	
	private Date actual_start;
	private Date actual_end;
	
	@NotBlank
	private String environment;
	
	private int executed_by;
	private Date created_at;
	private Date updated_at;
	
	@ManyToOne
	@JoinColumn(name="created_by")
	private User creator;
	
	@OneToMany(mappedBy="event")
	private Collection<Checklist> checklists=new ArrayList<Checklist>();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEvent_type() {
		return event_type;
	}

	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}

	public Date getExpected_start() {
		return expected_start;
	}

	public void setExpected_start(Date expected_start) {
		this.expected_start = expected_start;
	}

	public Date getExpected_end() {
		return expected_end;
	}

	public void setExpected_end(Date expected_end) {
		this.expected_end = expected_end;
	}

	public Date getActual_start() {
		return actual_start;
	}

	public void setActual_start(Date actual_start) {
		this.actual_start = actual_start;
	}

	public Date getActual_end() {
		return actual_end;
	}

	public void setActual_end(Date actual_end) {
		this.actual_end = actual_end;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public int getExecuted_by() {
		return executed_by;
	}

	public void setExecuted_by(int executed_by) {
		this.executed_by = executed_by;
	}

	public Date getCreated_at() {
		return created_at;
	}

	@CreationTimestamp
	public void setCreate_at() {
		this.created_at = new Date();
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	@UpdateTimestamp
	public void setUpdated_at() {
		this.updated_at = new Date();
	}

	public User getCreator() {
		return creator;
	}

	public void setUser(User creator) {
		this.creator = creator;
	}

	public Collection<Checklist> getChecklists() {
		return checklists;
	}

	public void setChecklists(Collection<Checklist> checklists) {
		this.checklists = checklists;
	}
	
}
