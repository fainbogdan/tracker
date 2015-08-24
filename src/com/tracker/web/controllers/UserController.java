package com.tracker.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tracker.web.models.User;
import com.tracker.web.service.EventService;

@Controller
public class UserController {

	private EventService service;

	@Autowired
	public void setService(EventService service) {
		this.service = service;
	}
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("user", new User());
		return "pages/register";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login()
	{
		return "pages/login";
	}
	
	@RequestMapping(value="register",method=RequestMethod.POST)
	public String store()
	{
		return "pages/index";
	}
}
