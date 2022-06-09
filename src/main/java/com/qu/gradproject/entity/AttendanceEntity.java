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
@Table(name = "attendancetaple")
public class AttendanceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "status")
	private String status;
	
	@Column(columnDefinition = "DATE",name ="dateoflecture")
	private LocalDate dateOfLecture;
	
	@ManyToOne
	private GroupsEntity groupsEntity;

	@ManyToOne
	private UserEntity userEntity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfLecture == null) ? 0 : dateOfLecture.hashCode());
		result = prime * result + ((groupsEntity == null) ? 0 : groupsEntity.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((userEntity == null) ? 0 : userEntity.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AttendanceEntity other = (AttendanceEntity) obj;
		if (dateOfLecture == null) {
			if (other.dateOfLecture != null)
				return false;
		} else if (!dateOfLecture.equals(other.dateOfLecture))
			return false;
		if (groupsEntity == null) {
			if (other.groupsEntity != null)
				return false;
		} else if (!groupsEntity.equals(other.groupsEntity))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (userEntity == null) {
			if (other.userEntity != null)
				return false;
		} else if (!userEntity.equals(other.userEntity))
			return false;
		return true;
	}
	
	
	
}
