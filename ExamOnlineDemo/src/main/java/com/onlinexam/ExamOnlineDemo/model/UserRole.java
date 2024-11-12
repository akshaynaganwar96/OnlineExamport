package com.onlinexam.ExamOnlineDemo.model;



import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.Setter;

@Entity

@AllArgsConstructor
@Setter 
@Getter
public class UserRole {

@Id
  @GeneratedValue(strategy = GenerationType.AUTO)
	private int userRoleId;


@ManyToOne(fetch = FetchType.EAGER)
private User user;
@ManyToOne
	private Role role;
public UserRole() {
	// TODO Auto-generated constructor stub
}

}
