package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class AuthDAO {

	
	
	public User login(String username, String password) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
		String sql = "SELECT * FROM users WHERE user_username = ? AND user_password = ?;";
			
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setString(1, username);
		ps.setString(2, password);
		
		ResultSet rs = ps.executeQuery();
		
		//if anything gets returned at all, we know a user exists with that username/password pair. 
		//so we can create a new User object to send to the front end
		if(rs.next()) {
			
			User u = new User(
					rs.getInt("user_id"),
					rs.getString("user_username"),
					rs.getString("user_password"),
					rs.getString("user_first_name"),
					rs.getString("user_last_name"),
					rs.getString("user_email"),
					rs.getInt("user_role_id")
					);
			System.out.println(u);
			
			return u;
			
			//notice we're returning a password here... probably not best practice lol
			//in a REAL application, you'd probably want a User constructor with no password
			//and send that around instead.
			
		}
		
		} catch (SQLException e) {
			System.out.println("Invalid Login");
			e.printStackTrace();
		}
		
		return null; //if no user is returned, return null. We will do a null check in the service layer.
		
	}
	
	
	public AuthDAO() {
		super();
	}
}
