package com.qu.gradproject.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.qu.gradproject.dto.AllModelMapperDTO;
import com.qu.gradproject.dto.HomeworkDTO;
import com.qu.gradproject.entity.FilesEntity;
import com.qu.gradproject.entity.GroupsEntity;
import com.qu.gradproject.entity.HomeworkEntity;
import com.qu.gradproject.entity.UserEntity;
import com.qu.gradproject.repository.HomeworkRepository;

@Service
public class HomeworkService {

	@Autowired
	AllModelMapperDTO allModelMappersService;
	@Autowired
	GroupsService groupsService;
	@Autowired
	HomeworkRepository homeworkRepository;
	
	@Lazy
	@Autowired
	FilesService filesService;
	
	public void createNewHomework(HomeworkDTO homeworkDTO,Long groupID) {
		HomeworkEntity homeworkEntity=allModelMappersService.fromDTOtoEntityHomeworkEntity(homeworkDTO);
		homeworkEntity.setGroupsEntity(groupsService.getGroupEntityById(groupID));
		homeworkEntity.setStatus("ongoing");
		homeworkEntity.setId(null);
		homeworkRepository.save(homeworkEntity);			
	}
	public List<HomeworkEntity> getAllAvailableHomeworksByGroup(Long id,UserEntity userEntity){
		
		List<HomeworkEntity> homeworkEntity=new ArrayList<>();
		GroupsEntity groupsEntity= groupsService.getGroupEntityById(id);
		
		if(userEntity.getRole().contains("Student")) {
		
		homeworkEntity=homeworkRepository.findAllAvailableHomeworks(groupsEntity);
		return homeworkEntity;}
		
		homeworkEntity=homeworkRepository.findAllBygroupsEntity(groupsEntity);
		
		return homeworkEntity;
		
	}
	public HomeworkEntity getHomeworkById(Long id) {
		Optional<HomeworkEntity>optional=homeworkRepository.findById(id);
		HomeworkEntity homeworkEntity=optional.orElseThrow(RuntimeException::new);
		return homeworkEntity;
	
	}
	
	public String deleteHomeworkById(Long id) {
		HomeworkEntity homeworkEntity=getHomeworkById(id);

		for(FilesEntity files : filesService.getAllFilesByHomeworkId(id)) {
			filesService.deleteContentById(files.getId());
		}
		Long groupid= homeworkEntity.getGroupsEntity().getId();
		homeworkRepository.delete(homeworkEntity);
		return groupid.toString();
	}
	
	public void checkDeadLines(Date date) {
		
		homeworkRepository.changeStatus("finished",date );
	}


	
}

