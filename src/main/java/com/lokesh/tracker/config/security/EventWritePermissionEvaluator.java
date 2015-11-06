package com.lokesh.tracker.config.security;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.lokesh.tracker.web.models.Checklist;
import com.lokesh.tracker.web.models.Event;
import com.lokesh.tracker.web.service.implementations.UserServiceImpl.CustomUser;
import com.lokesh.tracker.web.service.interfaces.ChecklistService;
import com.lokesh.tracker.web.service.interfaces.EventService;
import com.lokesh.tracker.web.service.interfaces.UserService;

@Component
public class EventWritePermissionEvaluator implements PermissionEvaluator{

	private ChecklistService checklistService;
	private UserService userService;
	private EventService eventService;

	@Autowired
	public void setChecklistService(ChecklistService checklistService) {
		this.checklistService = checklistService;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}
	
	public CustomUser currentUser()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUser customUser=(CustomUser) userService.loadUserByUsername(auth.getName());
		return customUser;
	}
	
	@Override
	public boolean hasPermission(Authentication authentication,
			Object targetDomainObject, Object permission) {

		if(targetDomainObject instanceof Checklist){
			Checklist checklist=(Checklist) targetDomainObject;
			Event event=checklistService.getChecklist(checklist.getId()).getEvent();
			String grp=event.getCreator().getGrp();
			if(currentUser().getGrp().equals(grp))
				return true;
			else
				return false;
		}
		else if(targetDomainObject instanceof Event){
			Event e=(Event) targetDomainObject;
			Event event=eventService.getEvent(e.getId());
			String grp=event.getCreator().getGrp();
			if(currentUser().getGrp().equals(grp))
				return true;
			else
				return false;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean hasPermission(Authentication authentication,
			Serializable targetId, String targetType, Object permission) {
		int event_id=(int) targetId;
		Event event=eventService.getEvent(event_id);
		String grp=event.getCreator().getGrp();
		if(currentUser().getGrp().equals(grp))
			return true;
		else
			return false;
	}

}
