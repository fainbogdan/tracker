package com.tracker.web.controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.tracker.web.models.Event;
import com.tracker.web.service.implementations.UserServiceImpl.CustomUser;
import com.tracker.web.service.interfaces.EventService;
import com.tracker.web.service.interfaces.UserService;

@Component
public class EventsApprovalInterceptor extends HandlerInterceptorAdapter {
	
	private EventService eventService;
	private UserService userService;
	
	@Autowired
	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		if(! (authentication instanceof AnonymousAuthenticationToken) && modelAndView!=null ){	
			if(request.isUserInRole("ROLE_LEAD")){
				String username=authentication.getName();
				CustomUser user=(CustomUser) userService.loadUserByUsername(username);
				List<Event> eventsToApprove=eventService.getEventsToApprove(user);
				modelAndView.getModelMap().addAttribute("eventsToApprove", eventsToApprove);
			}
		}
	}
}
