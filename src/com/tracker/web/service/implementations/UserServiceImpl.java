package com.tracker.web.service.implementations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.WebContext;

import com.tracker.integrations.MailService;
import com.tracker.web.dao.interfaces.UserRepo;
import com.tracker.web.models.Role;
import com.tracker.web.models.User;
import com.tracker.web.models.VerificationToken;
import com.tracker.web.service.interfaces.RoleService;
import com.tracker.web.service.interfaces.TokenService;
import com.tracker.web.service.interfaces.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private UserRepo userRepo;
	private RoleService roleService;
	private TokenService tokenService;
	private MailService mailService;
	private Locale locale=new Locale("en", "US");
	
	@Autowired
	public void setUserRepo(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@Autowired
	public void setTokenRepo(TokenService tokenService) {
		this.tokenService = tokenService;
	}
	
	@Autowired
	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user=userRepo.findUserByUsername(username);
		if(user == null) {
            throw new UsernameNotFoundException("Could not find user " + username);
        }
		CustomUser customUser=new CustomUser(user);
		return customUser;
	}
	
	public final static class CustomUser extends User implements UserDetails
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public CustomUser(User user) {
			super(user);
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			List<GrantedAuthority> authorities =
					new ArrayList<GrantedAuthority>();
					authorities.add(new SimpleGrantedAuthority("user"));
			
			return authorities;
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}
		
	}

	@Override
	public User register(User user, HttpServletRequest request, HttpServletResponse response) throws MessagingException {
		List<Role> roles=new ArrayList<Role>();
		Role role=new Role();
		role.setRole("user");
		role.setUser(user);
		roleService.save(role);
		roles.add(role);
		user.setRoles(roles);
		
		tokenService.saveToken(user);
		User registeredUser=userRepo.save(user);
		
		if(registeredUser!=null){
			final WebContext context = new WebContext(request, response, request.getServletContext(), locale);
			String url = "";
			if (request.getServerPort() == 80  || request.getServerPort() == 443 )
				url= request.getScheme() + "://" +request.getServerName() + request.getContextPath();
			    else
			    	url= request.getScheme() + "://" +request.getServerName() + ":" + request.getServerPort() +request.getContextPath();
			
			url+= "/regitrationConfirm?token=" + tokenService.getTokenByUser(registeredUser).getToken();
			context.setVariable("url", url);
			mailService.sendEmail("lokesh.cherukuri8@gmail.com", "Tracker : Activate account",context,"userActivation");
		}
		
		return registeredUser;
	}
	
	@Override
	public User accountActivation(String tokenValue){
		VerificationToken token= tokenService.getTokenByValue(tokenValue);
		if(token!=null)
		{
			User user=token.getUser();
			User activatedUser=userRepo.accountActivation(user);
			return activatedUser;
		}
		return null;
	}


	@Override
	public User accountRecovery(Map<String, String> inputs, HttpServletRequest request, HttpServletResponse response) throws MessagingException {
		User user=userRepo.findUserByEmail(inputs.get("email"));
		if(user!=null)
		{
			VerificationToken token=tokenService.update(user);
			final WebContext context = new WebContext(request, response, request.getServletContext(), locale);
			String url = "";
			if (request.getServerPort() == 80  || request.getServerPort() == 443 )
				url= request.getScheme() + "://" +request.getServerName() + request.getContextPath();
			else
				url= request.getScheme() + "://" +request.getServerName() + ":" + request.getServerPort() +request.getContextPath();
			
			if(inputs.get("recover").equals("activation"))
			{
				url+= "/regitrationConfirm?token=" + token.getToken();
				context.setVariable("url", url);
				System.out.println("activation url:"+ url);
				mailService.sendEmail("lokesh.cherukuri8@gmail.com", "Tracker : Activate account",context,"userActivation");
			}
			else 
			{
				url+= "/passwordReset?token=" + token.getToken();
				context.setVariable("url", url);
				System.out.println("password url:"+ url);
				mailService.sendEmail("lokesh.cherukuri8@gmail.com", "Tracker : Password Reset",context,"userActivation");
			}
			
			return user;
		}
		else
			return null;
	}

	@Override
	public User resetpassword(Map<String, String> inputs) {
		VerificationToken token=tokenService.getTokenByValue(inputs.get("token"));
		if(token!=null){
			User user=userRepo.resetpassword(token.getUser(),inputs.get("password"));
			return user;
		}
		else {
			return null;
		}
	}

}
