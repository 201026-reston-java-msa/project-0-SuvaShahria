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
		boolean b = false;
		try {
			User user = uc.login(username,pssw);
			curRole = user.getRoleID();
			UserID = user.getUserId();
//			System.out.println(curRole);
//			System.out.println(UserID);
			this.user = user;
			b = true;
		}catch(Exception e) {
			System.out.println("login error");
			//e.printStackTrace();
			
			d.start();
		}
		if(b) {
			d.loggedIn();
		}
		
	}
	
	public void loggedIn() {
		Scanner sc = new Scanner(System.in);
		if(curRole == 1) {
			//admin
			System.out.println("Admin: What would you like to do");
			System.out.println("1: Deposit 2: Withdraw 3: Transfer 4: Approval 5: Close 6: Exit");
			String s = sc.nextLine();
			switch(s) {
			case "1":
				Account dAC = ac.deposit(UserID,curRole);
				if(dAC==null) {
					System.out.println("Please try Again");
					loggedIn();
				}else {
					System.out.println(dAC);
					loggedIn();
				}
				break;
			case "2":
				Account wAC = ac.withdraw(UserID,curRole);
				if(wAC==null) {
					System.out.println("Please try Again");
					loggedIn();
				}else {
					System.out.println(wAC);
					loggedIn();
				}
				break;
			case "3":
				Account tAC = ac.transfer(UserID,curRole);
				if(tAC== null) {
					System.out.println("Please try Again");
					loggedIn();
				}else {
					//System.out.println(tAC);
					loggedIn();
				}
				break;
			case "4":
				Account acc = null;
				ac.approve();
				loggedIn();
				break;
			case "5":
				ac.close();
				loggedIn();
				break;
			default:
				break;
			}
			
		}else if(curRole ==2) {
			//employee
			System.out.println("Employee: What would you like to do");
			System.out.println("1: View Customer Info 2: View Account Info 3: Approval 4: Exit");
			String s = sc.nextLine();
			switch(s) {
			case "1":
				User u = null;
				
				try {
					u = uc.getUserById();
				}catch(Exception e) {
					
				}
				if(u==null) {
					System.out.println("User not found");
					loggedIn();
				}else {
					System.out.println(u);
					loggedIn();
				}
				break;
			case "2":
				Account account = null;
				try {
					account = ac.findByID();
				}catch(Exception e) {
					
				}
				if(account==null) {
					System.out.println("Account not found");
					loggedIn();
				}else {
					System.out.println(account);
					loggedIn();
				}
				break;
			case "3":
				Account acc = null;
				ac.approve();
				loggedIn();
			default:
				break;
			
			}
		}else if(curRole == 3) {
			//customer
			
			System.out.println("What would you like to do");
			System.out.println("1: Create an account. 2: Deposit. 3: Withdraw. 4: Transfer 5: Exit");
			String s = sc.nextLine();
			switch(s) {
			case "1":
				Account ac2= ac.insertAccount(UserID);
				System.out.println("Account "+ac2.getAccountId() + " has been created. Waiting on approval");
				loggedIn();
				
				break;
			case "2":
				Account dAC = ac.deposit(UserID,curRole);
				if(dAC==null) {
					System.out.println("Please try Again");
					loggedIn();
				}else {
					System.out.println(dAC);
					loggedIn();
				}
				
				break;
			case "3":
				Account wAC = ac.withdraw(UserID,curRole);
				if(wAC==null) {
					System.out.println("Please try Again");
					loggedIn();
				}else {
					System.out.println(wAC);
					loggedIn();
				}
				break;
			case "4":
				Account tAC = ac.transfer(UserID,curRole);
				if(tAC== null) {
					System.out.println("Please try Again");
					loggedIn();
				}else {
					//System.out.println(tAC);
					loggedIn();
				}
				break;
			default:
				break;
			}
		}
	}
	

}
