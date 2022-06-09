package com.qu.gradproject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "files")
public class FilesEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "file_name")
	private String fileName;
	
	@Column(name = "upload_type")
	private String uploadType;
	
	@ManyToOne
	private GroupsEntity groupsEntity;
	
	@ManyToOne
	private UserEntity userEntity;
	
	@ManyToOne
	private HomeworkEntity homeworkEntity;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public GroupsEntity getGroupsEntity() {
		return groupsEntity;
	}


	public void setGroupsEntity(GroupsEntity groupsEntity) {
		this.groupsEntity = groupsEntity;
	}


	public String getUploadType() {
		return uploadType;
	}


	public void setUploadType(String uploadType) {
		this.uploadType = uploadType;
	}


	public UserEntity getUserEntity() {
		return userEntity;
	}


	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}


	public HomeworkEntity getHomeworkEntity() {
		return homeworkEntity;
	}


	public void setHomeworkEntity(HomeworkEntity homeworkEntity) {
		this.homeworkEntity = homeworkEntity;
	}
	
	
	
	
}
