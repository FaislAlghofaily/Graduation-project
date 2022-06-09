package com.qu.gradproject.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.qu.gradproject.entity.AnnouncementEntity;
import com.qu.gradproject.entity.GroupsEntity;

@Repository
public interface AnnouncementRepository extends CrudRepository<AnnouncementEntity, Long> {

	List<AnnouncementEntity> findAllBygroupsEntity(GroupsEntity groupsEntity);
	
}
