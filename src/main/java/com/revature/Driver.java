package com.revature;

import java.util.List;
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
		log.info("Program Start");
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
			log.warn("Login Failed");
			System.out.println("login error");
			//e.printStackTrace();
			
			d.start();
		}
		if(b) {
			log.info("User logged in");
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
					log.warn("Deposit Error");
					System.out.println("Please try Again");
					loggedIn();
				}else {
					log.info("Deposit Success");
					System.out.println(dAC);
					loggedIn();
				}
				break;
			case "2":
				Account wAC = ac.withdraw(UserID,curRole);
				if(wAC==null) {
					log.warn("Withdraw Error");
					System.out.println("Please try Again");
					loggedIn();
				}else {
					log.info("Withdraw Success");
					System.out.println(wAC);
					loggedIn();
				}
				break;
			case "3":
				Account tAC = ac.transfer(UserID,curRole);
				if(tAC== null) {
					log.warn("Transfer Error");
					System.out.println("Please try Again");
					loggedIn();
				}else {
					log.info("Transfer Error");
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
				System.out.println("Would you like to view all. 1: Yes 2: No");
				if(sc.nextLine().equals("1")) {
					List<User> UL = uc.findAll();
					if(UL!=null) {
						log.info("Customers found");
						for(User u1: UL) {
							System.out.println(u1);
						}
					
					}else {
						log.warn("No Customers found");
						System.out.println("No Users found");
					}
				}else {
					try {
						u = uc.getUserById();
					}catch(Exception e) {
						
					}
					if(u==null) {
						log.warn("User not found");
						System.out.println("User not found");
						
					}else {
						log.info("User found");
						System.out.println(u);
						
					}
				}
				loggedIn();
				break;
			case "2":
				Account account = null;
				System.out.println("Would you like to view all. 1: Yes 2: No");
				if(sc.nextLine().equals("1")) {
					List<Account> accounts = ac.findAll();
					if(accounts!=null) {
						log.info("Accounts found");
						for(Account ac1: accounts) {
							System.out.println(ac1);
						}
						
					}else {
						log.warn("Accounts not found");
						System.out.println("No Accounts found");
					}
				}else {
					try {
						
						account = ac.findByID();
					}catch(Exception e) {
						
					}
					if(account==null) {
						log.warn("Account not found");
						System.out.println("Account not found");
						loggedIn();
					}else {
						log.info("Account found");
						System.out.println(account);
						loggedIn();
					}
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
				if(ac2 != null) {
					log.info("Account created");
					System.out.println("Account "+ac2.getAccountId() + " has been created. Waiting on approval");
					
				}else {
					log.error("Account not created");
				}
				
				loggedIn();
				
				break;
			case "2":
				Account dAC = ac.deposit(UserID,curRole);
				if(dAC==null) {
					log.warn("Deposit error");
					System.out.println("Please try Again");
					loggedIn();
				}else {
					log.info("Deposited");
					System.out.println(dAC);
					loggedIn();
				}
				
				break;
			case "3":
				Account wAC = ac.withdraw(UserID,curRole);
				if(wAC==null) {
					log.error("withdraw error");
					System.out.println("Please try Again");
					loggedIn();
				}else {
					log.info("Withdrawn");
					System.out.println(wAC);
					loggedIn();
				}
				break;
			case "4":
				Account tAC = ac.transfer(UserID,curRole);
				if(tAC== null) {
					log.error("Transfer error");
					System.out.println("Please try Again");
					loggedIn();
				}else {
					log.info("Transferred");
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
