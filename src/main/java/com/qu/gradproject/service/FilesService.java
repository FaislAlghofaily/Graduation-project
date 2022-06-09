package com.qu.gradproject.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.qu.gradproject.entity.FilesEntity;
import com.qu.gradproject.entity.GroupsEntity;
import com.qu.gradproject.entity.HomeworkEntity;
import com.qu.gradproject.entity.UserEntity;
import com.qu.gradproject.repository.FilesRepository;
import org.apache.commons.io.FilenameUtils;

@Service
public class FilesService {

	@Value("${directory}")
    private String directory;
	@Autowired
	GroupsService groupsService;
	@Autowired
	FilesRepository filesRepository;
	
	@Autowired
	HomeworkService homeworkService;
	
	public void saveFile(MultipartFile multipartFile,Long id,String type,UserEntity userEntity) {
		
		FilesEntity filesEntity=new FilesEntity();
		GroupsEntity groupsEntity= groupsService.getGroupEntityById(id);
		
		String UPLOAD_DIR = directory +groupsEntity.getGroupNumber()+"\\";
		filesEntity.setUploadType(type);
		File directory = new File(UPLOAD_DIR);
	    if (! directory.exists()){
	        directory.mkdir();
	        
	    }
	    File directoryContent = new File(UPLOAD_DIR+filesEntity.getUploadType()+"\\");
        if (! directoryContent.exists()){
        	directoryContent.mkdir();
	        
	    }
		
		
		String fileName =StringUtils.cleanPath(multipartFile.getOriginalFilename());
		
		try {
			Path path = Paths.get(UPLOAD_DIR+filesEntity.getUploadType()+"\\" + fileName);
			Files.copy(multipartFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		filesEntity.setFileName(fileName);
		filesEntity.setGroupsEntity(groupsEntity);
		filesEntity.setUserEntity(userEntity);
		filesRepository.save(filesEntity);
		
	}

public void saveHomeworkFile(MultipartFile multipartFile,Long id,String type,UserEntity userEntity,Long homeworkId) {
		HomeworkEntity homeworkEntity=homeworkService.getHomeworkById(homeworkId);
		FilesEntity filesEntity=new FilesEntity();
		GroupsEntity groupsEntity= groupsService.getGroupEntityById(id);
		
		String UPLOAD_DIR = directory +groupsEntity.getGroupNumber()+"\\";
		filesEntity.setUploadType(type);
		File directory = new File(UPLOAD_DIR);
	    if (! directory.exists()){
	        directory.mkdir();
	        
	    }
	    File directoryContent = new File(UPLOAD_DIR+filesEntity.getUploadType()+"\\");
        if (! directoryContent.exists()){
        	directoryContent.mkdir();
	        
	    }
		
		
        String newFileName=homeworkId+" "+userEntity.getName()+"."+FilenameUtils.getExtension(StringUtils.cleanPath(multipartFile.getOriginalFilename()));
        
        
		
		
		try {
			Path path = Paths.get(UPLOAD_DIR+filesEntity.getUploadType()+"\\" + newFileName);
			Files.copy(multipartFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		filesEntity.setFileName(newFileName);
		filesEntity.setGroupsEntity(groupsEntity);
		filesEntity.setUserEntity(userEntity);
		filesEntity.setHomeworkEntity(homeworkEntity);
		filesRepository.save(filesEntity);
		
	}
	
	
	public List<FilesEntity> getContentFilesByGroup(Long id) {
		List<FilesEntity> filesEntity=new ArrayList<>();
		GroupsEntity groupsEntity= groupsService.getGroupEntityById(id);
		filesEntity=filesRepository.findAllContnetBygroupsEntity(groupsEntity);
		return filesEntity;
	}
	
	public FilesEntity findFileById(Long id) {
		Optional<FilesEntity>optional=filesRepository.findById(id);
		FilesEntity filesEntity=optional.orElseThrow(RuntimeException::new);
		return filesEntity;
		
	}
	
	public String deleteContentById(Long id) {
		FilesEntity filesEntity=findFileById(id);
		try {
		File f=new File(directory+filesEntity.getGroupsEntity().getGroupNumber()+"\\"+filesEntity.getUploadType()+"\\"+filesEntity.getFileName());
		f.delete();
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		filesRepository.delete(filesEntity);
		return filesEntity.getGroupsEntity().getId().toString();
	}
	
	public List<FilesEntity> getAllFilesByHomeworkId(Long id){
		HomeworkEntity homeworkEntity=homeworkService.getHomeworkById(id);
		return filesRepository.findAllByhomeworkEntity(homeworkEntity);
	}
	
	public boolean checkIfSubmitted(HomeworkEntity homeworkEntity,UserEntity userEntity) {
		if(filesRepository.checkIfSubmitted(homeworkEntity, userEntity)!=null) {
			return true;
			
		}else return false;
	}
	
}
