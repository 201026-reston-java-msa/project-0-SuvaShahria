package com.revature.dao.impl;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import com.revature.controller.AccountController;
import com.revature.dao.*;
import com.revature.model.*;
import org.junit.runners.MethodSorters;


/*
 * 
 * Trouble importing @TestMethodOrder from junit 5
 * So used @FixMethodOrder(MethodSorters.NAME_ASCENDING) to control order of tests
 * That's why naming is weird
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class doaTest {

	
	private static final AccountDaoImpl acd =  new AccountDaoImpl(1);
	private static final UserDaoImpl usd = new UserDaoImpl(1);
	private static final AccountController acc = new AccountController();
	private static final AccountStatusDao asdao = AccountStatusDaoImpl.getInstance();
	private static final AccountTypeDao atdao = AccountTypeDaoImpl.getInstance();
	static User user = new User();
	static  User user2= new User();
	static int id;
	static Account ac = new Account();
	static Account ac2 = new Account();
	static Account ac3 = new Account();
	
	@BeforeClass
	public static void init() {
		
		
		user.setUsername("t");
		user.setPassword("t");
		user.setFirstName("t");
		user.setLastName("t");
		user.setEmail("t@gmail.com");
		user.setRoleID(3);
		Role r = new Role(3,"customer");
		user.setRole(r);
		
		
	}
	@Test
	
	public void a_insertUser() {
		//System.out.println("Insert 1");
		User u = null;
		try {
			 u = usd.insert(user);
		}catch(Exception e) {
			
		}
		
		User tu = usd.findByUsername("t");
		id = tu.getUserId();
		if(u!=null) {
			assert(true);
		}else {
			assert(false);
		}
		

	}
	@Test
	public void b_duplicateInsert() {
		//System.out.println("Insert 2");
		User u = null;
		assert(true);
		try {
			 u = usd.insert(user);
		}catch(Exception e) {
			
		}
		
		
		if(u!=null) {
			assert(false);
		}else {
			assert(true);
		}
	}
	
	@Test
	public void c_loginTest() {
		User u = usd.login("t", "t");
		if(u==null) {
			assert(false);
		}else {
			assert(true);
		}
	}
	
	@Test
	public void c_loginFailTest() {
		User u = usd.login("t", "t2");
		if(u==null) {
			assert(true);
		}else {
			assert(false);
		}
	}
	
	@Test
	public void d_loginFailTest() {
		User u = usd.login("t2", "t2");
		if(u==null) {
			assert(true);
		}else {
			assert(false);
		}
	}

	
	@Test
	public void e_insertAcc() {
		ac= acc.insertAccount(1);
		if(ac!=null) {
			assert(true);
		}else {
			assert(false);
		}
		
	}
	
	@Test
	public void f_insertAcc() {
		ac2= acc.insertAccount(1);
		if(ac2!=null) {
			assert(true);
		}else {
			assert(false);
		}
		
	}
	
	@Test
	public void g_deposit() {
		ac.setBalance(100);
		 acd.updateBalance(ac);
		 Account tmp = acd.findById(ac.getAccountId());
		 if(ac.getBalance() ==100) {
			 assert(true);
		 }else {
			 assert(false);
		 }
	}
	
	@Test
	public void h_insertAcc() {
		ac3 = null;
		ac3= acc.insertAccount(5);
		
		if(ac3!=null) {
			assert(false);
		}else {
			assert(true);
		}
		
	}
	
	@Test
	public void i_update() {
		ac2.setStatusId(2);
		Account tmp =acd.update(ac2, 1);
		
		if(tmp.getStatus().getStatusId() ==2) {
			assert(true);
		}else {
			assert(false);
		}
	}
	
	
	
	@AfterClass
	public static void finish() {
		User u = usd.findByUsername("t");
		usd.delete(u);
		acd.delete(ac);
		acd.delete(ac2);
	}
}