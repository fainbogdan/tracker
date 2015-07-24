package com.tracker.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
}
