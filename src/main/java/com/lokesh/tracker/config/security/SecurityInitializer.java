package com.lokesh.tracker.config.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer{

	@Override
	protected boolean enableHttpSessionEventPublisher() {
		return true;
	}
}
