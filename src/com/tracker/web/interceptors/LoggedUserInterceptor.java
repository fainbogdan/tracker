package com.tracker.web.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.tracker.web.service.implementations.UserServiceImpl.CustomUser;
import com.tracker.web.service.interfaces.UserService;

@Component
public class LoggedUserInterceptor extends HandlerInterceptorAdapter{

	private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		if(! (authentication instanceof AnonymousAuthenticationToken) && modelAndView!=null ){	
			String username=authentication.getName();
			CustomUser user=(CustomUser) userService.loadUserByUsername(username);
			modelAndView.getModelMap().addAttribute("loggeduser", user);
		}
	}
}
