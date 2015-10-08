package com.tracker.config.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class AccessDenyHandler implements AccessDeniedHandler{

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException arg2) throws IOException, ServletException {
		if("XMLHttpRequest".equals(request.getHeader("X-Requested-With")))
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
		else {
			String referer=request.getHeader("referer");
			if(referer!=null)
				response.sendRedirect(referer);
			else
				response.sendRedirect(request.getContextPath()+"/accessdenied");
		}
	}

}
