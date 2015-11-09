package com.lokesh.tracker.web.service.implementations;

import java.util.List;
import java.util.Locale;
import javax.transaction.Transactional;
import org.joda.time.LocalDateTime;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.thymeleaf.context.Context;
import com.lokesh.tracker.integrations.EmailMessage;
import com.lokesh.tracker.web.models.Event;
import com.lokesh.tracker.web.service.interfaces.EventService;
import com.lokesh.tracker.web.service.interfaces.SummaryService;

@Service
@Transactional
public class SummaryServiceImpl implements SummaryService {

	private EventService eventService;
	private Locale locale=new Locale("en", "US");
	private RabbitTemplate rabbitTemplate;
	@Autowired
	public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	@Autowired
	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	@Override
	public void sendDailySummaries() {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		LocalDateTime localDateTime= new LocalDateTime();
		List<Event> events=eventService.getEvents(localDateTime.withTime(0, 0, 0, 0),localDateTime.withTime(23, 59, 59, 0));
		final Context context = new Context(locale);
		context.setVariable("events", events);
		String[] recievers={"lokesh.cherukuri8@gmail.com"};
		String content="";
		
		for(Event event:events){
			content+=event.getName()+"--"+event.getEnvironment()+"\n";
		}
		
		EmailMessage emailMessage=new EmailMessage(content, recievers, "Tracker : Event summary for today");
		rabbitTemplate.convertAndSend(emailMessage);
	}

	@Override
	public void sendWeeklySummaries() {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		LocalDateTime localDateTime= new LocalDateTime();
		List<Event> events=eventService.getEvents(localDateTime.withTime(0, 0, 0, 0),localDateTime.plusDays(6).withTime(23, 59, 59, 0));
		final Context context = new Context(locale);
		context.setVariable("events", events);
		String[] recievers={"lokesh.cherukuri8@gmail.com"};
		String content="";
		
		for(Event event:events){
			content+=event.getName()+"--"+event.getEnvironment()+"\n";
		}
		
		EmailMessage emailMessage=new EmailMessage(content, recievers, "Tracker : Event summary for this week");
		rabbitTemplate.convertAndSend(emailMessage);
	}

	@Override
	public void sendMontlySummaries() {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		LocalDateTime localDateTime= new LocalDateTime();
		List<Event> events=eventService.getEvents(localDateTime.withTime(0, 0, 0, 0),localDateTime.withDayOfMonth(localDateTime.dayOfMonth().getMaximumValue()).withTime(23, 59, 59, 0));
		final Context context = new Context(locale);
		context.setVariable("events", events);
		String[] recievers={"lokesh.cherukuri8@gmail.com"};
		String content="";
		
		for(Event event:events){
			content+=event.getName()+"--"+event.getEnvironment()+"\n";
		}
		
		EmailMessage emailMessage=new EmailMessage(content, recievers, "Tracker : Event summary for this month");
		rabbitTemplate.convertAndSend(emailMessage);
	}

}
