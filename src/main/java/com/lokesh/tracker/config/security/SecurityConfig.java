package com.lokesh.tracker.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.lokesh.tracker.web.service.implementations.UserServiceImpl;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses={UserServiceImpl.class,AuthFailureHandler.class,AccessDenyHandler.class})
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private AuthFailureHandler authFailureHandler;
	
	@Autowired
	private AccessDenyHandler accessDenyHandler;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired 
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
		auth
			.userDetailsService(userDetailsService); 
	}
	
	@Override
    @Bean(name = "authenticationManager")
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/", "/index", "/register", "/regitrationConfirm", "/forgotPassword", "/accountRecovery", "/passwordReset", "/public/**").permitAll()
				.antMatchers(HttpMethod.POST, "/register", "/accountRecovery","/passwordReset").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.loginPage("/login?error")
				.permitAll()
				.failureHandler(authFailureHandler)
				.and()
			.exceptionHandling().accessDeniedHandler(accessDenyHandler)
				.and()
			.rememberMe()
				.tokenValiditySeconds(3600)
				.key("rememberTracker")
				.and()
			.logout()
				.permitAll()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/")
				.and()
			.sessionManagement()
                 .maximumSessions(1)
                 .expiredUrl("/login?expired");
		
	}
}
