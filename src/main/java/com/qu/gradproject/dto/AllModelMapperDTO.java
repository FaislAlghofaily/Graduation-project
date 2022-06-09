package com.qu.gradproject.dto;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.qu.gradproject.entity.AnnouncementEntity;
import com.qu.gradproject.entity.HomeworkEntity;
import com.qu.gradproject.entity.UserEntity;

;

@Component
public class AllModelMapperDTO {

	public UserEntity fromDTOtoEntityUserEntity(UserDTO userDto) {

		UserEntity userEntity = new UserEntity();
		
		BeanUtils.copyProperties(userDto, userEntity);
		
		
		return userEntity;

	}
	public UserDTO fromEntitytoDTOUserEntity(UserEntity userEntity) {

		UserDTO userDto = new UserDTO();
		BeanUtils.copyProperties(userEntity, userDto);

		return userDto;

	}
	
	public HomeworkEntity fromDTOtoEntityHomeworkEntity(HomeworkDTO homeworkDTO) {

		HomeworkEntity homeworkEntity = new HomeworkEntity();
		
		BeanUtils.copyProperties(homeworkDTO, homeworkEntity);
		
		
		return homeworkEntity;

	}
	public HomeworkDTO fromEntitytoDTOHomeworkEntity(HomeworkEntity homeworkEntity) {

		HomeworkDTO homeworkDTO = new HomeworkDTO();
		BeanUtils.copyProperties(homeworkEntity, homeworkDTO);

		return homeworkDTO;

	}
	
	public AnnouncementEntity fromDTOtoEntityAnnouncementEntity(AnnouncementDTO announcementDTO) {

		AnnouncementEntity announcementEntity = new AnnouncementEntity();
		
		BeanUtils.copyProperties(announcementDTO, announcementEntity);
		
		
		return announcementEntity;

	}
	public AnnouncementDTO fromEntitytoDTOAnnouncementEntity(AnnouncementEntity announcementEntity) {

		AnnouncementDTO announcementDTO = new AnnouncementDTO();
		BeanUtils.copyProperties(announcementEntity, announcementDTO);

		return announcementDTO;

	}
	
}
