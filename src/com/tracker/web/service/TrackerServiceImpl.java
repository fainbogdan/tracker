package com.tracker.web.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tracker.web.dao.TrackerRepo;
import com.tracker.web.models.Checklist;
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
		Checklist plan=new Checklist();
		plan.setName("plan");
		plan.setItem_order(1);
		plan.setPhase("setup");
		plan.setEvent(event);
		repo.save(plan);
		
		Checklist process=new Checklist();
		process.setName("process");
		process.setItem_order(2);
		process.setPhase("execute");
		process.setEvent(event);
		repo.save(process);
		
		Checklist test=new Checklist();
		test.setName("test");
		test.setItem_order(3);
		test.setPhase("execute");
		test.setEvent(event);
		repo.save(test);
		
		Checklist approve=new Checklist();
		approve.setName("approve");
		approve.setItem_order(4);
		approve.setPhase("teardown");
		approve.setEvent(event);
		repo.save(approve);
		
		Collection<Checklist> checklist=new ArrayList<Checklist>();
		checklist.add(plan);
		checklist.add(process);
		checklist.add(test);
		checklist.add(approve);
		
		event.setChecklists(checklist);
		repo.save(event);
	}

	@Override
	public Event getEvent(int id) {
		return repo.getEvent(id);
	}

	@Override
	public List<Event> getEvents(HttpServletRequest request) {
		String page=request.getParameter("page");
		
		return repo.getEvents();
	}

	@Override
	public Checklist update(Checklist checklist) {
		return repo.update(checklist);
	}

}
