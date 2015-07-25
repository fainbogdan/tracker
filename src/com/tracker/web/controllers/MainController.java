package com.tracker.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.tracker.web.service.EventService;

@Controller
public class MainController {

	private EventService eventService;
	
	@Autowired
	public void setTrackerService(EventService eventService) {
		this.eventService = eventService;
	}

	@RequestMapping(value={"/","index"},method=RequestMethod.GET)
	public String showIndexPage()
	{
		return "pages/index";
	}
}
