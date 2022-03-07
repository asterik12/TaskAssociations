package com.TaskAssociationsBackend.TaskAssociationsBackend.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/testUser")
public class UserController {

	
	@RequestMapping(method = RequestMethod.GET, value = "/user")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('MODERATOR')")
	public String userAccess() {
		return "DashBoard";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/moderator")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator";
	}
}
