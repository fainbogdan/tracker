package com.tracker.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tracker.web.models.Event;
import com.tracker.web.models.User;
import com.tracker.web.service.TrackerService;

@Controller
public class MainController {

	private TrackerService trackerService;
	
	@Autowired
	public void setTrackerService(TrackerService trackerService) {
		this.trackerService = trackerService;
	}

	@RequestMapping(value={"/","index"},method=RequestMethod.GET)
	public String showIndexPage()
	{
		return "pages/index";
	}
	
	@RequestMapping(value="emergency",method=RequestMethod.GET)
	public String createEmergencyEvent(Model model)
	{
		model.addAttribute("event", new Event());
		return "pages/emergency";
	}
	
	@RequestMapping(value="emergency",method=RequestMethod.POST)
	public String storeEmergencyEvent(@Valid Event event, Errors errors)
	{
		if(errors.hasErrors())
			return "pages/emergency";
		
		trackerService.save(event);
		return "pages/index";
	}
	
	@RequestMapping(value="planned",method=RequestMethod.GET)
	public String createPlannedEvent(Model model)
	{
		model.addAttribute("event", new Event());
		return "pages/planned";
	}
	
	@RequestMapping(value="planned",method=RequestMethod.POST)
	public String storePlannedEvent(@Valid Event event,Errors errors)
	{
		if(errors.hasErrors())
			return "/pages/planned";

		trackerService.save(event);
		return "pages/index";
	}
	
	@RequestMapping(value="register",method=RequestMethod.GET)
	public String createUserRegistration(Model model)
	{
		model.addAttribute("user", new User());
		return "pages/register";
	}
	
	@RequestMapping(value="register",method=RequestMethod.POST)
	public String storeUser()
	{
		return "pages/index";
	}
	
	@RequestMapping(value="/events/{id}")
	public String showEvent(@PathVariable("id") int id,Model model)
	{
		model.addAttribute("event", trackerService.getEvent(id));
		return "events/show";
	}
	
	@RequestMapping(value="events",method=RequestMethod.GET)
	public String listEvents()
	{
		return "events/index";
	}
}
