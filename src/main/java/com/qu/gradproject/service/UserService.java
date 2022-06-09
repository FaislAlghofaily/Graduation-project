package com.qu.gradproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.qu.gradproject.dto.AllModelMapperDTO;
import com.qu.gradproject.dto.UserDTO;
import com.qu.gradproject.entity.GroupsEntity;
import com.qu.gradproject.entity.InstructorEntity;
import com.qu.gradproject.entity.StudentEntity;
import com.qu.gradproject.entity.UserEntity;
import com.qu.gradproject.repository.GroupsRepository;
import com.qu.gradproject.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	AllModelMapperDTO allModelMappersService;

	@Autowired
	GroupsServiceImp groupsService;
	@Autowired
	UserRepository usersRepository;

	@Autowired
	GroupsRepository groupsRepository;

	public void createUser(UserDTO userDTO) {

		// coping data from DTO to Entity
		UserEntity userEntity = allModelMappersService.fromDTOtoEntityUserEntity(userDTO);

		// Encryripting passowrd
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(userEntity.getPassword());
		userEntity.setPassword(encodedPassword);

		// cheacking if the the new user is a student or not
		if (userDTO.getRole().contains("Student")) {
			StudentEntity studentEntity = new StudentEntity();
			studentEntity.setUserEntity(userEntity);
			userEntity.setStudentEntity(studentEntity);
		} else if (userDTO.getRole().contains("Instructor")) {
			InstructorEntity instructorEntity = new InstructorEntity();
			instructorEntity.setUserEntity(userEntity);
			userEntity.setInstructorEntity(instructorEntity);

		}

		usersRepository.save(userEntity);

	}

	public UserEntity getUserEntityById(Long id) {
		Optional<UserEntity> optional = usersRepository.findById(id);
		UserEntity userEntity = optional.orElseThrow(RuntimeException::new);
		return userEntity;

	}

	public UserEntity getUserEntityByEmail(String email) {
		UserEntity userEntity = usersRepository.findByEmail(email);
		return userEntity;

	}

	public List<UserEntity> getStudentsByGroupId(Long id) {
		
		GroupsEntity groupsEntity = groupsService.getGroupEntityById(id);
		List<UserEntity>students=usersRepository.findByGroupsEntities(groupsEntity);
		int index=0;
		for(UserEntity userEntity:students) {
			if(userEntity.getRole().contains("Instructor")) {
			index=	students.indexOf(userEntity);
				
			}
		}
		students.remove(index);
		return students;

	}

	public void test() {

//		List <UserEntity> userEntities=new ArrayList<>();

//		GroupsEntity groupsEntity = new GroupsEntity();
//		groupsEntity.setClassName("IT203");
//		groupsEntity.setClassroom("R-304");
//		groupsEntity.setGroupNumber((long) 24123);
//		groupsEntity.setLectureTime("1pm");

//		userEntities.add(userEntity);
//		groupsEntity.setUserEntities(userEntities);	

//		groupsRepository.save(groupsEntity);
//		UserDTO userDTO=new UserDTO();
//		userDTO.setEmail("hudwan@gmail.com");
//		userDTO.setName("hudwan");
//		userDTO.setPassword("123456");
//		userDTO.setQuNumber(1103L);
//		userDTO.setRole("Instructor");
//		
//		
//		createUser(userDTO);

	}

}
