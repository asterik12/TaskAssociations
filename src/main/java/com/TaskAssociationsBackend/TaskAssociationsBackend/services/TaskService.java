package com.TaskAssociationsBackend.TaskAssociationsBackend.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.TaskAssociationsBackend.TaskAssociationsBackend.Models.Task;
import com.TaskAssociationsBackend.TaskAssociationsBackend.Repository.TaskRepository;
import com.TaskAssociationsBackend.TaskAssociationsBackend.Security.services.UserDetailsServiceImpl;
import com.TaskAssociationsBackend.TaskAssociationsBackend.payload.CreateTask;


@Service
public class TaskService {

	@Autowired
	public TaskRepository taskRepository;
	
	@Autowired
	public UserDetailsServiceImpl userDetailsServiceImpl;
	
	public void addTask(CreateTask task) {
		
		Task taskData = new Task(task.getTaskName(),task.getTaskDescription());
//		
//		Long CurrentUserId = userDetailsServiceImpl.CurrentLoggedInUser();
//		System.out.println("ID"+ CurrentUserId);
//		taskData.setUserId(CurrentUserId);
		
		taskRepository.save(taskData);
	}
}
