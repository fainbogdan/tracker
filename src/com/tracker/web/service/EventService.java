package com.tracker.web.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.support.PagedListHolder;

import com.tracker.web.models.Event;

public interface EventService {
	public void save(Event event);
	public Event getEvent(int id);
	public List<Event> getEvents();
	public Map<String, Object> eventStart(Event event);
	public Map<String, Object> eventEnd(Event event);
}
