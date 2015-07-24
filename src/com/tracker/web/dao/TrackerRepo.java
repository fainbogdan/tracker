package com.tracker.web.dao;

import java.util.List;

import com.tracker.web.models.Checklist;
import com.tracker.web.models.Event;

public interface TrackerRepo {
	
	public void save(Event event);
	public void save(Checklist checklist);
	public Event getEvent(int id);
	public List<Event> getEvents();
	public Checklist update(Checklist checklist);
}
