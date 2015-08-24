package com.tracker.web.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.tracker.web.dao.UserRepo;
import com.tracker.web.models.User;

@Service
public class UserServiceImpl implements UserService {

	private UserRepo userRepo;
	
	@Autowired
	public void setUserRepo(UserRepo userRepo) {
		this.userRepo = userRepo;
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

}
