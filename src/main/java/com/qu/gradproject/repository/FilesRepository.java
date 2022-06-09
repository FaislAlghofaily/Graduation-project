package com.qu.gradproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qu.gradproject.entity.FilesEntity;
import com.qu.gradproject.entity.GroupsEntity;
import com.qu.gradproject.entity.HomeworkEntity;
import com.qu.gradproject.entity.UserEntity;

@Repository
public interface FilesRepository extends CrudRepository<FilesEntity,Long> {
	
	@Query("from FilesEntity u where u.groupsEntity= :groupsEntity and u.homeworkEntity =null")
	List<FilesEntity> findAllContnetBygroupsEntity( @Param("groupsEntity") GroupsEntity groupsEntity);
	
	@Query("from FilesEntity u where u.homeworkEntity= :homeworkEntity and u.userEntity =:userEntity")
	FilesEntity checkIfSubmitted(@Param("homeworkEntity") HomeworkEntity homeworkEntity,@Param("userEntity") UserEntity userEntity);
	
	List<FilesEntity> findAllByhomeworkEntity(HomeworkEntity homeworkEntity);
}
