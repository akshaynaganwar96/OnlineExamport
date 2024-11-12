package com.onlinexam.ExamOnlineDemo.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.Cascade;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Setter
@Getter
public class User implements UserDetails{ 

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

private int id;
private String username;
private String password;
private String firstName;
private String lastName;
private String email;
private String phono;
private String profile;

private boolean enable=true;
@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER ,mappedBy = "user")
@JsonIgnore
private Set<UserRole>userRoles=new HashSet<>();
public User() {
	// TODO Auto-generated constructor stub
}
@Override
public boolean isAccountNonExpired() {
	return true;
}

@Override
public boolean isAccountNonLocked() {
	return true;
}

@Override
public boolean isCredentialsNonExpired() {
	return true;
}


@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
	// TODO Auto-generated method stub
	Set<Authority> set=new HashSet<>();
	this.userRoles.forEach(userRole->{
		set.add(new Authority(userRole.getRole().getRoleName()));
		
	});
	
	return null;
}

}
