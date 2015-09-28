package com.tracker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import com.tracker.web.controllers.EventsApprovalInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan("com.tracker.web.controllers")
public class WebConfig extends WebMvcConfigurerAdapter{
	
	private EventsApprovalInterceptor eventsApprovalInterceptor;
	
	@Autowired
	public void setLoggedUserInterceptor(EventsApprovalInterceptor eventsApprovalInterceptor) {
		this.eventsApprovalInterceptor = eventsApprovalInterceptor;
	}

	@Bean
	public TilesConfigurer tilesConfigurer()
	{
		TilesConfigurer configurer=new TilesConfigurer();
		configurer.setDefinitions(new String[] {"/WEB-INF/views/layout/tiles.xml"});
		configurer.setCheckRefresh(true);
		return configurer;
	}
	
	@Bean
	public TilesViewResolver tilesViewResolver()
	{
		return new TilesViewResolver();
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer)
	{
		configurer.enable();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(eventsApprovalInterceptor);
	}
	
}
