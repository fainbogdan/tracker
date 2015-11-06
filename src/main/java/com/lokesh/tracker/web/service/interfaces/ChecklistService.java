package com.lokesh.tracker.web.service.interfaces;

import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lokesh.tracker.web.models.Checklist;

public interface ChecklistService {
	
	public Checklist getChecklist(int id);
	public Checklist update(Checklist checklist);
	public Checklist delete(int id);
	public String sort(List<Map<String, String>> newOrder);
	public Checklist save(Map<String, String> map);
	public Map<String, Object> updateState(Checklist checklist,
			HttpServletRequest request, HttpServletResponse response) throws MessagingException;

}
