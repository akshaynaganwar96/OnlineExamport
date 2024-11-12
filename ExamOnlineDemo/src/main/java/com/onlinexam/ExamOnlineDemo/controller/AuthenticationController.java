package com.onlinexam.ExamOnlineDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onlinexam.ExamOnlineDemo.model.JwtRequest;
import com.onlinexam.ExamOnlineDemo.model.JwtResponse;
import com.onlinexam.ExamOnlineDemo.model.JwtUtil;
import com.onlinexam.ExamOnlineDemo.serviceimpl.UserDetailServiceimpl;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailServiceimpl userDetailServiceimpl;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            throw new Exception("User Not Found");
        }

        UserDetails userDetails = this.userDetailServiceimpl.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID CREDENTIALS", e);
        }
    }
}



//package com.onlinexam.ExamOnlineDemo.controller;
//
//import org.apache.catalina.connector.Response;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.onlinexam.ExamOnlineDemo.model.JwtRequest;
//import com.onlinexam.ExamOnlineDemo.model.JwtResponse;
//import com.onlinexam.ExamOnlineDemo.model.JwtUtil;
//import com.onlinexam.ExamOnlineDemo.serviceimpl.UserDetailServiceimpl;
//
//@RestController
//public class AuthenticationController {
//	
//	@Autowired
//	AuthenticationManager authenticationManager;
//
//	@Autowired
//	UserDetailServiceimpl userDetailServiceimpl;
//	
//	@Autowired
//	JwtUtil jwtUtil;
//	
//	@PostMapping("/generate-Token")
//	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
//	
//		try {
//			
//			authenticate(jwtRequest.getPassword(), jwtRequest.getPassword());
//			
//			
//			
//		} catch (UsernameNotFoundException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			throw new Exception("User Not Found");
//		}
//		
//		UserDetails userDetails = this.userDetailServiceimpl.loadUserByUsername(jwtRequest.getUsername());
//		
//		String token=this.jwtUtil.generateToken(userDetails);
//		
//		return ResponseEntity.ok(new JwtResponse(token));
//		
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	private void authenticate(String username,String password) throws Exception {
//		
//	try {
//		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//	}catch (DisabledException e) {
//		// TODO: handle exception
//throw new Exception("USER DISABLE");
//	}catch (BadCredentialsException e) {
//		// TODO: handle exception
//		throw new Exception("Inavalid Credential"+e.getMessage());
//	}
//		
//		
//		
//	}
//	
//}
