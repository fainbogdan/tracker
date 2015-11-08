package com.lokesh.tracker.integrations.jobs;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.lokesh.tracker.web.service.interfaces.SummaryService;

@DisallowConcurrentExecution
public class DailySummary extends QuartzJobBean{

	private SummaryService summaryService;
	
	@Autowired
	public void setSummaryService(SummaryService summaryService) {
		this.summaryService = summaryService;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		System.out.println(summaryService);
		summaryService.sendDailySummaries();
	}

}
