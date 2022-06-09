package com.qu.gradproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qu.gradproject.dto.AllModelMapperDTO;
import com.qu.gradproject.dto.AnnouncementDTO;
import com.qu.gradproject.entity.AnnouncementEntity;
import com.qu.gradproject.entity.GroupsEntity;
import com.qu.gradproject.repository.AnnouncementRepository;

@Service
public class AnnouncementService {
	@Autowired
	AnnouncementRepository announcementRepository;
	@Autowired
	AllModelMapperDTO allModelMappersService;

	@Autowired
	GroupsService groupsService;
	
	public List<AnnouncementEntity> getAllAnnouncementByGroupId(Long groupID){
	GroupsEntity groupsEntity=groupsService.getGroupEntityById(groupID);
	return announcementRepository.findAllBygroupsEntity(groupsEntity);
	}
	
	public void createNewAnnouncement(AnnouncementDTO announcementDTO,Long groupID) {
	AnnouncementEntity announcementEntity=allModelMappersService.fromDTOtoEntityAnnouncementEntity(announcementDTO);
	GroupsEntity groupsEntity=groupsService.getGroupEntityById(groupID);
	announcementEntity.setGroupsEntity(groupsEntity);
	announcementRepository.save(announcementEntity);
	}
	
	
	
}
