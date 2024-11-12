package com.onlinexam.ExamOnlineDemo.confi;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.onlinexam.ExamOnlineDemo.model.JwtUtil;
import com.onlinexam.ExamOnlineDemo.serviceimpl.UserDetailServiceimpl;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailServiceimpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");
        System.out.println("Authorization Header: " + requestTokenHeader);

        String username = null;
        String jwtToken = null;

        // JWT Token is in the form "Bearer token". Remove Bearer word and get only the Token
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer <token>")) {
            jwtToken = requestTokenHeader.substring(7);

            try {
                username = this.jwtUtil.extractUsername(jwtToken);
            } catch (ExpiredJwtException e) {
                e.printStackTrace();
                System.out.println("JWT token has expired");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error while extracting username from token");
            }
        } else {
            System.out.println("Invalid token, does not start with Bearer String");
        }

        // Once we get the token, validate it.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            // if token is valid configure Spring Security to manually set authentication
            if (this.jwtUtil.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthentication);
            }
            } else {
                System.out.println("Token is not valid");
            }
        

        filterChain.doFilter(request, response);
    }


}





//package com.onlinexam.ExamOnlineDemo.confi;
//
//
//
//import java.io.IOException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.onlinexam.ExamOnlineDemo.model.JwtUtil;
//import com.onlinexam.ExamOnlineDemo.serviceimpl.UserDetailServiceimpl;
//
//import io.jsonwebtoken.ExpiredJwtException;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@Component
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//	@Autowired
//	private UserDetailServiceimpl userDetailsService;
//	
//	
//	@Autowired
//	private JwtUtil jwtUtil;
//	
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		
//		
//		final String requestTokenHeader = request.getHeader("Authorization");
//		System.out.println(requestTokenHeader);
//		String username=null;
//		String jwtToken=null;
//		
//		
//		if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer")){
//			
//	jwtToken= requestTokenHeader.substring(7);
//	try {
//	username=this.jwtUtil.extractUsername(jwtToken);
//	}
//	catch (ExpiredJwtException e) {
//		// TODO: handle exception
//	e.printStackTrace();
//	System.out.println("jwt token has expired");
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//			System.out.println("error");
//		}
//		}
//		else {
//			System.out.println("Invalid token , not start with bearer string");
//		}
//		
//		
//		
//		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
//			
//			final UserDetails userDetails=this.userDetailsService.loadUserByUsername(username);
//		if(this.jwtUtil.validateToken(jwtToken, userDetails)) {
//			
//			
//			UsernamePasswordAuthenticationToken usernamePasswordAuthentication=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
//			
//			usernamePasswordAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//			
//			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthentication);
//			
//			
//			
//		}
//		
//		}else{
//			System.out.println("token is not valid");
//		}
//			filterChain.doFilter(request, response);
//	}
//	
//	
//
//}
