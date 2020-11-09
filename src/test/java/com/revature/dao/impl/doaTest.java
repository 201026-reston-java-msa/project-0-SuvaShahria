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
	static User user = new User();
	User user2= new User();
	int id;
	
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
		assert(true);
	}
	
	@AfterClass
	public static void finish() {
		User u = usd.findByUsername("t");
		
		usd.delete(u);
	}
}