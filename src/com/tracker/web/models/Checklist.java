package com.tracker.web.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="checklists")
@DynamicUpdate(value=true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Checklist {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private int item_order;
	private String name;
	private String details;
	private String phase;
	private String completed;
	private String skipped_note;
	private Date completed_on;
	private int completed_by;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date created_at;
	
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updated_at;
	
	@ManyToOne
	private Event event;

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

	public Date getCompleted_on() {
		return completed_on;
	}

	public void setCompleted_on(Date completed_on) {
		this.completed_on = completed_on;
	}

	public int getCompleted_by() {
		return completed_by;
	}

	public void setCompleted_by(int completed_by) {
		this.completed_by = completed_by;
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
	
}

