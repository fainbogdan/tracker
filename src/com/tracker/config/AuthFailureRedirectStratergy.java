package com.tracker.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.DefaultRedirectStrategy;

public class AuthFailureRedirectStratergy extends DefaultRedirectStrategy{

	@Override
	public void sendRedirect(HttpServletRequest request,
			HttpServletResponse response, String url) throws IOException {
		super.sendRedirect(request, response, url);
	}
}
