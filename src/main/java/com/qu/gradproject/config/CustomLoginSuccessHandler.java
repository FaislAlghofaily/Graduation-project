package com.qu.gradproject.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;



@Configuration
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		String targetURL = determineTargetURL(authentication);

		if (response.isCommitted()) {
			return;
		}
		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		redirectStrategy.sendRedirect(request, response, targetURL);
	}

	protected String determineTargetURL(Authentication authentication) {
		String url = "/login?error=true";
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		if (authorities.contains(new SimpleGrantedAuthority("Admin"))) {
			url = "/admin/index";
		} else if (authorities.contains(new SimpleGrantedAuthority("Instructor"))) {
			url = "/subjects/dashboard";
		} else if (authorities.contains(new SimpleGrantedAuthority("Student"))) {
			url = "/subjects/dashboard";}
		
	

		return url;
	}

}
