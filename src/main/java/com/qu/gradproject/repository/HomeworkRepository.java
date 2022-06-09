package com.qu.gradproject.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qu.gradproject.entity.GroupsEntity;
import com.qu.gradproject.entity.HomeworkEntity;

@Repository
public interface HomeworkRepository extends CrudRepository<HomeworkEntity, Long>{
	List<HomeworkEntity> findAllBygroupsEntity(GroupsEntity groupsEntity);
	
	@Transactional
	@Modifying
	@Query("UPDATE HomeworkEntity u SET u.status = :status WHERE DATE(:localdate) > (u.deadline)")
	void changeStatus(@Param("status") String status, @Param("localdate") Date localdate);
	
	@Query(value = "from HomeworkEntity u WHERE u.status = 'ongoing' and u.groupsEntity=:groupsEntity ")
	List <HomeworkEntity> findAllAvailableHomeworks(@Param("groupsEntity") GroupsEntity groupsEntity);
	
}
