package com.tracker.web.dao;

import com.tracker.web.models.Event;

public interface TrackerRepo {
	
	public void save(Event event);
}
