package com.qu.gradproject.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "classgroups")
public class GroupsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name="group_number")
	private Long groupNumber;
	
	@Column(name = "class_name")
	private String className;
	
	@Column(name = "classroom")
	private String classroom;
	
	@Column(name = "lecture_time")
	private String lectureTime;

	@OneToMany(mappedBy = "groupsEntity")
	private List<FilesEntity> contentEntity;
	
	@OneToMany(mappedBy = "groupsEntity")
	private List<CodeCheckEntity> codeCheckEntity;
	
	@OneToMany(mappedBy = "groupsEntity")
	private List<AttendanceEntity> attendanceEntity;
	
	@OneToMany(mappedBy = "groupsEntity")
	private List<AnnouncementEntity> announcementEntity;
	
	@ManyToMany(mappedBy = "groupsEntities")
	private List<UserEntity> userEntities;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(Long groupNumber) {
		this.groupNumber = groupNumber;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassroom() {
		return classroom;
	}

	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}

	public String getLectureTime() {
		return lectureTime;
	}

	public void setLectureTime(String lectureTime) {
		this.lectureTime = lectureTime;
	}

	public List<UserEntity> getUserEntities() {
		return userEntities;
	}

	public void setUserEntities(List<UserEntity> userEntities) {
		this.userEntities = userEntities;
	}

	public List<FilesEntity> getContentEntity() {
		return contentEntity;
	}

	public void setContentEntity(List<FilesEntity> contentEntity) {
		this.contentEntity = contentEntity;
	}

	public List<AnnouncementEntity> getAnnouncementEntity() {
		return announcementEntity;
	}

	public void setAnnouncementEntity(List<AnnouncementEntity> announcementEntity) {
		this.announcementEntity = announcementEntity;
	}

	public List<CodeCheckEntity> getCodeCheckEntity() {
		return codeCheckEntity;
	}

	public void setCodeCheckEntity(List<CodeCheckEntity> codeCheckEntity) {
		this.codeCheckEntity = codeCheckEntity;
	}

	public List<AttendanceEntity> getAttendanceEntity() {
		return attendanceEntity;
	}

	public void setAttendanceEntity(List<AttendanceEntity> attendanceEntity) {
		this.attendanceEntity = attendanceEntity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((announcementEntity == null) ? 0 : announcementEntity.hashCode());
		result = prime * result + ((attendanceEntity == null) ? 0 : attendanceEntity.hashCode());
		result = prime * result + ((className == null) ? 0 : className.hashCode());
		result = prime * result + ((classroom == null) ? 0 : classroom.hashCode());
		result = prime * result + ((codeCheckEntity == null) ? 0 : codeCheckEntity.hashCode());
		result = prime * result + ((contentEntity == null) ? 0 : contentEntity.hashCode());
		result = prime * result + ((groupNumber == null) ? 0 : groupNumber.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lectureTime == null) ? 0 : lectureTime.hashCode());
		result = prime * result + ((userEntities == null) ? 0 : userEntities.hashCode());
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
		GroupsEntity other = (GroupsEntity) obj;
		if (announcementEntity == null) {
			if (other.announcementEntity != null)
				return false;
		} else if (!announcementEntity.equals(other.announcementEntity))
			return false;
		if (attendanceEntity == null) {
			if (other.attendanceEntity != null)
				return false;
		} else if (!attendanceEntity.equals(other.attendanceEntity))
			return false;
		if (className == null) {
			if (other.className != null)
				return false;
		} else if (!className.equals(other.className))
			return false;
		if (classroom == null) {
			if (other.classroom != null)
				return false;
		} else if (!classroom.equals(other.classroom))
			return false;
		if (codeCheckEntity == null) {
			if (other.codeCheckEntity != null)
				return false;
		} else if (!codeCheckEntity.equals(other.codeCheckEntity))
			return false;
		if (contentEntity == null) {
			if (other.contentEntity != null)
				return false;
		} else if (!contentEntity.equals(other.contentEntity))
			return false;
		if (groupNumber == null) {
			if (other.groupNumber != null)
				return false;
		} else if (!groupNumber.equals(other.groupNumber))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lectureTime == null) {
			if (other.lectureTime != null)
				return false;
		} else if (!lectureTime.equals(other.lectureTime))
			return false;
		if (userEntities == null) {
			if (other.userEntities != null)
				return false;
		} else if (!userEntities.equals(other.userEntities))
			return false;
		return true;
	}

	



	
	
}