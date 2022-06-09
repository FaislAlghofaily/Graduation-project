package com.qu.gradproject.controller;

import java.security.Principal;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController  {

    @RequestMapping("/error")
    public String handleError(Principal principal) {
    	
    	if(principal==null) {
    		return "redirect:/login";
    	}
    	
        //do something like logging
        return "404page1";
    }
}
