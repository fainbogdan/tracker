package com.tracker.web.service.interfaces;

import java.util.List;
import java.util.Map;
import com.tracker.web.models.Event;

public interface EventService {
	public int save(Event event);
	public Event getEvent(int id);
	public List<Event> getEvents();
	public Map<String, Object> eventStart(Event event);
	public Map<String, Object> eventEnd(Event event);
	public List<Event> getEventsForToday();
	public List<Event> getEventsForMonth();
}
