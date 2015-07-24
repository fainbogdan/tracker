package com.tracker.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tracker.web.models.Checklist;
import com.tracker.web.service.TrackerService;

@Controller
public class ChecklistController {
	private TrackerService service;

	@Autowired
	public void setService(TrackerService service) {
		this.service = service;
	}
	
	@RequestMapping(value="/checklist/{id}",method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE , produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Checklist update(@RequestBody Checklist checklist, @PathVariable("id") int id)
	{
		checklist.setId(id);
		return service.update(checklist);
		
	}
	
}
