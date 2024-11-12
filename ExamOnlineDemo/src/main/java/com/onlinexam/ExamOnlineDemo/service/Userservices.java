package com.onlinexam.ExamOnlineDemo.service;

import java.util.Set;

import com.onlinexam.ExamOnlineDemo.model.User;
import com.onlinexam.ExamOnlineDemo.model.UserRole;

public interface Userservices {

	public User createUser( User user,Set<UserRole> userRoles);
public User getUser(String username);
public void deleteUser(int userId);
public User updateUse(User user,int id);
}
