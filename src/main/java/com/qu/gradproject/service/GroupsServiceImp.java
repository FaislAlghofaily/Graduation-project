package com.qu.gradproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qu.gradproject.entity.GroupsEntity;
import com.qu.gradproject.entity.UserEntity;
import com.qu.gradproject.repository.GroupsRepository;

@Service
public class GroupsServiceImp implements GroupsService{

	@Autowired
	GroupsRepository groupsRepository;
	
	@Override
	public GroupsEntity getGroupEntityById(Long id) {
		Optional<GroupsEntity> optional=groupsRepository.findById(id);
		GroupsEntity groupsEntity=optional.orElseThrow(RuntimeException::new);	
		return groupsEntity;
	}
	
	@Override
	public List<GroupsEntity> getGroupEntityByUserEntity(UserEntity userEntity) {
		List<GroupsEntity> groupsEntity=groupsRepository.findByUserEntities(userEntity);
		return groupsEntity;
		
	}

}
