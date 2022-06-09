package com.qu.gradproject.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "codecheck")
public class CodeCheckEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "codeforattendance")
	private String attendanceCode;
	
	@Column(columnDefinition = "DATE",name ="dateoflecture")
	private LocalDate dateOfLecture;
	
	@ManyToOne
	private GroupsEntity groupsEntity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAttendanceCode() {
		return attendanceCode;
	}

	public void setAttendanceCode(String attendanceCode) {
		this.attendanceCode = attendanceCode;
	}



	public LocalDate getDate() {
		return dateOfLecture;
	}

	public void setDate(LocalDate date) {
		this.dateOfLecture = date;
	}

	public GroupsEntity getGroupsEntity() {
		return groupsEntity;
	}

	public void setGroupsEntity(GroupsEntity groupsEntity) {
		this.groupsEntity = groupsEntity;
	}
	
}
