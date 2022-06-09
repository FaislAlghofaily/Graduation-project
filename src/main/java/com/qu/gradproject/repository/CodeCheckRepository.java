package com.qu.gradproject.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qu.gradproject.entity.CodeCheckEntity;
import com.qu.gradproject.entity.GroupsEntity;

@Repository
public interface CodeCheckRepository extends CrudRepository<CodeCheckEntity,Long> {
	CodeCheckEntity findByAttendanceCode(String Code);

	@Query(value = "from CodeCheckEntity u where u.groupsEntity = :groupsEntity and u.dateOfLecture = :dateOfLecture ")
	CodeCheckEntity findCurrentAttendanceCode(@Param("groupsEntity") GroupsEntity groupsEntity,@Param("dateOfLecture") LocalDate dateOfLecture);
}
