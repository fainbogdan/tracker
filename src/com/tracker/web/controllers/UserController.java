package com.tracker.web.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.tracker.web.models.User;
import com.tracker.web.service.interfaces.UserService;

@Controller
public class UserController {

	private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
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
	public String store(@Valid User user, Errors errors)
	{
		if(errors.hasErrors())
			return "pages/register";

		userService.register(user);
		return "redirect:pages/login";
	}
}
