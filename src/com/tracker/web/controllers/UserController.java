package com.tracker.web.controllers;

import java.util.Map;
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
import com.tracker.web.service.interfaces.TokenService;
import com.tracker.web.service.interfaces.UserService;

@Controller
public class UserController {

	private UserService userService;
	private TokenService tokenService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setTokenService(TokenService tokenService) {
		this.tokenService = tokenService;
	}

	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("user", new User());
		return "user/register";
	}
	
	@RequestMapping(value={"/login","/login?error"}, method=RequestMethod.GET)
	public String login()
	{
		return "user/login";
	}
	
	@RequestMapping(value="register",method=RequestMethod.POST)
	public String store(@Valid User user, Errors errors, RedirectAttributes redirectAttributes , HttpServletRequest request, HttpServletResponse response) throws MessagingException
	{
		if(errors.hasErrors())
			return "user/register";

		User registeredUser= userService.register(user,request,response);
		if(registeredUser!=null){
			redirectAttributes.addFlashAttribute("message", "Registration success. See confirmation email to activate your account.");
			redirectAttributes.addFlashAttribute("registeredUser", registeredUser);
			return "redirect:/login";
		}
		else {
			return "user/register";
		}
	}
	
	@RequestMapping(value="/regitrationConfirm")
	public String accountActivation(@RequestParam("token") String tokenValue, RedirectAttributes redirectAttributes){
		VerificationToken token=tokenService.getTokenByValue(tokenValue);
		if(token!=null)
		{
			User activatedUser=userService.accountActivation(token);
			if(activatedUser!=null){
				redirectAttributes.addFlashAttribute("message", "Account activated. Login now");
				return "redirect:/login";
			}
			else{
				redirectAttributes.addFlashAttribute("message", "Invalid URL. Try again or request new link");
				return "redirect:/accountRecovery";
			}
			
		}
		else{
			redirectAttributes.addFlashAttribute("message", "Invalid URL. Try again or request new link");
			return "redirect:/accountRecovery";
		}
		
	}
	
	@RequestMapping(value="/accountRecovery", method=RequestMethod.GET)
	public String getAccountRecovery(){
		return "user/accountRecovery";
	}
	
	@RequestMapping(value="/forgotPassword", method=RequestMethod.GET)
	public String forgotPassword(RedirectAttributes redirectAttributes){
		redirectAttributes.addFlashAttribute("message", "Request password reset link");
		return "redirect:/accountRecovery";
	}
	
	@RequestMapping(value="/accountRecovery", method=RequestMethod.POST)
	public String postAccountRecovery(@RequestParam Map<String,String> inputs, Model model, HttpServletRequest request, HttpServletResponse response) throws MessagingException{
		if(inputs.get("email").length()>1 && inputs.get("recover")!=null){
			User user=userService.findUserByEmail(inputs.get("email"));
			if(user!=null)
			{
				User recoveredUser=userService.accountRecovery(inputs,request,response);
				if(recoveredUser!=null)
				{
					model.addAttribute("message", "Requested link sent to your email");
					return "user/login";
				}
				else
				{
					model.addAttribute("message", "This email is not associated with any account");
					return "user/accountRecovery";
				}
			}
			else
			{
				model.addAttribute("message", "This email is not associated with any account");
				return "user/accountRecovery";
			}
			
		}
		else {
			model.addAttribute("message", "Email and request option needed");
			return "user/accountRecovery";
		}
		
	}
	
	@RequestMapping(value="/passwordReset", method=RequestMethod.GET)
	public String passwordReset(@RequestParam(value="token", required=false) String tokenValue, Model model){
		if(tokenValue==null){
			model.addAttribute("message", "Use reset link sent to your email. Request again if needed");
			return "user/accountRecovery";
		}
		
		VerificationToken token=tokenService.getTokenByValue(tokenValue);
		if(token!=null){
			model.addAttribute("token", token.getToken());
			return "user/passwordReset";
		}
		else{
			model.addAttribute("message", "Invalid reset link. Try again or request new link");
			return "user/login";
		}
	}
	
	@RequestMapping(value="/passwordReset", method=RequestMethod.POST)
	public String passwordReset(@RequestParam Map<String,String> inputs, Model model){
		if(inputs.get("password").length()>0 && inputs.get("repassword").length()>0 && inputs.get("password").equals(inputs.get("repassword"))){
			User user=userService.resetpassword(inputs);
			if(user!=null){
				model.addAttribute("message", "password changed. Login with your new Credentials");
				return "user/login";
			}
			else{
				model.addAttribute("message", "Something went wrong. try again");
				return "user/accountRecovery";
			}
		}
		else{
			model.addAttribute("token", inputs.get("token"));
			model.addAttribute("message", "Password & Confirm password should match");
			return "user/passwordReset";
		}
	}
	
}
