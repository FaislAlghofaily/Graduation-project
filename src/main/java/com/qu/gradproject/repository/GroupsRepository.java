package com.qu.gradproject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.qu.gradproject.entity.GroupsEntity;
import com.qu.gradproject.entity.UserEntity;

@Repository
public interface GroupsRepository extends CrudRepository<GroupsEntity, Long> {

	List<GroupsEntity> findByUserEntities(UserEntity userEntities);
	List<GroupsEntity> findByGroupNumber(Long groupNumber);
	Optional<GroupsEntity> findById(Long id);
	
}
