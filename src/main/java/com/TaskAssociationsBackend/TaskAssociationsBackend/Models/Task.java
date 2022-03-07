package com.TaskAssociationsBackend.TaskAssociationsBackend.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private long userId;
	private String taskName;
	private String taskDescription;
	public Task(String taskName, String taskDescription) {
		this.taskName = taskName;
		this.taskDescription = taskDescription;
	}
	public Task() {
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	@Override
	public String toString() {
		return "Task [id=" + id + ", userId=" + userId + ", taskName=" + taskName + ", taskDescription="
				+ taskDescription + "]";
	}
	
	
	
}
