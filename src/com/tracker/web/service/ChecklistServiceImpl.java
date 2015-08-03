package com.tracker.web.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tracker.web.dao.ChecklistRepo;
import com.tracker.web.dao.EventRepo;
import com.tracker.web.models.Checklist;
import com.tracker.web.models.Event;

@Service
@Transactional
public class ChecklistServiceImpl implements ChecklistService{

	private ChecklistRepo checklistRepo;
	private EventRepo eventRepo;

	@Autowired
	public void setRepo(ChecklistRepo checklistRepo) {
		this.checklistRepo = checklistRepo;
	}

	@Autowired
	public void setEventRepo(EventRepo eventRepo) {
		this.eventRepo = eventRepo;
	}
	
	
	@Override
	public Checklist getChecklist(int id) {
		return checklistRepo.getChecklist(id);
	}


	@Override
	public Checklist save(Map<String, String> map) {
		Event event=eventRepo.getEvent(Integer.parseInt(map.get("event_id")));
		Checklist checklist=new Checklist();
		checklist.setName(map.get("name"));
		checklist.setName(map.get("details"));
		checklist.setEvent(event);
		return checklistRepo.save(checklist);
	}
	
	@Override
	public Checklist update(Checklist checklist) {
		return checklistRepo.update(checklist);
	}


	@Override
	public Map<String, Object> updateState(Checklist ch) 
	{
		Map<String, Object> data=new HashMap<String, Object>();
		if(checklistRepo.arePreviousItemsDone(ch))
		{
			Checklist updatedChecklist=checklistRepo.updateState(ch);
			data.put("checklist", updatedChecklist);
			data.put("message", "success");
			return data;
		}
		
		data.put("checklist", checklistRepo.getChecklist(ch.getId()));
		data.put("message", "Please complete all prior checklist items before this  ");
		return data;
	}
	
	
	@Override
	public Checklist delete(int id) {
		return checklistRepo.delete(id);
	}

	@Override
	public String sort(List<Map<String, String>> newOrder) {
		return checklistRepo.sort(newOrder);
	}
	
}
