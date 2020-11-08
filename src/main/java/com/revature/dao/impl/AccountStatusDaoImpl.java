package com.revature.dao.impl;
import com.revature.model.*;
import com.revature.dao.AccountStatusDao;
import com.revature.dao.util.mySqlConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountStatusDaoImpl implements AccountStatusDao {
	private static AccountStatusDao ASD = new AccountStatusDaoImpl();
	private AccountStatusDaoImpl() {
		
	}
	public AccountStatusDaoImpl(int x) {
	
	}
	public static AccountStatusDao getInstance() {
		return ASD;
	}
	
	@Override
	public AccountStatus findById(int id) {
		try(Connection conn = mySqlConnector.getConnection()){
			String sql = "SELECT * FROM account_status WHERE status_id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1,id);
			
			ResultSet rs = statement.executeQuery();
			
			if(rs.next()) {
				AccountStatus ac = new AccountStatus(rs.getInt("status_id"),rs.getString("acc_status")  );
				return ac;						
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
