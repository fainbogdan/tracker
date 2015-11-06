package com.lokesh.tracker.web.dao.interfaces;

import java.util.List;
import java.util.Map;

import com.lokesh.tracker.web.models.Checklist;

public interface ChecklistRepo {

	public Checklist getChecklist(int id);
	public Checklist save(Checklist checklist);
	public Checklist update(Checklist checklist);
	public Checklist delete(int id);
	public String sort(List<Map<String, String>> newOrder);
	public boolean arePreviousItemsDone(Checklist checklist);
	public Checklist updateState(Checklist checklist);

}
