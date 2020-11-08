package com.revature.controller;

import java.util.Scanner;
import java.util.List;
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
			System.out.println("Email exists");
			// log here
			return null;
		}
		if( userService.findByUsername(user.getUsername())!=null ) {
			//log here
			System.out.println("Username exists");
			return null;
		}
		try {
			User u = userService.register(user);
			return u;
		}catch(Exception e) {
			System.out.println("Error creating account");
			return null;
		}
	}

	public User getUserById() {
		// TODO Auto-generated method stub
		//User user = userService.findById(id);
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the id of user your looking for");
		int id = Integer.parseInt(sc.nextLine());
		User user = userService.findById(id);
		return user;
		
	}

	public List<User> findAll() {
		// TODO Auto-generated method stub
		List<User> UL = null;
		 UL =userService.findAllUsers();
		 return UL;
	}

}
