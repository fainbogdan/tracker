package com.lokesh.tracker.config;

import java.util.Calendar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.lokesh.tracker.integrations.jobs.DailySummary;

@Configuration
public class QuartzConfig {

	@Bean
	public JobDetailFactoryBean dailySummary(){
		JobDetailFactoryBean bean=new JobDetailFactoryBean();
		bean.setJobClass(DailySummary.class);
		bean.setName("dailySummary");
		bean.setGroup("dailySummaries");
		return bean;
	}
	
	@Bean
	public JobDetailFactoryBean weeklySummary() {
		JobDetailFactoryBean bean=new JobDetailFactoryBean();
		bean.setJobClass(DailySummary.class);
		bean.setName("weeklySummary");
		bean.setGroup("weeklySummaries");
		return bean;
	}
	
	@Bean
	public JobDetailFactoryBean monthlySummary() {
		JobDetailFactoryBean bean=new JobDetailFactoryBean();
		bean.setJobClass(DailySummary.class);
		bean.setName("monthlySummary");
		bean.setGroup("monthlySummaries");
		return bean;
	}
	
	@Bean
	public CronTriggerFactoryBean dailySummarytrigger(){
		Calendar calendar=Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE, 1);
		calendar.set(Calendar.SECOND, 0);
		
		CronTriggerFactoryBean bean=new CronTriggerFactoryBean();
		bean.setJobDetail(dailySummary().getObject());
		bean.setStartTime(calendar.getTime());
		bean.setBeanName("dailySummarytrigger");
		bean.setGroup("dailySummaries");
		bean.setCronExpression("0 1 0 * * ?");
		return bean;
	}
	
	@Bean
	public CronTriggerFactoryBean weeklySummarytrigger(){
		Calendar calendar=Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE, 1);
		calendar.set(Calendar.SECOND, 0);
		
		CronTriggerFactoryBean bean=new CronTriggerFactoryBean();
		bean.setJobDetail(dailySummary().getObject());
		bean.setStartTime(calendar.getTime());
		bean.setBeanName("weeklySummarytrigger");
		bean.setGroup("weeklySummaries");
		bean.setCronExpression("0 1 0 ? * 2");
		return bean;
	}
	
	@Bean
	public CronTriggerFactoryBean monthlySummarytrigger(){
		Calendar calendar=Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE, 1);
		calendar.set(Calendar.SECOND, 0);
		
		CronTriggerFactoryBean bean=new CronTriggerFactoryBean();
		bean.setJobDetail(dailySummary().getObject());
		bean.setStartTime(calendar.getTime());
		bean.setBeanName("monthlySummarytrigger");
		bean.setGroup("monthlySummaries");
		bean.setCronExpression("0 1 0 1 * ?");
		return bean;
	}
	
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {
		SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
		scheduler.setTriggers(dailySummarytrigger().getObject(),weeklySummarytrigger().getObject(),monthlySummarytrigger().getObject());
		return scheduler;
	} 
}
