package com.qu.gradproject.dto;

import java.time.LocalDate;

public class AttendanceDTO {
	
	private Long id;
	private String name;
	private String status;
	private String codeOfAttendance;
	
	private String dateOfLecture;

	public String getCodeOfAttendance() {
		return codeOfAttendance;
	}

	public void setCodeOfAttendance(String codeOfAttendance) {
		this.codeOfAttendance = codeOfAttendance;
	}

	public String getDateOfLecture() {
		return dateOfLecture;
	}

	public void setDateOfLecture(String dateOfLecture) {
		this.dateOfLecture = dateOfLecture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	
	
}
