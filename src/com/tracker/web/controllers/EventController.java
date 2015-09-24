package com.tracker.web.controllers;

import java.util.List;
import java.util.Map;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tracker.web.models.Event;
import com.tracker.web.service.implementations.UserServiceImpl.CustomUser;
import com.tracker.web.service.interfaces.EventService;
import com.tracker.web.service.interfaces.UserService;

@Controller
public class EventController {

	private EventService eventService;
	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}
	
	@ModelAttribute("loggeduser")
	public CustomUser currentUser()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUser customUser=(CustomUser) userService.loadUserByUsername(auth.getName());
		return customUser;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value="events")
	public String index(Model model,HttpServletRequest request)
	{
		int page=1;
		if(request.getParameter("page")!=null)
			page=Integer.parseInt(request.getParameter("page"));
		PagedListHolder<Event> eventsList=(PagedListHolder<Event>) request.getSession().getAttribute("eventList");
		if(eventsList==null)
		{
			eventsList=new PagedListHolder<Event>(eventService.getEvents());
			eventsList.setPageSize(10);
			request.getSession().setAttribute("eventList", eventsList);
			model.addAttribute("currentPage", page);
			model.addAttribute("pageCount", eventsList.getPageCount());
			model.addAttribute("events",eventsList.getPageList());
		}
		else
		{
			eventsList.setPage(page);
			model.addAttribute("currentPage", page);
			model.addAttribute("pageCount", eventsList.getPageCount());
			model.addAttribute("events",eventsList.getPageList());
		}	
		return "events/index";
	}
	
	@RequestMapping(value={"emergency","planned"},method=RequestMethod.GET)
	public String create(Model model,HttpServletRequest request)
	{
		model.addAttribute("event", new Event());
		if(request.getServletPath().equals("/emergency"))
		{
			model.addAttribute("eventsForToday", eventService.getEventsForToday());
			return "pages/emergency";
		}
		else
		{
			return "pages/planned";
		}
	}
	
	@RequestMapping(value={"emergency","planned"},method=RequestMethod.POST)
	public String store(@Valid Event event, Errors errors,Model model,  HttpServletRequest request, HttpServletResponse response) throws MessagingException
	{
		if(errors.hasErrors())
		{
			if(request.getServletPath().equals("/emergency"))
			{
				model.addAttribute("eventsForToday", eventService.getEventsForToday());
				return "pages/emergency";
			}
			else
				return "pages/planned";
		}
		else
		{
			Event savedEvent=eventService.save(event, request, response);
			return "redirect:"+ "events/"+savedEvent.getId();
		}
	}
	
	@RequestMapping(value="/events/{id}",method=RequestMethod.GET)
	public String show(@PathVariable("id") int id,Model model)
	{
		model.addAttribute("event", eventService.getEvent(id));
		return "events/show";
	}
	
	
	@RequestMapping(value="/events/{id}/json", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Event eventInJson(@PathVariable("id") int id) {
		return eventService.getEvent(id);
	}
	
	@RequestMapping(value="/events/month", method=RequestMethod.GET)
	@ResponseBody
	public List<Event> getEventsForMonth()
	{
		return eventService.getEventsForMonth();
	}
	
	
	@RequestMapping(value="events/{id}/edit",method=RequestMethod.GET)
	public String edit(Model model,@PathVariable("id") int id) {
		model.addAttribute("event", eventService.getEvent(id));
		return "events/edit";
	}
	
	@RequestMapping(value="/events/{id}/start", method=RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> eventStart(@RequestBody Event event, @PathVariable("id") int id, HttpServletRequest request, HttpServletResponse response) throws MessagingException
	{
		event.setId(id);
		return eventService.eventStart(event, request, response);
	}
	
	
	@RequestMapping(value="/events/{id}/end", method=RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> eventEnd(@RequestBody Event event, @PathVariable("id") int id, HttpServletRequest request, HttpServletResponse response) throws MessagingException
	{
		event.setId(id);
		return eventService.eventEnd(event, request, response);
	}
}
