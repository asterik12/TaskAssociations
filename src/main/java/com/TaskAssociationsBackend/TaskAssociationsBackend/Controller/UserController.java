package com.TaskAssociationsBackend.TaskAssociationsBackend.Controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.TaskAssociationsBackend.TaskAssociationsBackend.Models.Like;
import com.TaskAssociationsBackend.TaskAssociationsBackend.Models.Task;
import com.TaskAssociationsBackend.TaskAssociationsBackend.Models.User;
import com.TaskAssociationsBackend.TaskAssociationsBackend.Repository.TaskRepository;
import com.TaskAssociationsBackend.TaskAssociationsBackend.Repository.UserRepository;
import com.TaskAssociationsBackend.TaskAssociationsBackend.services.TaskService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/testUser")
public class UserController {

	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public TaskRepository taskRepository;
	
	@Autowired
	public TaskService taskService; 

	
	
	@RequestMapping(method = RequestMethod.GET, value = "/user")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('MODERATOR')")
	public List<Task> userAccess() {
		return taskService.getUserTask();
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Task> adminAccess() {
		return taskService.getAdminTask();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/moderator")
	@PreAuthorize("hasRole('MODERATOR')")
	public List<Task> moderatorAccess() {
		return taskService.getModeratorTask();
	}
}
