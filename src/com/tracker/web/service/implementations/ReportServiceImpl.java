package com.tracker.web.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tracker.web.dao.interfaces.ReportRepo;
import com.tracker.web.models.Event;
import com.tracker.web.service.interfaces.ReportService;

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
