package com.TaskAssociationsBackend.TaskAssociationsBackend.Models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Task {

	@Id
	private long id;
	private long userId;
	private String taskName;
	private String taskDescription;
	public Task(long id, long userId, String taskName, String taskDescription) {
		super();
		this.id = id;
		this.userId = userId;
		this.taskName = taskName;
		this.taskDescription = taskDescription;
	}
	public Task() {
		super();
		// TODO Auto-generated constructor stub
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
