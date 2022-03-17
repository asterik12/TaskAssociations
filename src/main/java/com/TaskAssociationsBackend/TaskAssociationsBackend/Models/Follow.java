package com.TaskAssociationsBackend.TaskAssociationsBackend.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Follow {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long followId;
	
	private long taskId;
	
	private long userId;

	public Follow() {
		
	}
	public Follow(long taskId, long userId) {
		super();
		this.taskId = taskId;
		this.userId = userId;
	}
	public long getFollowId() {
		return followId;
	}
	public void setFollowId(long followId) {
		this.followId = followId;
	}
	public long getTaskId() {
		return taskId;
	}
	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Follow [followId=" + followId + ", taskId=" + taskId + ", userId=" + userId + "]";
	}
	
	
	
}
