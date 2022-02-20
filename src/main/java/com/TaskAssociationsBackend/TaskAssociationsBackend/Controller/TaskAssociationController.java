package com.TaskAssociationsBackend.TaskAssociationsBackend.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TaskAssociationController {

	
	@RequestMapping(method = RequestMethod.GET, value ="/all") 
	public String allAccess() {
		return "Public Content";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/user")
	@PreAuthorize("hasRole('USER' or hasRole('MODERATOR') or hasRole('ADMIN'))")
	public String userAccess() {
		return "User Content";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board";
	}
}
