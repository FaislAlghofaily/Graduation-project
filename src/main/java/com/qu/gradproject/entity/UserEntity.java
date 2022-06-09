package com.qu.gradproject.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "users")
@DynamicUpdate
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "role")
	private String role;

	@Column(name = "qu_number")
	private Long quNumber;

	// one to one
	@OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private StudentEntity studentEntity;

	@OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private InstructorEntity instructorEntity;

	@OneToMany(mappedBy = "userEntity")
	private List<FilesEntity> contentEntity;
	//one to many
	@OneToMany(mappedBy = "userEntity")
	private List<AttendanceEntity> attendanceEntity;
	// many to many
	@ManyToMany
	private List<GroupsEntity> groupsEntities;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getQuNumber() {
		return quNumber;
	}

	public void setQuNumber(Long quNumber) {
		this.quNumber = quNumber;
	}

	public StudentEntity getStudentEntity() {
		return studentEntity;
	}

	public void setStudentEntity(StudentEntity studentEntity) {
		this.studentEntity = studentEntity;
	}

	public InstructorEntity getInstructorEntity() {
		return instructorEntity;
	}

	public void setInstructorEntity(InstructorEntity instructorEntity) {
		this.instructorEntity = instructorEntity;
	}

	public List<GroupsEntity> getGroupsEntities() {
		return groupsEntities;
	}

	public void setGroupsEntities(List<GroupsEntity> groupsEntities) {
		this.groupsEntities = groupsEntities;
	}

	public List<FilesEntity> getContentEntity() {
		return contentEntity;
	}

	public void setContentEntity(List<FilesEntity> contentEntity) {
		this.contentEntity = contentEntity;
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
		result = prime * result + ((attendanceEntity == null) ? 0 : attendanceEntity.hashCode());
		result = prime * result + ((contentEntity == null) ? 0 : contentEntity.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((groupsEntities == null) ? 0 : groupsEntities.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((instructorEntity == null) ? 0 : instructorEntity.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((quNumber == null) ? 0 : quNumber.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((studentEntity == null) ? 0 : studentEntity.hashCode());
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
		UserEntity other = (UserEntity) obj;
		if (attendanceEntity == null) {
			if (other.attendanceEntity != null)
				return false;
		} else if (!attendanceEntity.equals(other.attendanceEntity))
			return false;
		if (contentEntity == null) {
			if (other.contentEntity != null)
				return false;
		} else if (!contentEntity.equals(other.contentEntity))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (groupsEntities == null) {
			if (other.groupsEntities != null)
				return false;
		} else if (!groupsEntities.equals(other.groupsEntities))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (instructorEntity == null) {
			if (other.instructorEntity != null)
				return false;
		} else if (!instructorEntity.equals(other.instructorEntity))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (quNumber == null) {
			if (other.quNumber != null)
				return false;
		} else if (!quNumber.equals(other.quNumber))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (studentEntity == null) {
			if (other.studentEntity != null)
				return false;
		} else if (!studentEntity.equals(other.studentEntity))
			return false;
		return true;
	}

}
