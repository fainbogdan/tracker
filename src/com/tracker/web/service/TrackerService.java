package com.tracker.web.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import com.tracker.web.models.Event;

public interface TrackerService {
	public void save(Event event);
	public Event getEvent(int id);
	public List<Event> getEvents(HttpServletRequest request);
}
