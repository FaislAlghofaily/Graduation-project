package com.qu.gradproject.service;

import java.util.List;

import com.qu.gradproject.entity.GroupsEntity;
import com.qu.gradproject.entity.UserEntity;

public interface GroupsService {

	GroupsEntity getGroupEntityById(Long id);
	List<GroupsEntity> getGroupEntityByUserEntity(UserEntity userEntity);
}
