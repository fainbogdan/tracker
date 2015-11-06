package com.lokesh.tracker.web.service.interfaces;

import java.util.List;

import com.lokesh.tracker.web.models.Event;

public interface ReportService {
	public List<Event> search(String keyword);
}
