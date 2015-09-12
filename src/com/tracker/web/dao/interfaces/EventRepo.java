package com.tracker.web.dao.interfaces;

import java.util.List;
import com.tracker.web.models.Event;

public interface EventRepo {
	
	public Event save(Event event);
	public Event getEvent(int id);
	public List<Event> getEvents();
	public Event eventStart(Event event);
	public boolean canEventStart(Event event);
	public boolean canEventEnd(Event event);
	public Event eventEnd(Event event);
	public List<Event> getEventsForToday();
	public List<Event> getEventsForMonth();
}
