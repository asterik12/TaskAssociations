package com.TaskAssociationsBackend.TaskAssociationsBackend.Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private long userId;
	
	private String taskName;
	
	private String taskDescription;
	
	private String sharedWith;
	
	private String status;
	
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern =  "yyy-MM-dd HH:mm:ss", timezone = "IST")
	@Column(name = "create_date")
	private Date createDate;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern =  "yyy-MM-dd HH:mm:ss", timezone = "IST")
	@Column(name = "updated_date")
	private Date updatedDate;
	
	

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
	public String getSharedWith() {
		return sharedWith;
	}
	public void setSharedWith(String sharedWith) {
		this.sharedWith = sharedWith;
	}
	
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Task [id=" + id + ", userId=" + userId + ", taskName=" + taskName + ", taskDescription="
				+ taskDescription + ", sharedWith=" + sharedWith + ", status=" + status + ", createDate=" + createDate
				+ ", updatedDate=" + updatedDate + "]";
	}
	
	
	
}
