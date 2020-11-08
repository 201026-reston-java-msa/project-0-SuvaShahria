package com.revature.dao;

import java.util.List;

import com.revature.model.User;

public interface UserDao {

	User login(String username, String password);

	User insert2(User user);

	User insert(User user);

	boolean update(User user);

	boolean delete(User u);

	User findById(int id);

	User findByEmail(String email);

	User findByUsername(String u);

	List<User> findAllUsers();

}