package com.tracker.web.controllers;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tracker.web.models.Checklist;
import com.tracker.web.models.Event;
import com.tracker.web.service.implementations.UserServiceImpl.CustomUser;
import com.tracker.web.service.interfaces.ChecklistService;
import com.tracker.web.service.interfaces.UserService;

@Controller
public class ChecklistController {

	private ChecklistService checklistService;
	private UserService userService;

	@Autowired
	public void setChecklistService(ChecklistService checklistService) {
		this.checklistService = checklistService;
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
	
	@RequestMapping(value="/checklists/{id}", method=RequestMethod.GET)
	public String show(@PathVariable("id") int id, Model model)
	{
		model.addAttribute("loggeduser", currentUser());
		model.addAttribute("checklist", checklistService.getChecklist(id));
		return "checklists/show";
	}
	
	@RequestMapping(value="/checklists/{id}/json", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Checklist checklistInJson(@PathVariable("id") int id) {
		return checklistService.getChecklist(id);
	}
	
	@RequestMapping(value="/checklist",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Checklist store(@RequestBody Map<String, String> map) {
		return checklistService.save(map);
	}

	@RequestMapping(value="/checklist/{id}",method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE , produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Checklist update(@RequestBody Checklist checklist, @PathVariable("id") int id)
	{
		checklist.setId(id);
		return checklistService.update(checklist);
	}
	
	@RequestMapping(value="/checklistState/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> updateState(@RequestBody Checklist checklist, @PathVariable("id") int id, HttpServletRequest request, HttpServletResponse response) throws MessagingException 
	{
		checklist.setId(id);
		return checklistService.updateState(checklist, request,response);
	}
	
	@RequestMapping(value="/checklist/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public Checklist delete(@PathVariable("id") int id)
	{
		return checklistService.delete(id);
	}
	
	@RequestMapping(value="/checklist/sort", method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String sort(@RequestBody List< Map<String, String> > newOrder)
	{
		return checklistService.sort(newOrder);
	}
	
	@RequestMapping(value="/checklist/{id}", method=RequestMethod.POST)
	public String fileUpload(@RequestPart("attachedFiles") MultipartFile file,@PathVariable("id") int id, Model model) throws IllegalStateException, IOException {
		file.transferTo(new File(file.getOriginalFilename()));
		model.addAttribute("checklist", checklistService.getChecklist(id));
		return "checklists/show";
	}
	
}
