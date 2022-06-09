package com.qu.gradproject.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class HomeworkDTO {

	private Long id;

	private String title;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date deadline;
	
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
