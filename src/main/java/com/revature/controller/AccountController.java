package com.revature.controller;

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

}
