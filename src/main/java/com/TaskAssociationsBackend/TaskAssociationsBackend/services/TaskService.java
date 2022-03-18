package com.TaskAssociationsBackend.TaskAssociationsBackend.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.TaskAssociationsBackend.TaskAssociationsBackend.Models.Comment;
import com.TaskAssociationsBackend.TaskAssociationsBackend.Models.Follow;
import com.TaskAssociationsBackend.TaskAssociationsBackend.Models.Like;
import com.TaskAssociationsBackend.TaskAssociationsBackend.Models.Task;
import com.TaskAssociationsBackend.TaskAssociationsBackend.Models.User;
import com.TaskAssociationsBackend.TaskAssociationsBackend.Repository.CommentRepository;
import com.TaskAssociationsBackend.TaskAssociationsBackend.Repository.FollowRepository;
import com.TaskAssociationsBackend.TaskAssociationsBackend.Repository.LikeRepository;
import com.TaskAssociationsBackend.TaskAssociationsBackend.Repository.TaskRepository;
import com.TaskAssociationsBackend.TaskAssociationsBackend.Repository.UserRepository;
import com.TaskAssociationsBackend.TaskAssociationsBackend.Security.services.UserDetailsServiceImpl;


@Service
public class TaskService {

	@Autowired
	public TaskRepository taskRepository;
	
	@Autowired
	public UserRepository userRepository;
	@Autowired
	public LikeRepository likeRepository;
	@Autowired
	public FollowRepository followRepository; 
	
	@Autowired
	public CommentRepository commentRepository;
	@Autowired
	public UserDetailsServiceImpl userDetailsServiceImpl;

	public void addTask(Task task) {
		taskRepository.save(task);
	}
	
	public List<Task> getUserTask() {
		List<Task> userTask = new ArrayList<>(Arrays.asList());
		for(Task task : taskRepository.findAll() ) {
			if(task.getSharedWith().equals("users")) {
				if(userTask.contains(task)) {
					continue;
				}
				else {
					userTask.add(task);
				}
				
			}
		}
		return userTask.stream().distinct().collect(Collectors.toList());
		
	}
	
	public List<Task> getAdminTask() {
		List<Task> adminTask = new ArrayList<>(Arrays.asList());
		for(Task task : taskRepository.findAll() ) {
			if(task.getSharedWith().equals("admin")) {
				if(adminTask.contains(task)) {
					continue;
				}
				else {
					adminTask.add(task);
				}
				
			}
		}
		return adminTask.stream().distinct().collect(Collectors.toList());
	}
	public List<Task> getModeratorTask() {
		List<Task> moderatorTask = new ArrayList<>(Arrays.asList());
		for(Task task : taskRepository.findAll() ) {
			if(task.getSharedWith().equals("moderator")) {
				if(moderatorTask.contains(task)) {
					continue;
				}
				else {
					moderatorTask.add(task);
				}
				
			}
		}
		return moderatorTask.stream().distinct().collect(Collectors.toList());
		
	}
	public List<Task> getALLSharedTask() {
		List<Task> all = new ArrayList<>(Arrays.asList());
		for(Task task : taskRepository.findAll() ) {
			if(task.getSharedWith().equals("all")) {
				if(all.contains(task)) {
					continue;
				}
				else {
					all.add(task);
				}
				
			}
		}
		return all.stream().distinct().collect(Collectors.toList());
		
	}
	
	
	
	public Task getSingleTask(Long id) {
		Task task = null;
		for(Task t : taskRepository.findAll()) {
			if(t.getId() == id) {
				task = t;
				break;
			}
		}
		System.out.println(task);
		return task;
	}
	
	public void updateTask(Long id, Task task) {
		taskRepository.save(task);
	}
	
	public void deleteTask(Long id) {
		Task entity = taskRepository.getOne(id);
		taskRepository.delete(entity);
	}
	
	public Task viewTask(Long id) {
		Task task = null;
		for(Task t : taskRepository.findAll()) {
			if(t.getId() == id) {
				task = t;
				break;
			}
		}
		System.out.println(task);
		return task;
	}
	
