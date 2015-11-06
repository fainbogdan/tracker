package com.lokesh.tracker.web.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lokesh.tracker.web.dao.interfaces.ReportRepo;
import com.lokesh.tracker.web.models.Event;
import com.lokesh.tracker.web.service.interfaces.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

	private ReportRepo reportRepo;
	
	@Autowired
	public void setReportRepo(ReportRepo reportRepo) {
		this.reportRepo = reportRepo;
	}

	@Override
	public List<Event> search(String keyword) {
		return reportRepo.search(keyword);
	}

}
