package com.onlinexam.ExamOnlineDemo.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinexam.ExamOnlineDemo.model.Role;
import com.onlinexam.ExamOnlineDemo.model.User;
import com.onlinexam.ExamOnlineDemo.model.UserRole;
import com.onlinexam.ExamOnlineDemo.service.Userservices;import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
@Autowired
Userservices userservice;
	
	@PostMapping("/send")
	public User createUser(@RequestBody User user) {
	Set<UserRole>roles=new HashSet<>();
		Role role=new Role();
		role.setRoleId(12);
		role.setRoleName("NORAML");
		UserRole userRole=new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		roles.add(userRole);
		
		return this.userservice.createUser(user, roles);
		
		
	}
	@GetMapping("/{username}")
	public User getUser(@PathVariable String username) {
	return this.userservice.getUser(username);
}
	@DeleteMapping("/{id}")
public String deletebyId(@PathVariable int id) {
		this.userservice.deleteUser(id);
		return "data delete successfully";
		
	}
	@PutMapping("/{id}")
	public User updateUser(@RequestBody User user3,@PathVariable int id ) {
User user4=	userservice.updateUse(user3, id);
		
		return user4;
		
		
	}
}
