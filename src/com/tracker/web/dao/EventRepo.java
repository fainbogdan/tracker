package com.tracker.web.dao;

import java.util.List;

import com.tracker.web.models.Checklist;
import com.tracker.web.models.Event;

public interface EventRepo {
	
	public void save(Event event);
	public Event getEvent(int id);
	public List<Event> getEvents();
}
