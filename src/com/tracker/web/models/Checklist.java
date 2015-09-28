package com.tracker.web.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import customClasses.CustomDateSerializer;

@Entity
@Table(name="checklists")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Checklist implements Comparable<Checklist>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private int item_order;
	private String name;
	
	@Type(type="text")
	private String details;
	
	private String phase;
	private String completed;
	
	@Type(type="text")
	private String skipped_note;
	
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime completed_on;
	
	@OneToOne
	@JoinColumn(name="created_by")
	private User creator;
	
	@OneToOne
	@JoinColumn(name="completed_by")
	private User finisher;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date created_at;
	
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updated_at;
	
	@ManyToOne
	private Event event;
	
	public Checklist(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getItem_order() {
		return item_order;
	}

	public void setItem_order(int item_order) {
		this.item_order = item_order;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getCompleted() {
		return completed;
	}

	public void setCompleted(String completed) {
		this.completed = completed;
	}

	public String getSkipped_note() {
		return skipped_note;
	}

	public void setSkipped_note(String skipped_note) {
		this.skipped_note = skipped_note;
	}

	@JsonSerialize(using=CustomDateSerializer.class)
	public LocalDateTime getCompleted_on() {
		return completed_on;
	}

	public void setCompleted_on(LocalDateTime completed_on) {
		this.completed_on = completed_on;
	}
	
	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public User getFinisher() {
		return finisher;
	}

	public void setFinisher(User finisher) {
		this.finisher = finisher;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at() {
		this.created_at = new Date();
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at() {
		this.updated_at = new Date();
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@Override
	public int compareTo(Checklist checklist) {
		return (this.item_order)-(checklist.getItem_order());
	}
	
}

