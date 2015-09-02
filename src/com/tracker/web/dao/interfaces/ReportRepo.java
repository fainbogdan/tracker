package com.tracker.web.dao.interfaces;

import java.util.List;

import com.tracker.web.models.Event;

public interface ReportRepo {
	public List<Event> search(String keyword);
}
