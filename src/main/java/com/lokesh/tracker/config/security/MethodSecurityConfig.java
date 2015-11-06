package com.lokesh.tracker.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
@ComponentScan(basePackageClasses={EventWritePermissionEvaluator.class})
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration{
	
	private EventWritePermissionEvaluator eventWritePermissionEvaluator;
	
	@Autowired
	public void setEventWritePermissionEvaluator(
			EventWritePermissionEvaluator eventWritePermissionEvaluator) {
		this.eventWritePermissionEvaluator = eventWritePermissionEvaluator;
	}


	@Override
	protected MethodSecurityExpressionHandler createExpressionHandler() {
		DefaultMethodSecurityExpressionHandler expressionHandler=new DefaultMethodSecurityExpressionHandler();
		expressionHandler.setPermissionEvaluator(eventWritePermissionEvaluator);
		return expressionHandler;
	}
}
