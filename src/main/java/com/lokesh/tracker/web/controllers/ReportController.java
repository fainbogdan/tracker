package com.lokesh.tracker.web.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lokesh.tracker.web.models.Event;
import com.lokesh.tracker.web.service.interfaces.ReportService;

@Controller
public class ReportController {

	private ReportService reportService;
	
	@Autowired
	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	@RequestMapping(value="reports/create")
	public String create() {
		return "reports/create";
	}
	
	@RequestMapping(value="/reports/create/search", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Event> search(@RequestBody Map<String, String> keyword)
	{
		System.out.println(keyword.get("keyword"));
		return reportService.search(keyword.get("keyword"));
	}
	
}
