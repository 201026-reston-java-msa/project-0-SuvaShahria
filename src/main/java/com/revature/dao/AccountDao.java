package com.revature.dao;

import java.util.List;

import com.revature.model.Account;

public interface AccountDao {

	Account insert(Account account);

	Account delete(Account ac);

	Account updateStatus(Account account);

	Account findById(int id);

	Account update(Account ac, int roleId);

	List<Account> findAll();

	List<Account> findAccountsById(int id);

	List<Account> findAccountsByStatusId(int id);

	void updateBalance(Account ac);

}