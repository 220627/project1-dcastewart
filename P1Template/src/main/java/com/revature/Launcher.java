package com.revature;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.revature.controllers.AuthController;
import com.revature.controllers.ReimbController;
import com.revature.controllers.UserController;
import com.revature.daos.ReimbDAO;
import com.revature.daos.UserDAO;
import com.revature.models.Reimbursement;
import com.revature.utils.ConnectionUtil;

import io.javalin.Javalin;

public class Launcher {

	public static void main(String[] args) {
		
		//Welcome to P1! 
		
		//If you're reading this, you've successfully cloned your repo and imported the template
		
		//Do you coding in this project, and don't forget to save/push your progress with:
		//git add .
		//git commit -m"message"
		//git push
		System.out.println("====================Welcome to the ERS====================");
		
		try(Connection conn = ConnectionUtil.getConnection()){
			System.out.println("CONNECTION SUCCESSFUL");
		} catch (SQLException e) {
			//if the connection fails to open, catch the resulting SQLException and print the stack trace (error log)
			System.out.println("connection failed...");
			e.printStackTrace();
		}
		
//		UserDAO uDAO = new UserDAO();
//		ReimbDAO rDAO = new ReimbDAO();
//		System.out.println(uDAO.getUserByID(1));
//		Reimbursement r = new Reimbursement(400.00, 1, 1, 1, "Hotel for Business trip");
//		System.out.println(rDAO.insertReimb(r));
//		System.out.println("============get all");
//		System.out.println(rDAO.getAllReimbursements());
//		System.out.println("============editing");
//		rDAO.editStatus(1, 2);
//		System.out.println(rDAO.getReimbByID(1));
//		System.out.println(rDAO.getUserReimbursements(1));
		
		Javalin app = Javalin.create(
				
				config -> {
					config.enableCorsForAllOrigins();
				}
				
				).start(3000);
		
		UserController uc = new UserController();
		AuthController ac = new AuthController();
		ReimbController rc = new ReimbController();
		
		app.get("/users/:id", uc.getUserByIDHandler);
		app.post("/login", ac.loginHandler);
		app.get("/reimbursements", rc.getAllReimbsHandler);
		app.post("/reimbursements", rc.createReimbHandler);
		app.put("/reimbursements/:id", rc.editStatusHandler);
		app.get("/reimbursements/:status", rc.getReimbsByStatusHandler);
		app.post("/logout", ac.logoutHandler);
		
		
		
		
		
		
		
		
//               __
//          (___()'`;
//          /,    /`
//          \\"--\\
		
	}
	
}
