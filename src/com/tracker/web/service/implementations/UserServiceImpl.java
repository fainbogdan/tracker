package com.tracker.web.service.implementations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tracker.integrations.MailService;
import com.tracker.web.dao.interfaces.TokenRepo;
import com.tracker.web.dao.interfaces.UserRepo;
import com.tracker.web.models.Role;
import com.tracker.web.models.User;
import com.tracker.web.models.VerificationToken;
import com.tracker.web.service.interfaces.RoleService;
import com.tracker.web.service.interfaces.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private UserRepo userRepo;
	private RoleService roleService;
	private TokenRepo tokenRepo;
	private MailService mailService;
	
	@Autowired
	public void setUserRepo(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	
	public void setTokenRepo(TokenRepo tokenRepo) {
		this.tokenRepo = tokenRepo;
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
	public User register(User user) {
		List<Role> roles=new ArrayList<Role>();
		Role role=new Role();
		role.setRole("user");
		role.setUser(user);
		roleService.save(role);
		roles.add(role);
		user.setRoles(roles);
		
		VerificationToken token=new VerificationToken();
		token.setToken(UUID.randomUUID().toString());
		token.setUser(user);
		tokenRepo.save(token);
		
		String username=userRepo.save(user);
		return userRepo.findUserByUsername(username);
	}

}
