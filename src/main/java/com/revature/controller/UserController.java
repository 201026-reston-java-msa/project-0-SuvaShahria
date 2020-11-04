package com.revature.controller;

import java.util.Scanner;

import com.revature.model.*;
import com.revature.services.UserService;

public class UserController {
	
	private static final UserService userService = new UserService();

	public User login(String username, String pssw) {

		 User user = userService.login(username,pssw);
		user.setRoleID(user.getRole().getRoleId());
		return user;
		
	}
	
	public User register() {
		Scanner sc = new Scanner(System.in);
		User user = new User();
		System.out.println("Enter User Name");
		user.setUsername(sc.nextLine());
		System.out.println("Enter Password");
		user.setPassword(sc.nextLine());
		System.out.println("Enter First Name");
		user.setFirstName(sc.nextLine());
		System.out.println("Enter Last Name");
		user.setLastName(sc.nextLine());
		System.out.println("Enter Email");
		user.setEmail(sc.nextLine());
		user.setRoleID(3);
		Role r = new Role(3,"customer");
		user.setRole(r);
		//System.out.println(user);
		if(userService.findByEmail(user.getEmail())!=null) {
			// log here
			return null;
		}
		if( userService.findByUsername(user.getUsername())!=null ) {
			//log here
			return null;
		}
		try {
			User u = userService.register(user);
			return u;
		}catch(Exception e) {
			return null;
		}
	}

}
