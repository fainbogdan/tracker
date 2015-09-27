package com.tracker.web.service.interfaces;

import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tracker.web.models.Event;
import com.tracker.web.service.implementations.UserServiceImpl.CustomUser;

public interface EventService {
	public Event save(Event event, HttpServletRequest request, HttpServletResponse response) throws MessagingException;
	public Event getEvent(int id);
	public List<Event> getEvents();
	public Map<String, Object> eventStart(Event event,HttpServletRequest request, HttpServletResponse response) throws MessagingException;
	public Map<String, Object> eventEnd(Event event,HttpServletRequest request, HttpServletResponse response) throws MessagingException;
	public List<Event> getEventsForToday();
	public List<Event> getEventsForMonth();
	public List<Event> getEventsToApprove(CustomUser user);
	public Event approve(Map<String, String> action);
}
