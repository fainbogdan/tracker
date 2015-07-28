package com.tracker.web.controllers;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tracker.web.models.Checklist;
import com.tracker.web.service.ChecklistService;

@Controller
public class ChecklistController {

	private ChecklistService checklistService;

	@Autowired
	public void setChecklistService(ChecklistService checklistService) {
		this.checklistService = checklistService;
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
	public Map<String, Object> updateState(@RequestBody Checklist checklist, @PathVariable("id") int id) 
	{
		checklist.setId(id);
		return checklistService.updateState(checklist);
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
	
}
