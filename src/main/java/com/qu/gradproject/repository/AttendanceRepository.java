package com.qu.gradproject.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qu.gradproject.entity.AttendanceEntity;
import com.qu.gradproject.entity.GroupsEntity;
import com.qu.gradproject.entity.UserEntity;

@Repository
public interface AttendanceRepository extends CrudRepository<AttendanceEntity, Long> {
	@Transactional
	void deleteByUserEntity(UserEntity userEntity);

	@Transactional
	@Modifying
	@Query("delete from AttendanceEntity u WHERE u.userEntity= :userEntity and u.groupsEntity= :groupsEntity and u.dateOfLecture= :dateOfLecture")
	void deleteByUserEntityAndDate(@Param("userEntity") UserEntity userEntity,
			@Param("dateOfLecture") LocalDate dateOfLecture, @Param("groupsEntity") GroupsEntity groupsEntity);

	@Query("from AttendanceEntity u WHERE u.userEntity= :userEntity and u.groupsEntity= :groupsEntity and u.dateOfLecture= :dateOfLecture")
	AttendanceEntity checkIfAttendanceTaken(@Param("userEntity") UserEntity userEntity,
			@Param("groupsEntity") GroupsEntity groupsEntity, @Param("dateOfLecture") LocalDate dateOfLecture);
	
	@Query("SELECT DISTINCT dateOfLecture from AttendanceEntity u where u.groupsEntity= :groupsEntity")
	List<LocalDate> findAllDatesByGroups(@Param("groupsEntity") GroupsEntity groupsEntity);
	
	@Query("from AttendanceEntity u WHERE u.groupsEntity= :groupsEntity and u.dateOfLecture= :dateOfLecture")
	List <AttendanceEntity>getAllAbsentStudents(@Param("groupsEntity") GroupsEntity groupsEntity,@Param("dateOfLecture") LocalDate dateOfLecture);
}
