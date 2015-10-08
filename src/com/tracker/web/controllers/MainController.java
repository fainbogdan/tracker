package com.tracker.web.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tracker.web.service.implementations.UserServiceImpl.CustomUser;
import com.tracker.web.service.interfaces.UserService;

@Controller
public class MainController {

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value={"/","index"},method=RequestMethod.GET)
	public String showIndexPage(Principal principal,Model model)
	{
		if(principal!=null)
		{
			CustomUser customUser=(CustomUser) userService.loadUserByUsername(principal.getName());
			model.addAttribute("loggeduser", customUser);
		}
		return "pages/index";
	}
	
	
	@RequestMapping(value="accessdenied")
	public String accessDenied(){
		return "pages/accessdenied";
	}
	
}
