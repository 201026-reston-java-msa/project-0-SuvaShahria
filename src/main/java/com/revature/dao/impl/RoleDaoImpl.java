package com.revature.dao.impl;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.revature.model.*;
import com.revature.dao.RoleDao;
import com.revature.dao.util.mySqlConnector;
public class RoleDaoImpl implements RoleDao {

	private static RoleDao rd = new RoleDaoImpl();
	private RoleDaoImpl() {}
	public static RoleDao getInstance() {
		return rd;
	}
	public RoleDaoImpl(int x) {
		
	}

	
	@Override
	public Role findRoleById(int id) {
		
		try(Connection connection = mySqlConnector.getConnection()){
			String sql = "SELECT * FROM roles WHERE role_id = ?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1,id);
			
			ResultSet resultset = statement.executeQuery();
			
			if(resultset.next()) {
				return new Role(
						resultset.getInt("role_id"), 					
						resultset.getString("role_name"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
