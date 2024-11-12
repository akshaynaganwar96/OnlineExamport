package com.onlinexam.ExamOnlineDemo;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.onlinexam.ExamOnlineDemo.model.Role;
import com.onlinexam.ExamOnlineDemo.model.User;
import com.onlinexam.ExamOnlineDemo.model.UserRole;
import com.onlinexam.ExamOnlineDemo.service.Userservices;

@SpringBootApplication
public class ExamOnlineDemoApplication implements CommandLineRunner{
@Autowired
	private Userservices useservice;
	
	
	public static void main(String[] args) {
		SpringApplication.run(ExamOnlineDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Starting code....");
		
		User user=new User();
		user.setFirstName("akshay");
		user.setLastName("nagawar");
		user.setUsername("akshay@123");
		user.setPassword("abc");
		user.setEmail("akshaynaganwar@gmail.com");
		user.setProfile("default.png");
		user.setPhono("98754345");
		
		Role role1=new Role();
	//	role1.setRoleId(44);
		role1.setRoleName("ADMIN");
		
		Set<UserRole>userRoleSet=new HashSet<>();
		UserRole userRole=new UserRole();
		userRole.setRole(role1);
		userRole.setUser(user);
		userRoleSet.add(userRole);
	User user1=	this.useservice.createUser(user, userRoleSet);
	System.out.println(user1);
	}

}
