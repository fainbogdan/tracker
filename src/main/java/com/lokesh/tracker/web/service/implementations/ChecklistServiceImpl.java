package com.lokesh.tracker.web.service.implementations;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.joda.time.LocalDateTime;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import com.lokesh.tracker.integrations.EmailMessage;
import com.lokesh.tracker.web.dao.interfaces.ChecklistRepo;
import com.lokesh.tracker.web.dao.interfaces.EventRepo;
import com.lokesh.tracker.web.models.Checklist;
import com.lokesh.tracker.web.models.Event;
import com.lokesh.tracker.web.service.implementations.UserServiceImpl.CustomUser;
import com.lokesh.tracker.web.service.interfaces.ChecklistService;
import com.lokesh.tracker.web.service.interfaces.UserService;

@Service
@Transactional
public class ChecklistServiceImpl implements ChecklistService{

	private ChecklistRepo checklistRepo;
	private EventRepo eventRepo;
	private UserService userService;
	private Locale locale=new Locale("en", "US");
	private TemplateEngine templateEngine;
	private RabbitTemplate rabbitTemplate;

	@Autowired
	public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	@Autowired
	public void setTemplateEngine(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}

	@Autowired
	public void setRepo(ChecklistRepo checklistRepo) {
		this.checklistRepo = checklistRepo;
	}

	@Autowired
	public void setEventRepo(EventRepo eventRepo) {
		this.eventRepo = eventRepo;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public CustomUser currentUser()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUser customUser=(CustomUser) userService.loadUserByUsername(auth.getName());
		return customUser;
	}
	
	@Override
	public Checklist getChecklist(int id) {
		Checklist checklist=checklistRepo.getChecklist(id);
		return checklist;
	}


	@Override
	public Checklist save(Map<String, String> map) {
		Event event=eventRepo.getEvent(Integer.parseInt(map.get("event_id")));
		Checklist checklist=new Checklist();
		checklist.setName(map.get("name"));
		checklist.setDetails(map.get("details"));
		checklist.setEvent(event);
		checklist.setCreator(currentUser());
		return checklistRepo.save(checklist);
	}
	
	
	@Override
	public Checklist update(Checklist checklist) {
		return checklistRepo.update(checklist);
	}


	@Override
	public Map<String, Object> updateState(Checklist ch, HttpServletRequest request, HttpServletResponse response) throws MessagingException 
	{
		Map<String, Object> data=new HashMap<String, Object>();
		if(checklistRepo.getChecklist(ch.getId()).getEvent().isApproved()){
			if(checklistRepo.arePreviousItemsDone(ch))
			{
				ch.setFinisher(currentUser());
				ch.setCompleted_on(new LocalDateTime());
				Checklist updatedChecklist=checklistRepo.updateState(ch);
				data.put("checklist", updatedChecklist);
				data.put("message", "success");
				
				final WebContext context = new WebContext(request, response, request.getServletContext(), locale);
				context.setVariable("event", updatedChecklist.getEvent());
				String[] recievers={"lokesh.cherukuri8@gmail.com"};
				String content=templateEngine.process("eventUpdate", context);
				EmailMessage emailMessage=new EmailMessage(content, recievers, "Tracker : Event updated");
				rabbitTemplate.convertAndSend(emailMessage);
				
				return data;
			}
			else{
				data.put("checklist", checklistRepo.getChecklist(ch.getId()));
				data.put("message", "Please complete all prior checklist items before this  ");
			}
		}
		else{
			data.put("checklist", checklistRepo.getChecklist(ch.getId()));
			data.put("message", "Event not yet approved. contact manager ");
		}
		
		return data;
	}
	
	
	@Override
	public Checklist delete(int id) {
		return checklistRepo.delete(id);
	}

	@Override
	public String sort(List<Map<String, String>> newOrder) {
		return checklistRepo.sort(newOrder);
	}
	
}
