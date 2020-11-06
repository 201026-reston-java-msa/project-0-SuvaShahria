package com.revature.controller;

import java.util.Scanner;

import com.revature.model.Account;
import com.revature.services.AccountServices;
import com.revature.services.UserService;

public class AccountController {
	private static final AccountServices as = new AccountServices();

	public Account insertAccount(int userid) {
		// TODO Auto-generated method stub
		
		Account ac = new Account();
		ac.setUserId(userid);
		ac.setBalance(0);
		ac.setTypeId(1);
		ac.setStatusId(1);
		try {
			Account ac2 = as.insertAccount(ac);
			return ac2;
		}catch(Exception e) {
			return null;
		}
		
		
	}

	public Account deposit(int userID,int roleId) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the account ID");
		int acID = (int)Integer.parseInt(sc.nextLine());
		Account account = as.findById(acID);
		if(account!=null) {
			if(roleId ==1 || userID == account.getUserId() ) {
				System.out.println("Enter value to be deposited");
				try {
			        double d = Double.parseDouble(sc.nextLine());
			        if(d>0) {
			        	if(account.getStatusId() == 2) {
			        		account.setBalance(account.getBalance()+(float) d);
							as.updateBalance(account);
							System.out.printf("%.2f was deposited into account %d\n",(float)d,account.getAccountId());
			        		return account;
			        	}else {
			        		System.out.println("Account is not open");
			        	}
			        }else {
			        	System.out.println("value has to be positive");
			        }
			    } catch (NumberFormatException e) {
			       System.out.println("Enter a valid number: Resetting");
			    }
			}else {
				System.out.println("You don't have permission to access this account");
			}
			
			
		}else {
			System.out.println("Account Not found");
		}
		

		return null;
	}

	public Account withdraw(int userID, int curRole) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the account ID");
		int acID = (int)Integer.parseInt(sc.nextLine());
		Account account = as.findById(acID);
		if(account!=null) {
			if(curRole ==1 || userID == account.getUserId() ) {
				System.out.println("Enter value to be withdrawn");
				try {
			        double d = Double.parseDouble(sc.nextLine());
			        if(d>0) {
			        	if(account.getStatusId() == 2) {
			        		if(d>account.getBalance()) {
			        			System.out.println("Insufficient funds");
			        			return null;
			        		}
			        		account.setBalance(account.getBalance()-(float) d);
							as.updateBalance(account);
							System.out.printf("%.2f was withdrawn from account %d\n",(float)d,account.getAccountId());
			        		return account;
			        	}else {
			        		System.out.println("Account is not open");
			        	}
			        }else {
			        	System.out.println("value has to be positive");
			        }
			    } catch (NumberFormatException e) {
			       System.out.println("Enter a valid number: Resetting");
			    }
			}else {
				System.out.println("You don't have permission to access this account");
			}
			
			
		}else {
			System.out.println("Account Not found");
		}
		return null;
	}

	public Account transfer(int userID, int curRole) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the account ID of the account being withdrawn from followed by the one being deposited into");
		int acID = (int)Integer.parseInt(sc.nextLine());
		Account ac = as.findById(acID);
		int acID2 = (int)Integer.parseInt(sc.nextLine());
		Account ac2 = as.findById(acID2);
		
		if(ac ==null) {
			System.out.println("Account 1 not found");
			return null;
		}
		
		if(ac2 ==null) {
			System.out.println("Account 2 not found");
			return null;
		}
		if(curRole ==1 || userID == ac.getUserId() ) {
			try {
				System.out.println("Enter the amount");
				double d = Double.parseDouble(sc.nextLine());
		        if(d>0) {
		        	if(ac.getStatusId() == 2  && ac2.getStatusId() == 2) {
		        		if(d>ac.getBalance()) {
		        			System.out.println("Insufficient funds");
		        			return null;
		        		}
		        		ac.setBalance(ac.getBalance()-(float) d);
						as.updateBalance(ac);
						ac2.setBalance(ac2.getBalance()+(float) d);
						as.updateBalance(ac2);
						System.out.printf("%.2f was withdrawn from account %d and deposited into account %d\n",(float)d,ac.getAccountId(),ac2.getAccountId());
						System.out.println();
						System.out.println(ac);
						System.out.println();
						System.out.println(ac2);
		        		return ac;
		        	}
		        }else {
		        	System.out.println("Value has to be positive");
		        }
			}catch (NumberFormatException e) {
			       System.out.println("Enter a valid number: Resetting");
			    }
		}else {
			System.out.println("You don't have permission to access this account");
		}
		
		return null;
	}

	public Account findByID() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the id of the account your looking for");
		int id = Integer.parseInt(sc.nextLine());
		Account ac = as.findById(id);
		return ac;
	}

	public Account approve() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the id of the account your approving");
		int id = Integer.parseInt(sc.nextLine());
		Account ac = as.findById(id);
		if(ac == null) {
			System.out.println("Account not Found");
			return null;
		}
		if(ac.getStatusId() != 1) {
			System.out.println("This account is not pending approval");
			return null;
		}
		
		System.out.println("Would you like to 1:Approve 2:Deny");
		if(sc.nextLine().equals("1")) {
			ac.setStatusId(2);
			
		}else {
			ac.setStatusId(4);
		}
		Account ac2 = as.updateAccount(ac,1);
		return ac2;
	}

	public void close() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the id of the account your closing");
		int id = Integer.parseInt(sc.nextLine());
		Account ac = as.findById(id);
		if(ac == null) {
			System.out.println("Account not Found");
			
		}
		ac.setStatusId(3);
		Account ac2 = as.updateAccount(ac,1);
	}

}
