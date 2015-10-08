package com.tracker.config.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		
		if(exception.getClass().isAssignableFrom(DisabledException.class)){
			request.getSession().setAttribute("send_activation_link", "Your account is not yet activated."
					+ " Click activation link sent to your email or request link again.");
			super.setDefaultFailureUrl("/accountRecovery");
		}		
		
		if(exception.getClass().isAssignableFrom(BadCredentialsException.class)){
			request.getSession().setAttribute("login_error", "Incorrect username or password");
			super.setDefaultFailureUrl("/login?error");
		}
		
		super.onAuthenticationFailure(request, response, exception);
	}
}
