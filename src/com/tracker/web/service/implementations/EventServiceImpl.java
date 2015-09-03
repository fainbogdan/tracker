package com.tracker.web.service.implementations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tracker.web.dao.interfaces.ChecklistRepo;
import com.tracker.web.dao.interfaces.EventRepo;
import com.tracker.web.models.Checklist;
import com.tracker.web.models.Event;
import com.tracker.web.service.implementations.UserServiceImpl.CustomUser;
import com.tracker.web.service.interfaces.EventService;
import com.tracker.web.service.interfaces.UserService;

@Service
@Transactional
public class EventServiceImpl implements EventService {

	private EventRepo eventRepo;
	private ChecklistRepo checklistRepo;
	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setEventRepo(EventRepo eventRepo) {
		this.eventRepo = eventRepo;
	}

	@Autowired
	public void setChecklistRepo(ChecklistRepo checklistRepo) {
		this.checklistRepo = checklistRepo;
	}
	
	public CustomUser currentUser()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUser customUser=(CustomUser) userService.loadUserByUsername(auth.getName());
		return customUser;
	}

	public int save(Event event) {
		if(event.getEvent_type().equals("emergency"))
			event.setActual_start(new LocalDateTime());
		
		Checklist plan=new Checklist();
		plan.setName("plan");
		plan.setItem_order(1);
		plan.setPhase("setup");
		plan.setEvent(event);
		plan.setCreator(currentUser());
		checklistRepo.save(plan);
		
		Checklist process=new Checklist();
		process.setName("process");
		process.setItem_order(2);
		process.setPhase("execute");
		process.setEvent(event);
		process.setCreator(currentUser());
		checklistRepo.save(process);
		
		Checklist test=new Checklist();
		test.setName("test");
		test.setItem_order(3);
		test.setPhase("execute");
		test.setEvent(event);
		test.setCreator(currentUser());
		checklistRepo.save(test);
		
		Checklist approve=new Checklist();
		approve.setName("approve");
		approve.setItem_order(4);
		approve.setPhase("teardown");
		approve.setEvent(event);
		approve.setCreator(currentUser());
		checklistRepo.save(approve);
		
		Collection<Checklist> checklist=new ArrayList<Checklist>();
		checklist.add(plan);
		checklist.add(process);
		checklist.add(test);
		checklist.add(approve);
		
		event.setChecklists(checklist);
		event.setCreator(currentUser());
		int id=eventRepo.save(event);
		return id;
	}

	@Override
	public Event getEvent(int id) {
		return eventRepo.getEvent(id);
	}
	
	
	@Override
	public List<Event> getEvents() {
		return eventRepo.getEvents();
	}

	@Override
	public Map<String, Object> eventStart(Event event) {
		Map<String, Object> data=new HashMap<String, Object>();
		if(eventRepo.canEventStart(event))
		{
			Event updatedEvent=eventRepo.eventStart(event);
			data.put("event", updatedEvent);
			data.put("message", "success");
			return data;
		}
			
		data.put("event", event);
		data.put("message", "Please complete all setup items before starting event  ");
		return data;
	}
	
	@Override
	public Map<String, Object> eventEnd(Event event) {
		Map<String, Object> data=new HashMap<String, Object>();
		if(eventRepo.canEventEnd(event))
		{
			Event updatedEvent=eventRepo.eventEnd(event);
			data.put("event", updatedEvent);
			data.put("message", "success");
			return data;
		}
			
		data.put("event", event);
		data.put("message", "Please complete all prior items before ending event  ");
		return data;
	}

	@Override
	public List<Event> getEventsForToday() {
		return eventRepo.getEventsForToday();
	}

	@Override
	public List<Event> getEventsForMonth() {
		return eventRepo.getEventsForMonth();
	}

}