package com.revature;

import java.util.Scanner;
import com.revature.model.*;
import com.revature.controller.*;

import org.apache.log4j.Logger;

public class Driver {
	
	private static final UserController uc = new UserController();
	private static final AccountController ac = new AccountController();
	private static final Logger log = Logger.getLogger(Driver.class);
	int curRole;
	int UserID;
	static Driver d = new Driver();
	User user;
	
	public static void main(String[] args) {
		
		d.start();

	}
	
	public void start() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter 1 to login, Enter 2 to register");
		
		String input = sc.nextLine();
		
		switch(input) {
		case "1":
			
			d.login();
			break;
		case "2":
			User u = uc.register();
			if(u == null) {
				
				//log error
				d.start();
			}else {
				//success
				System.out.println(u.getUsername()+ " has been created. Please Log in");
				d.start();
			}
			
			
			break;
		default:
			System.out.println("Error Reenter Input");
			break;
		}
		
	}
	
	public void login() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter User Name");
		String username = sc.nextLine();
		System.out.println("Enter Password");
		String pssw = sc.nextLine();
		
		try {
			User user = uc.login(username,pssw);
			curRole = user.getRoleID();
			UserID = user.getUserId();
//			System.out.println(curRole);
//			System.out.println(UserID);
			this.user = user;
			d.loggedIn();
		}catch(Exception e) {
			System.out.println("login error");
			
			d.start();
		}
		
	}
	
	public void loggedIn() {
		Scanner sc = new Scanner(System.in);
		if(curRole == 1) {
			//admin
			
		}else if(curRole ==2) {
			//employee
			
		}else if(curRole == 3) {
			//customer
			
			System.out.println("What would you like to do");
			System.out.println("1: Create an account. 2: Deposit. 3: Withdraw. 4: Transfer");
			String s = sc.nextLine();
			switch(s) {
			case "1":
				Account ac2= ac.insertAccount(UserID);
				System.out.println(ac2.getAccountId() + " has been created. Waiting on approval");
				
				break;
			case "2":
				break;
			case "3":
				break;
			case "4":
				break;
			default:
				break;
			}
		}
	}
	

}
