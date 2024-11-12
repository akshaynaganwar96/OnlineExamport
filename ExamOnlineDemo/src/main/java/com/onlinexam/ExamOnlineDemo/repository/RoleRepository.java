package com.onlinexam.ExamOnlineDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlinexam.ExamOnlineDemo.model.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

}