	// Comment task Actions
	public void addComment(Comment comments) {
		commentRepository.save(comments);
	}
	
	public void removeComment(Long id, Comment comment) {
		long tempID = 0;
		for(Comment c: commentRepository.findAll()) {
			if(c.getTaskId() == id && c.getUserId() == comment.getUserId()) {
				tempID = c.getId();
			}
		}
		Comment entity = commentRepository.getOne(tempID);
		commentRepository.delete(entity);
	}
	
	public List<Comment> showComments() {
		List<Comment> commentData = new ArrayList<>(Arrays.asList());
		for(Comment c : commentRepository.findAll() ) {
			commentData.add(c);
		}
		return commentData;
	}
	
	public Set<User> CommentUserData() {
		Set<User> commentUserData = new HashSet<>();
		
		for(Comment comment : commentRepository.findAll() ) {
			for(User user: userRepository.findAll()) {
				if(comment.getUserId() == user.getId()) {
					commentUserData.add(user);
					break;
				}
			}
		}
		return commentUserData;
		
	}
	
	// Likes task Actions
	
	public void addLike(Long id, Like like) {
		List<Like> likeData = likeRepository.findAll();
		if(likeData.isEmpty()) {
			likeRepository.save(like);
		}
		else {
			for(Like l: likeRepository.findAll()) {
				if(l.getTaskId() == like.getTaskId() && l.getUserId() == like.getUserId()) {
					break;
				}
				else {
					likeRepository.save(like);
				}
			}
		}
		
		
	}
	
	public void removeLike(Long id, Like like) {
		long tempID = 0;
		for(Like l: likeRepository.findAll()) {
			if(l.getTaskId() == id && l.getUserId() == like.getUserId()) {
				tempID = l.getId();
			}
		}
		Like entity = likeRepository.getOne(tempID);
		likeRepository.delete(entity);
	}
	
	public void view() {
		
		List<User> user = userRepository.findAll();
		
		for(User i: user) {
			System.out.println("UserName : "+ i.getUsername());
		}
		System.out.println("User is : "+ user);
	}
	
	public List<Like> likesData() {
		List<Like> likesData = new ArrayList<>(Arrays.asList());
		for(Like like : likeRepository.findAll() ) {
			likesData.add(like);
		}
		return likesData;
		
	}
	
	public Set<User> likesUserData() {
		Set<User> likesUserData = new HashSet<>();
		
		for(Like like : likeRepository.findAll() ) {
			for(User user: userRepository.findAll()) {
				if(like.getUserId() == user.getId()) {
					likesUserData.add(user);
					break;
				}
			}
		}
		return likesUserData;
		
	}
	
	//follow task actions
	public void addFollow(Long id, Follow follow) {
		List<Follow> followData = followRepository.findAll();
		if(followData.isEmpty()) {
			followRepository.save(follow);
		}
		else {
			for(Follow f: followRepository.findAll()) {
				if(f.getTaskId() == follow.getTaskId() && f.getUserId() == follow.getUserId()) {
					break;
				}
				else {
					followRepository.save(follow);
				}
			}
		}
		
		
	}
	
	public void removeFollow(Long id, Follow follow) {
		long tempID = 0;
		for(Follow f: followRepository.findAll()) {
			if(f.getTaskId() == id && f.getUserId() == follow.getUserId()) {
				tempID = f.getFollowId();
			}
		}
		Follow entity = followRepository.getOne(tempID);
		followRepository.delete(entity);
	}
	
	public Set<User> followUserData() {
		Set<User> followUserData = new HashSet<>();
		
		for(Follow follow : followRepository.findAll() ) {
			for(User user: userRepository.findAll()) {
				if(follow.getUserId() == user.getId()) {
					followUserData.add(user);
					break;
				}
			}
		}
		return followUserData;
		
	}
	
	public List<Follow> followData() {
		List<Follow> followData = new ArrayList<>(Arrays.asList());
		for(Follow follow : followRepository.findAll() ) {
			followData.add(follow);
		}
		return followData;
	
	}
	
}
