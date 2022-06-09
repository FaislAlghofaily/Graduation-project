package com.qu.gradproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.qu.gradproject.service.UserService;

@Controller
public class MainController {

	@Autowired
	UserService userService;
  
    @GetMapping("/login")
    public String viewLoginPage() {
       
        return "login";
    }
	
	
	@GetMapping("/logout")
	public String logout() {
		
		
		return "login";
	}
}
