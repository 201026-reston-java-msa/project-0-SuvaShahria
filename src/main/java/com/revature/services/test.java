package com.revature.services;
import java.security.NoSuchAlgorithmException;
import com.revature.dao.*;
import com.revature.model.*;
public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserService us =new UserService();
		User u= us.login("s5","s");
		System.out.println(u.getEmail());
	}

}
