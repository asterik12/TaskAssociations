package com.TaskAssociationsBackend.TaskAssociationsBackend.Controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.TaskAssociationsBackend.TaskAssociationsBackend.Models.Comment;
import com.TaskAssociationsBackend.TaskAssociationsBackend.Models.Follow;
import com.TaskAssociationsBackend.TaskAssociationsBackend.Models.Like;
import com.TaskAssociationsBackend.TaskAssociationsBackend.Models.Task;
import com.TaskAssociationsBackend.TaskAssociationsBackend.Models.User;
import com.TaskAssociationsBackend.TaskAssociationsBackend.Repository.UserRepository;
import com.TaskAssociationsBackend.TaskAssociationsBackend.services.TaskService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/task")
public class TaskController {

	@Autowired
	public TaskService taskService ;
	
	@Autowired
	public UserRepository userRepository;
	
	public Long CurrentLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String CurrentUser = authentication.getName();
		Long CurrentUserId;
		Optional<User> user = userRepository.findByUsername(CurrentUser);
		CurrentUserId = user.get().getId();
		System.out.println("ID"+ CurrentUserId);
		return CurrentUserId;
	}

	// Create Page API
	@RequestMapping(method = RequestMethod.GET, value = "/createTask")
	public String TaskForm() {
		return "Task";
	}
	
	// Create Task API
	@RequestMapping(method = RequestMethod.POST, value = "/createTask/submit")
	public void addTask(@RequestBody Task task) {
		this.taskService.addTask(task);
	}
	
	// get Task by ID API
	@RequestMapping(method = RequestMethod.GET, value = "/edit/{id}")
	public Task getSingleTask(@PathVariable Long id) {
		return this.taskService.getSingleTask(id);
	}
	
	// Edit Task API
	@RequestMapping(method = RequestMethod.PUT, value = "/edit/{id}")
	public void updateTask(@PathVariable Long id, @RequestBody Task task) {
		this.taskService.updateTask(id, task);
	}
	
	// Delete Task API
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
	public void deleteTask(@PathVariable Long id) {
		this.taskService.deleteTask(id);
	}
	
	// get Task by ID API
	@RequestMapping(method = RequestMethod.GET, value = "/view/task/{id}")
	public Task viewTask(@PathVariable Long id) {
		return this.taskService.viewTask(id);
	}
	
	// Add Comment
	@RequestMapping(method = RequestMethod.PUT, value = "/view/add/comment")
	public void addComment(@RequestBody Comment comment) {
		this.taskService.addComment(comment);
	}	
	
	// Delete Comment
	@RequestMapping(method = RequestMethod.DELETE, value = "/view/task/{id}/delete")
	public void removeComment(@PathVariable Long id,@RequestBody Comment comment) {
		this.taskService.removeComment(id, comment);
	}	
	
	// Show Comments
	@RequestMapping(method = RequestMethod.GET, value = "/view/task/AllCommentsData")
	public List<Comment> showComments() {
		return this.taskService.showComments();
	}
	// Add Like
	@RequestMapping(method = RequestMethod.PUT, value = "/edit/{id}/like")
	public void addLike(@PathVariable Long id, @RequestBody Like like) {
		this.taskService.addLike(id, like);
	}
	
	// Remove Like
	@RequestMapping(method = RequestMethod.DELETE, value = "/edit/{id}/unlike")
	public void removeLike(@PathVariable Long id, @RequestBody Like like) {
		this.taskService.removeLike(id, like);
	}
	
	// follow task
	@RequestMapping(method = RequestMethod.PUT, value = "/edit/{id}/follow")
	public void addFollow(@PathVariable Long id, @RequestBody Follow follow) {
		this.taskService.addFollow(id, follow);
	}
		
	// Unfollow task
	@RequestMapping(method = RequestMethod.DELETE, value = "/edit/{id}/unfollow")
	public void removeFollow(@PathVariable Long id, @RequestBody Follow follow) {
		this.taskService.removeFollow(id, follow);
	}
	@RequestMapping(method = RequestMethod.GET, value = "/AllFollowUserData")
	public Set<User> followUserData() {
		return taskService.followUserData();
	}
	@RequestMapping(method = RequestMethod.GET, value = "/AllFollowData")
	public List<Follow> followData() {
		return taskService.followData();
	}
	
	// view task
	@RequestMapping(method = RequestMethod.GET, value = "/view")
	public void view() {
		this.taskService.view();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/AllLikesData")
	public List<Like> likesData() {
		return taskService.likesData();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/AllLikesUserData")
	public Set<User> likesUserData() {
		return taskService.likesUserData();
	}
	
	
}
