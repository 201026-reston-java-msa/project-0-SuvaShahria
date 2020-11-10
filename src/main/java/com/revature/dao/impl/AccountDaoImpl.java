package com.revature.dao.impl;
import com.revature.model.*;
import com.revature.Driver;
import com.revature.dao.AccountDao;
import com.revature.dao.AccountStatusDao;
import com.revature.dao.AccountTypeDao;
import com.revature.dao.UserDao;
import com.revature.dao.util.mySqlConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
public class AccountDaoImpl implements AccountDao {
	
	private static final AccountDao adao = new AccountDaoImpl();
	private static final AccountTypeDao atdao = AccountTypeDaoImpl.getInstance();
	private static final AccountStatusDao asdao = AccountStatusDaoImpl.getInstance();
	private static final UserDao udao = UserDaoImpl.getInstance();
	private static final Logger logac= Logger.getLogger(AccountDaoImpl.class);
	private AccountDaoImpl() {
		
	}
	public AccountDaoImpl( int x) {
		
	}
	public static AccountDao getInstance() {
		return adao;
	}
	
	@Override
	public Account insert(Account account) {
		
		
		try(Connection c = mySqlConnector.getConnection()){
			String sql = "INSERT INTO accounts (user_id_fk,balance,status_id_fk,type_id_fk) "
					+ "VALUES (?,?,?,?);";
			PreparedStatement statement = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1,account.getUserId());
			statement.setFloat(2,account.getBalance());
			statement.setInt(3,account.getStatus().getStatusId());
			statement.setInt(4,account.getType().getTypeId());
			
			int succ = statement.executeUpdate();
			
			if(succ > 0) {
				
				ResultSet rs = statement.getGeneratedKeys();
				
				while(rs.next()) {
					account = findById((int)rs.getInt(1));
				}
				logac.info("Account inserted");
				rs.close();
		            
			}else {
				logac.error("Account not inserted");
			}
			
			return account;

		}catch(SQLException e) {
			//System.out.println(e);
		}
		return null;
	
	}
	
	@Override
	public Account delete(Account ac) {
		//System.out.println(ac.getAccountId());
		Account ac2 = findById(ac.getAccountId());
		try(Connection connector = mySqlConnector.getConnection();){
			
			String sql ="DELETE "+ "FROM accounts "+ "WHERE account_id = ?;";
			PreparedStatement statement = connector.prepareStatement(sql);
			statement = connector.prepareStatement(sql);
			statement.setInt(1,ac.getAccountId());
			
			
			statement.executeUpdate();
			logac.info("Account deleted");
			return ac2;
							
		}catch(SQLException e) {
			e.printStackTrace();
		}
		logac.error("Account Not deleted");
		return null;
	}
	
	@Override
	public Account updateStatus(Account account) {
		
		try(Connection c = mySqlConnector.getConnection()){
			String sql = "UPDATE accounts SET status_id_fk = ? WHERE account_id = ?";
			PreparedStatement statement = c.prepareStatement(sql);			
			statement.setInt(1,account.getStatus().getStatusId());
			statement.setInt(2,account.getAccountId());
			
			statement.executeUpdate();
			account = findById(account.getAccountId());
			logac.info("Account Updated");
			return account;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		logac.error("Account not updated");
		return null;		
	}
	
	@Override
	public Account findById(int id) {
		//System.out.println("Looking Up Account by id");
		
		try(Connection connector = mySqlConnector.getConnection()){
			String sql = "SELECT * FROM accounts WHERE account_id = ?";
			PreparedStatement statement = connector.prepareStatement(sql);
			statement.setInt(1,id);
			
			ResultSet rs = statement.executeQuery();
			
			if(rs.next()) {
				//udao.findById(id)
				Account ac = new Account(rs.getInt("account_id"),rs.getInt("user_id_fk"),
						udao.findById(rs.getInt("user_id_fk")),
						rs.getFloat("balance"),
						asdao.findById(rs.getInt("status_id_fk")),
						atdao.findById(rs.getInt("type_id_fk")));
				logac.info("Account found");
				return ac;
//				System.out.println(rs.getInt("account_id"));
//				System.out.println(rs.getInt("user_id_fk"));
//				System.out.println(udao.findById(rs.getInt("user_id_fk")).getUserId()  );
			}
			
		}catch(SQLException e) {
			
		}
		logac.error("Account not found");
		return null;
	}
	@Override
	public Account update(Account ac, int roleId) {
		
		try(Connection c = mySqlConnector.getConnection()){	
			if(roleId == 1) {
				String sql = "UPDATE accounts SET user_id_fk = ?, status_id_fk = ? WHERE account_id = ?;";
				
				
				PreparedStatement statement = c.prepareStatement(sql);
				statement.setInt(1,ac.getUserId());
				statement.setInt(2,ac.getStatusId());
				statement.setInt(3,ac.getAccountId());
				statement.executeUpdate();	
			}else {
				String sql = "UPDATE accounts SET status_id_fk = ? WHERE account_id = ?;";
				
				
				PreparedStatement statement = c.prepareStatement(sql);
				statement.setInt(1,ac.getStatusId());
				statement.setInt(2,ac.getAccountId());
				statement.executeUpdate();
				
			}
				
			Account ac2 = findById(ac.getAccountId());
			logac.info("Account updated");
			return ac2;
						
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		logac.error("Account not updated");
		return null;
	}
	@Override
	public List<Account> findAll() {
		try(Connection connector = mySqlConnector.getConnection()){
			String sql = "SELECT * FROM accounts account_id;";
			Statement statement = connector.createStatement();
			
			List<Account> accounts = new ArrayList<>();
						
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				Account ac = new Account(rs.getInt("account_id"),rs.getInt("user_id_fk"),
						udao.findById(rs.getInt("user_id_fk")),
						rs.getFloat("balance"),
						asdao.findById(rs.getInt("status_id_fk")),
						atdao.findById(rs.getInt("type_id_fk")));
				
				accounts.add(ac);
			}
			logac.info("Accounts found");
			return accounts;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		logac.error("Accounts not found");
		return null;
	}
	@Override
	public List<Account> findAccountsById(int id) {
		try(Connection connector = mySqlConnector.getConnection()){
			String sql = "SELECT * FROM accounts WHERE user_id_fk = ?;";
			PreparedStatement statement = connector.prepareStatement(sql);
			statement.setInt(1,id);
			
			List<Account> accounts = new ArrayList<>();
			
			ResultSet rs = statement.executeQuery();
			
		
//			String sql ="DELETE "+ "FROM accounts "+ "WHERE account_id = ?;";
//			PreparedStatement statement = connector.prepareStatement(sql);
//			statement = connector.prepareStatement(sql);
//			statement.setInt(1,ac.getAccountId());
			
			
						
			
			
			while(rs.next()) {
				Account ac = new Account(rs.getInt("account_id"),rs.getInt("user_id_fk"),
						udao.findById(rs.getInt("user_id_fk")),
						rs.getFloat("balance"),
						asdao.findById(rs.getInt("status_id_fk")),
						atdao.findById(rs.getInt("type_id_fk")));
				
				accounts.add(ac);
			}
			
			return accounts;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	@Override
	public List<Account> findAccountsByStatusId(int id) {
		try(Connection connector = mySqlConnector.getConnection()){
			String sql = "SELECT * FROM accounts WHERE status_id_fk = ?;";
			PreparedStatement statement = connector.prepareStatement(sql);
			statement.setInt(1,id);
			
			List<Account> accounts = new ArrayList<>();
			
			ResultSet rs = statement.executeQuery();
			
		
//			String sql ="DELETE "+ "FROM accounts "+ "WHERE account_id = ?;";
//			PreparedStatement statement = connector.prepareStatement(sql);
//			statement = connector.prepareStatement(sql);
//			statement.setInt(1,ac.getAccountId());
			
			
						
			
			
			while(rs.next()) {
				Account ac = new Account(rs.getInt("account_id"),rs.getInt("user_id_fk"),
						udao.findById(rs.getInt("user_id_fk")),
						rs.getFloat("balance"),
						asdao.findById(rs.getInt("status_id_fk")),
						atdao.findById(rs.getInt("type_id_fk")));
				
				accounts.add(ac);
			}
			
			return accounts;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	@Override
	public void updateBalance(Account ac) {
//		System.out.println(ac.getAccountId());
//		System.out.println(ac.getBalance());
		
		 
		try(Connection c = mySqlConnector.getConnection()){	
			
				String sql = "UPDATE accounts SET balance = ? WHERE account_id = ?;";
				
				
				PreparedStatement statement = c.prepareStatement(sql);
				statement.setFloat(1,ac.getBalance());
				statement.setInt(2,ac.getAccountId());
				statement.executeUpdate();
				
			
				
			
				logac.info("Balance Updated");
		}catch(SQLException e) {
			logac.error("Balance not updated");
			
		}
	
	}

}
