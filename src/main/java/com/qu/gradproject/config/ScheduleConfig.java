package com.qu.gradproject.config;




import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.qu.gradproject.service.HomeworkService;

@Configuration
@EnableScheduling
public class ScheduleConfig {

	@Autowired
	HomeworkService homeworkService;
	
	@Scheduled(cron = "0 0 0 * * *")
	public void dailyScheduleTask() {
		//this task will run everyday at 12 am
	    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date();  
	   homeworkService.checkDeadLines(date);
	}
	
}
