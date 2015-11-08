package com.lokesh.tracker.web.service.implementations;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.LocalDateTime;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import com.lokesh.tracker.integrations.EmailMessage;
import com.lokesh.tracker.web.models.Event;
import com.lokesh.tracker.web.service.interfaces.EventService;
import com.lokesh.tracker.web.service.interfaces.SummaryService;

@Service
public class SummaryServiceImpl implements SummaryService {

	private EventService eventService;
	private Locale locale=new Locale("en", "US");
	private TemplateEngine templateEngine;
	private RabbitTemplate rabbitTemplate;
	private HttpServletRequest request;
	private HttpServletResponse response;

	@Autowired
	public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@Autowired
	public void setTemplateEngine(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}
	
	@Autowired
	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	@Override
	public int sendDailySummaries() {
		List<Event> events=eventService.getEvents(new LocalDateTime().withTime(0, 0, 0, 0),new LocalDateTime().withTime(23, 59, 59, 0));
		final WebContext context = new WebContext(request, response, request.getServletContext(), locale);
		context.setVariable("events", events);
		String[] recievers={"lokesh.cherukuri8@gmail.com"};
		String content=templateEngine.process("summary", context);
		EmailMessage emailMessage=new EmailMessage(content, recievers, "Tracker : Event summary for today");
		rabbitTemplate.convertAndSend(emailMessage);
		System.out.println("sending summary");
		return 0;
	}

	@Override
	public int sendWeeklySummaries() {
		return 0;
	}

	@Override
	public int sendMontlySummaries() {
		return 0;
	}

}
