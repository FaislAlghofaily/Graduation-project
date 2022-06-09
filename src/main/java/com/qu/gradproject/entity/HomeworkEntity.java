package com.qu.gradproject.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "homework")
public class HomeworkEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "deadline")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date deadline;
	
	@Column(name = "status")
	private String status;
	
	@ManyToOne
	private GroupsEntity groupsEntity;

	@OneToMany(mappedBy = "homeworkEntity")
	private List<FilesEntity> contentEntity;

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


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getDeadline() {
		return deadline;
	}


	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}


	public GroupsEntity getGroupsEntity() {
		return groupsEntity;
	}


	public void setGroupsEntity(GroupsEntity groupsEntity) {
		this.groupsEntity = groupsEntity;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	
	
}
