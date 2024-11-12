package com.onlinexam.ExamOnlineDemo.serviceimpl;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinexam.ExamOnlineDemo.model.User;
import com.onlinexam.ExamOnlineDemo.model.UserRole;
import com.onlinexam.ExamOnlineDemo.repository.RoleRepository;
import com.onlinexam.ExamOnlineDemo.repository.UserRepository;
import com.onlinexam.ExamOnlineDemo.service.Userservices;
@Service
public class UserSeviceImpl implements Userservices {
@Autowired
UserRepository userRepository;

@Autowired
RoleRepository roleRepository;

	@Override
	public User createUser(User user, Set<UserRole> userRoles){
		// TODO Auto-generated method stub
		
	User local=	this.userRepository.findByUsername(user.getUsername());
		if(local !=null) {
			
			System.out.println("user is already there!!");
			System.out.println("user already present");
		}else {
			for(UserRole ur:userRoles) {
				roleRepository.save(ur.getRole());
			}
			user.getUserRoles().addAll(userRoles);
			
			local=this.userRepository.save(user);
		}
		return local;
	}

	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return this.userRepository.findByUsername(username);
	}

	@Override
	public void deleteUser(int userId) {
		// TODO Auto-generated method stub
	
		this.userRepository.deleteById(userId);
	}

	@Override
	public User updateUse(User user, int id) {
		// TODO Auto-generated method stub
	Optional<User>updateUser=userRepository.findById(null);
	if(updateUser.isPresent()) {
		User user2=updateUser.get();
		user2.setUsername(user.getUsername());;
		user2.setFirstName(user.getFirstName());
		user2.setLastName(user.getLastName());;
		user2.setPhono(user.getPhono());;
		user2.setPassword(user.getPhono());
		user2.setProfile(user.getProfile());
		userRepository.save(user2);
		
	}
		
		return null;
	}

}
