package com.tracker.web.service;

import com.tracker.web.models.Checklist;

public interface ChecklistService {
	
	public Checklist update(Checklist checklist);
	public Checklist delete(int id);
}
