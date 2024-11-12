package com.onlinexam.ExamOnlineDemo.model;

import java.util.HashSet;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.Setter;

@Entity

@AllArgsConstructor
@Getter@Setter
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private int roleId;
private String roleName;
@OneToMany(cascade = CascadeType.ALL,fetch =FetchType.LAZY,mappedBy = "role" )
Set<UserRole>userRoles =new HashSet<>();


public Role() {
	// TODO Auto-generated constructor stub
}

}
