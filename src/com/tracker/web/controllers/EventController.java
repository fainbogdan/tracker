package com.tracker.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.tracker.web.models.Event;
import com.tracker.web.service.EventService;

@Controller
public class EventController {

	private EventService eventService;

	@Autowired
	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}
	
	@RequestMapping(value="events")
	public String index(Model model,HttpServletRequest request)
	{
		model.addAttribute("events",eventService.getEvents(request));
		return "events/index";
	}
	
	@RequestMapping(value={"emergency","planned"},method=RequestMethod.GET)
	public String create(Model model,HttpServletRequest request)
	{
		model.addAttribute("event", new Event());
		if(request.getContextPath().equals("emergency"))
			return "pages/emergency";
		else
			return "pages/planned";
	}
	
	@RequestMapping(value={"emergency","planned"},method=RequestMethod.POST)
	public String store(@Valid Event event, Errors errors,HttpServletRequest request)
	{
		if(errors.hasErrors())
		{
			if(request.getContextPath().equals("emergency"))
				return "pages/emergency";
			else
				return "pages/planned";
		}
		else
		{
			eventService.save(event);
			return "pages/index";
		}
	}
	
	@RequestMapping(value="/events/{id}",method=RequestMethod.GET)
	public String show(@PathVariable("id") int id,Model model)
	{
		model.addAttribute("event", eventService.getEvent(id));
		return "events/show";
	}
	
	@RequestMapping(value="events/{id}/edit",method=RequestMethod.GET)
	public String edit(Model model,@PathVariable("id") int id) {
		model.addAttribute("event", eventService.getEvent(id));
		return "events/edit";
	}
}
