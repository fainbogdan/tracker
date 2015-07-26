package com.tracker.web.dao;

import java.util.List;
import java.util.Map;

import com.tracker.web.models.Checklist;

public interface ChecklistRepo {

	public Checklist save(Checklist checklist);
	public Checklist update(Checklist checklist);
	public Checklist delete(int id);
	public String sort(List<Map<String, String>> newOrder);
}
