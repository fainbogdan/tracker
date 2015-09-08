package com.tracker.web.controllers;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tracker.web.models.User;
import com.tracker.web.models.VerificationToken;
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
	public String store(@Valid User user, Errors errors, RedirectAttributes redirectAttributes , HttpServletRequest request, HttpServletResponse response) throws MessagingException
	{
		if(errors.hasErrors())
			return "pages/register";

		User registeredUser= userService.register(user,request,response);
		redirectAttributes.addFlashAttribute("message", "Registration success. See confirmation email to activate your account.");
		redirectAttributes.addFlashAttribute("registeredUser", registeredUser);
		return "redirect:/login";
	}
	
	@RequestMapping(value="/regitrationConfirm")
	public String accountActivation(@RequestParam("token") String tokenValue){
		userService.accountActivation(tokenValue);	
		return "redirect:/login";
	}
	
	@RequestMapping(value="/accountRecovery")
	public String accountRecovery(){
		return "pages/accountRecovery";
	}
}
