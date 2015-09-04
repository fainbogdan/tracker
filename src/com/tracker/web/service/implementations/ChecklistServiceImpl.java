package com.tracker.web.service.implementations;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.WebContext;
import com.tracker.integrations.MailService;
import com.tracker.web.dao.interfaces.ChecklistRepo;
import com.tracker.web.dao.interfaces.EventRepo;
import com.tracker.web.models.Checklist;
import com.tracker.web.models.Event;
import com.tracker.web.service.implementations.UserServiceImpl.CustomUser;
import com.tracker.web.service.interfaces.ChecklistService;
import com.tracker.web.service.interfaces.UserService;

@Service
@Transactional
public class ChecklistServiceImpl implements ChecklistService{

	private ChecklistRepo checklistRepo;
	private EventRepo eventRepo;
	private MailService mailService;
	private UserService userService;
	private Locale locale=new Locale("en", "US");
	
	@Autowired
	public void setMailService(MailService mailService) {
		this.mailService = mailService;
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
		return checklistRepo.getChecklist(id);
	}


	@Override
	public Checklist save(Map<String, String> map) {
		Event event=eventRepo.getEvent(Integer.parseInt(map.get("event_id")));
		Checklist checklist=new Checklist();
		checklist.setName(map.get("name"));
		checklist.setDetails(map.get("details"));
		checklist.setEvent(event);
		checklist.setCreator(currentUser());
		int id=checklistRepo.save(checklist);
		return checklistRepo.getChecklist(id);
	}
	
	@Override
	public Checklist update(Checklist checklist) {
		return checklistRepo.update(checklist);
	}


	@Override
	public Map<String, Object> updateState(Checklist ch, HttpServletRequest request, HttpServletResponse response) throws MessagingException 
	{
		Map<String, Object> data=new HashMap<String, Object>();
		if(checklistRepo.arePreviousItemsDone(ch))
		{
			ch.setFinisher(currentUser());
			ch.setCompleted_on(new LocalDateTime());
			Checklist updatedChecklist=checklistRepo.updateState(ch);
			data.put("checklist", updatedChecklist);
			data.put("message", "success");
			
			final WebContext context = new WebContext(request, response, request.getServletContext(), locale);
			context.setVariable("event", updatedChecklist.getEvent());
			mailService.sendEmail("lokesh.cherukuri8@gmail.com", "Tracker : Event updated",context);
			
			return data;
		}
		
		data.put("checklist", checklistRepo.getChecklist(ch.getId()));
		data.put("message", "Please complete all prior checklist items before this  ");
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
