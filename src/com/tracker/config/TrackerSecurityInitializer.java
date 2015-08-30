package com.tracker.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class TrackerSecurityInitializer extends AbstractSecurityWebApplicationInitializer{

	@Override
	protected boolean enableHttpSessionEventPublisher() {
		return true;
	}
}
