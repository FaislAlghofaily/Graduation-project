package com.qu.gradproject.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qu.gradproject.entity.GroupsEntity;
import com.qu.gradproject.entity.UserEntity;



@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long> {
	UserEntity findByEmail(String email);
	
	List<UserEntity> findByGroupsEntities(GroupsEntity groupsEntity);
	
	@Query("from UserEntity u where u.role='Student' and u.groupsEntities= :groupsEntities")
	List<UserEntity> findStudentsByGroupsEntities(@Param("groupsEntities") List<GroupsEntity> groupsEntities);
}
