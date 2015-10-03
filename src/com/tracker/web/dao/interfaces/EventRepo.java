package com.tracker.web.dao.interfaces;

import java.util.List;
import java.util.Map;

import org.joda.time.LocalDateTime;

import com.tracker.web.models.Event;
import com.tracker.web.service.implementations.UserServiceImpl.CustomUser;

public interface EventRepo {
	
	public Event save(Event event);
	public Event getEvent(int id);
	public List<Event> getEvents(LocalDateTime week_start, LocalDateTime week_end);
	public Event eventStart(Event event);
	public boolean canEventStart(Event event);
	public boolean canEventEnd(Event event);
	public Event eventEnd(Event event);
	public List<Event> getEventsForToday();
	public List<Event> getEventsForMonth();
	public List<Event> getEventsToApprove(CustomUser user);
	public Event approve(Map<String, String> action);
}
