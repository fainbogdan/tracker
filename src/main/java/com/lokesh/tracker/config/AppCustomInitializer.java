package com.lokesh.tracker.config;

import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.orm.hibernate4.support.OpenSessionInViewFilter;
import org.springframework.web.WebApplicationInitializer;

public class AppCustomInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		Dynamic filter=servletContext.addFilter("openSessionInViewFilter", OpenSessionInViewFilter.class);
		filter.setInitParameter("sessionFactoryBeanName", "sessionFactory");
		filter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST,DispatcherType.FORWARD),false,"/*");
	}

}
