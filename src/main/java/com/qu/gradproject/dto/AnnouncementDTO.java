package com.qu.gradproject.dto;

import com.qu.gradproject.entity.GroupsEntity;

public class AnnouncementDTO {
	private Long id;
	private String title;
	private String description;
	private GroupsEntity groupsEntity;

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

	public GroupsEntity getGroupsEntity() {
		return groupsEntity;
	}

	public void setGroupsEntity(GroupsEntity groupsEntity) {
		this.groupsEntity = groupsEntity;
	}

}
