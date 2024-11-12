package com.onlinexam.ExamOnlineDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlinexam.ExamOnlineDemo.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
 public User findByUsername(String username);
}
