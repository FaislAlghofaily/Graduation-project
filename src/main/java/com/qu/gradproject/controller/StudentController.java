package com.qu.gradproject.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qu.gradproject.entity.UserEntity;
import com.qu.gradproject.repository.GroupsRepository;
import com.qu.gradproject.repository.UserRepository;
import com.qu.gradproject.service.GroupsService;
import com.qu.gradproject.service.UserService;

@Controller
@RequestMapping(value="/student")
public class StudentController {

	
}
