package com.tracker.web.service;

import java.util.List;
import java.util.Map;

import com.tracker.web.models.Checklist;

public interface ChecklistService {
	
	public Checklist getChecklist(int id);
	public Checklist update(Checklist checklist);
	public Checklist delete(int id);
	public String sort(List<Map<String, String>> newOrder);
	public Checklist save(Map<String, String> map);
	public Map<String, Object> updateState(Checklist checklist);

}
