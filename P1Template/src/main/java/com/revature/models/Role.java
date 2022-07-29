package com.revature.models;

public class Role {
	
	private int role_id;
	private String role;
	
	public Role(int roleID) {
		this.role_id = roleID;
		if(roleID == 1) {
			role = "Manager";
		} else {
			role = "Employee";
		}
	}
	

	public int getRole_id() {
		return role_id;
	}


	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}
	
	


	@Override
	public String toString() {
		return "Role [role_id=" + role_id + ", role=" + role + "]";
	}


	public Role() {
		// TODO Auto-generated constructor stub
	}

}
