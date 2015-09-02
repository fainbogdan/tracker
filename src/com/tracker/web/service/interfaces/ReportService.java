package com.tracker.web.service.interfaces;

import java.util.List;

import com.tracker.web.models.Event;

public interface ReportService {
	public List<Event> search(String keyword);
}
