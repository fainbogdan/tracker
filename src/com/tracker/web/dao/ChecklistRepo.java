package com.tracker.web.dao;

import com.tracker.web.models.Checklist;

public interface ChecklistRepo {

	public void save(Checklist checklist);
	public Checklist update(Checklist checklist);
	public Checklist delete(int id);
}
