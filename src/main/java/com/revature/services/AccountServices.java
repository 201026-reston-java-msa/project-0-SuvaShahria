package com.revature.services;

import java.util.List;

import com.revature.dao.*;
import com.revature.dao.impl.AccountDaoImpl;
import com.revature.dao.impl.AccountStatusDaoImpl;
import com.revature.dao.impl.AccountTypeDaoImpl;
import com.revature.dao.impl.RoleDaoImpl;
import com.revature.dao.impl.UserDaoImpl;
import com.revature.model.*;

public class AccountServices {

	private static final RoleDao rdao = RoleDaoImpl.getInstance();
	private static final UserDao udao = UserDaoImpl.getInstance();
	private static final AccountDao acdao = AccountDaoImpl.getInstance();
	private static final AccountStatusDao asdao = AccountStatusDaoImpl.getInstance();
	private static final AccountTypeDao atdao = AccountTypeDaoImpl.getInstance();
	
	public Account insertAccount(Account ac) {
		
		ac.setStatus(asdao.findById(ac.getStatusId()));
		ac.setType(atdao.findById(ac.getTypeId()));
		Account ac2 = acdao.insert(ac);
		if(ac2 != null) {
			ac2.setStatusId(ac.getStatusId());
			ac2.setTypeId(ac.getTypeId());
		}
		return ac2;
		
	}

	public Account updateAccount(Account ac,int roleId) {
		ac.setStatus(asdao.findById(ac.getStatusId()));
		ac.setType(atdao.findById(ac.getTypeId()));
		Account ac2 = acdao.update(ac,roleId);
		if(ac2 != null) {
			ac2.setStatusId(ac.getStatusId());
			ac2.setTypeId(ac.getTypeId());
		}
		return ac2;
		
	}

	public Account deleteUser(Account ac) {
		//System.out.println("in service" +ac.getAccountId());

		ac.setStatus(asdao.findById(ac.getStatusId()));
		ac.setType(atdao.findById(ac.getTypeId()));
		Account ac2 = acdao.delete(ac);
		if(ac2 != null) {
			ac2.setStatusId(ac.getStatusId());
			ac2.setTypeId(ac.getTypeId());
		}
		return ac2;
	}

	public List<Account> findAll() {
		// TODO Auto-generated method stub
		
		List<Account> accounts = acdao.findAll();
		if(accounts != null) {
			for(Account a:accounts) {
				a.setStatusId(a.getStatus().getStatusId());
				a.setTypeId(a.getType().getTypeId());
			}
		}
		
		return accounts;
	}

	public Account findById(int id) {
		// TODO Auto-generated method stub
		Account ac = acdao.findById(id);
		if(ac!= null) {
			ac.setStatusId(ac.getStatus().getStatusId());
			ac.setTypeId(ac.getType().getTypeId());
		}
		return ac;
	}

	public List<Account> findAccountsById(int id) {
		// TODO Auto-generated method stub
		List<Account> accounts = acdao.findAccountsById(id);
		if(accounts!= null) {
			for(Account a:accounts) {
				a.setStatusId(a.getStatus().getStatusId());
				a.setTypeId(a.getType().getTypeId());
			}
		}
		
		return accounts;
		
	}

	public List<Account> findAccountsByStatusId(int id) {
		// TODO Auto-generated method stub
		List<Account> accounts = acdao.findAccountsByStatusId(id);
		if(accounts!= null) {
			for(Account a:accounts) {
				a.setStatusId(a.getStatus().getStatusId());
				a.setTypeId(a.getType().getTypeId());
			}
		}
		
		return accounts;
	}

	public void updateBalance(Account ac) {
		// TODO Auto-generated method stub
		acdao.updateBalance(ac);
//		System.out.println(ac.getAccountId());
//		System.out.println(ac.getBalance());
		
	}

}
