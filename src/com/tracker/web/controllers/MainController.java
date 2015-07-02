package com.tracker.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tracker.web.dao.TrackerRepoImpl;
import com.tracker.web.models.Event;
import com.tracker.web.service.TrackerService;

@Controller
@RequestMapping("/")
public class MainController {

	private TrackerService trackerService;
	
	@Autowired
	public void setTrackerService(TrackerService trackerService) {
		this.trackerService = trackerService;
	}

	@RequestMapping(value={"/","/index"},method=RequestMethod.GET)
	public String showIndexPage()
	{
		return "/pages/index";
	}
	
	@RequestMapping(value="emergency",method=RequestMethod.GET)
	public String showEmergencyPage(Model model)
	{
		model.addAttribute("event", new Event());
		return "/pages/emergency";
	}
	
	@RequestMapping(value="emergency",method=RequestMethod.POST)
	public String submitEmergencyPage(@Valid Event event, Errors errors)
	{
		if(errors.hasErrors())
			return "/pages/emergency";
		
		System.out.println("name:"+event.getName()+"\n"+
				"description:"+event.getDescription()+"\n"+
				"start:"+event.getExpected_start()+"\n"+
				"end:"+event.getExpected_end()+"\n");
		return "/pages/index";
	}
	
	@RequestMapping(value="planned",method=RequestMethod.GET)
	public String showPlannedPage(Model model)
	{
		model.addAttribute("event", new Event());
		return "/pages/planned";
	}
	
	@RequestMapping(value="planned",method=RequestMethod.POST)
	public String submitPlannedPage(@Valid Event event,Errors errors)
	{
		if(errors.hasErrors())
			return "/pages/planned";

		trackerService.save(event);
		return "/pages/index";
	}
	
	@RequestMapping(value="register",method=RequestMethod.GET)
	public String showLoginPage()
	{
		return "/auth/register";
	}
}
