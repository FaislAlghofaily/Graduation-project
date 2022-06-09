package com.qu.gradproject.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.qu.gradproject.entity.GroupsEntity;
import com.qu.gradproject.entity.UserEntity;
import com.qu.gradproject.repository.GroupsRepository;
import com.qu.gradproject.repository.UserRepository;
import com.qu.gradproject.service.FilesService;
import com.qu.gradproject.service.GroupsService;
import com.qu.gradproject.service.UserService;

@Controller
@RequestMapping(value="/instructor")
public class InstructorController {
	
	
	
	
}