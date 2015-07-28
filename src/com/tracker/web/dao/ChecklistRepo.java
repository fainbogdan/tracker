package com.tracker.web.dao;

import java.util.List;
import java.util.Map;

import com.tracker.web.models.Checklist;
import com.tracker.web.models.Event;

public interface ChecklistRepo {

	public Checklist getChecklist(int id);
	public Checklist save(Checklist checklist);
	public Checklist update(Checklist checklist);
	public Checklist delete(int id);
	public String sort(List<Map<String, String>> newOrder);
	public boolean arePreviousItemsDone(Checklist checklist);
	public Checklist updateState(Checklist checklist);
}
