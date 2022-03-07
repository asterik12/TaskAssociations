package com.TaskAssociationsBackend.TaskAssociationsBackend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.TaskAssociationsBackend.TaskAssociationsBackend.Models.Task;
import com.TaskAssociationsBackend.TaskAssociationsBackend.Repository.UserRepository;
import com.TaskAssociationsBackend.TaskAssociationsBackend.Security.services.UserDetailsServiceImpl;
import com.TaskAssociationsBackend.TaskAssociationsBackend.payload.CreateTask;
import com.TaskAssociationsBackend.TaskAssociationsBackend.services.TaskService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/task")
public class TaskController {

	@Autowired
	public TaskService taskService ;
	
	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public UserDetailsServiceImpl userDetailsServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET, value = "/createTask")
	
	public String TaskForm() {
		return "createTask";
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/createTask/submit")

	public void addTask(@RequestBody CreateTask task) {
		this.taskService.addTask(task);
	}
	
}
