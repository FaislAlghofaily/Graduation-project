package com.qu.gradproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qu.gradproject.dto.UserDTO;
import com.qu.gradproject.service.UserService;





@Controller
@RequestMapping(value="/admin")
public class AdminController {
	
	@Autowired
	UserService userService;
	
	@GetMapping(value = "index")
	public String index() {
		return "adminindex";
	}
	@GetMapping(value = "/dashboard")
	public String adminDashboard(Model model) {	
		
		
		model.addAttribute("users", new UserDTO());
		return "admindashboard";	
	}
	@PostMapping(value = "/createuser")
	public String createUser(@ModelAttribute("users") UserDTO userDTO,Model model) {
		userService.createUser(userDTO);
		return "admindashboard";
	}
	
	
	@GetMapping(value = "/newsubject")
	public String newSubject(Model model) {
		return "newsubject";
	}
	
	
	
}
