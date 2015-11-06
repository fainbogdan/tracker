package com.lokesh.tracker.web.dao.interfaces;

import java.util.List;

import com.lokesh.tracker.web.models.Event;

public interface ReportRepo {
	public List<Event> search(String keyword);
}
