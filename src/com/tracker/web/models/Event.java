package com.tracker.web.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.LocalDateTime;
import org.joda.time.Period;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import customClasses.CustomDateSerializer;

@Entity
@Table(name="events")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Event implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	@Type(type="text")
	private String description;
	
	private String event_type;
	
	@NotNull
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime expected_start;
	
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime expected_end;
	
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime actual_start;
	
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime actual_end;
	
	@NotBlank
	private String environment;
	
	private int executed_by;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date created_at;
	
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updated_at;
	
	@ManyToOne
	@JoinColumn(name="created_by")
	private User creator;
	
	@OneToMany(mappedBy="event")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private Collection<Checklist> checklist=new ArrayList<Checklist>();
	
	private boolean approved;
	
	public Event(){}
	
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

	@JsonSerialize(using=CustomDateSerializer.class)
	public LocalDateTime getExpected_start() {
		return expected_start;
	}

	public void setExpected_start(LocalDateTime expected_start) {
		this.expected_start=expected_start;
	}

	@JsonSerialize(using=CustomDateSerializer.class)
	public LocalDateTime getExpected_end() {
		return expected_end;
	}

	public void setExpected_end(LocalDateTime expected_end) {
		this.expected_end=expected_end;
	}

	@JsonSerialize(using=CustomDateSerializer.class)
	public LocalDateTime getActual_start() {
		return actual_start;
	}

	public void setActual_start(LocalDateTime actual_start) {
		this.actual_start = actual_start;
	}

	@JsonSerialize(using=CustomDateSerializer.class)
	public LocalDateTime getActual_end() {
		return actual_end;
	}

	public void setActual_end(LocalDateTime actual_end) {
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

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Collection<Checklist> getChecklist() {
		return checklist;
	}

	public void setChecklists(Collection<Checklist> checklist) {
		this.checklist = checklist;
	}
	
	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public List<Checklist> sortedChecklist() {
		List<Checklist> list= (List<Checklist>) this.getChecklist();
		Collections.sort(list);
		return list;
	}
	
	public boolean isLongEvent(){
		if(expected_end==null)
			return true;
		
		Period period=new Period(expected_start,expected_end);
		if((period.toStandardMinutes().getMinutes()/60)>=8)
			return true;
		else
			return false;
	}
	
}
