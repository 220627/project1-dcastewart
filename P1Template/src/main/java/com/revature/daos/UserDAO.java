package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class UserDAO {
	
	public UserDAO() {
		super();
	}
	
	public User getUserByID(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "select * from users where user_id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				User user = new User(
						rs.getInt("user_id"),
						rs.getString("user_username"),
						rs.getString("user_password"),
						rs.getString("user_first_name"),
						rs.getString("user_last_name"),
						rs.getString("user_email"),
						rs.getInt("user_role_id")
				);
				return user;
			}
			
			
		} catch(SQLException e) {
			System.out.println("Get user by id failed");
			e.printStackTrace();
		}
		
		return null;
	}

}
