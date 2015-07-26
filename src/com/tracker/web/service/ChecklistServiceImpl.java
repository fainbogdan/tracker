package com.tracker.web.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tracker.web.dao.ChecklistRepo;
import com.tracker.web.models.Checklist;

@Service
@Transactional
public class ChecklistServiceImpl implements ChecklistService{

	private ChecklistRepo checklistRepo;

	@Autowired
	public void setRepo(ChecklistRepo checklistRepo) {
		this.checklistRepo = checklistRepo;
	}

	@Override
	public Checklist save(Checklist checklist) {
		return checklistRepo.save(checklist);
	}
	
	@Override
	public Checklist update(Checklist checklist) {
		return checklistRepo.update(checklist);
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
