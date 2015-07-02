package com.tracker.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tracker.web.dao.TrackerRepo;
import com.tracker.web.models.Event;

@Service
@Transactional
public class TrackerServiceImpl implements TrackerService {

	private TrackerRepo repo;

	@Autowired
	public void setRepo(TrackerRepo repo) {
		this.repo = repo;
	}

	public void save(Event event) {
		repo.save(event);
	}

}
