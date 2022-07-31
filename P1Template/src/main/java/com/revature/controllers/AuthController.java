package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.models.LoginDTO;
import com.revature.models.User;
import com.revature.services.AuthService;

import io.javalin.http.Handler;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuthController {

	
	public static Logger log = LogManager.getLogger();
	
	
	AuthService as = new AuthService();
	
	
	public static HttpSession ses;
	public static User currentUser;
	
	
	public Handler loginHandler = (ctx) -> {
		
		String body = ctx.body();
		
		Gson gson = new Gson();
		
		LoginDTO lDTO = gson.fromJson(body, LoginDTO.class);
		
		
		User user = as.login(lDTO.getUsername(), lDTO.getPassword()); 
		
		if(user != null) { 
			
			log.info("User Logged In!");
			
			ses = ctx.req.getSession(); 
			currentUser = user;
			
			String userJSON = gson.toJson(user);
			
			ctx.result(userJSON); //send the user to the front end
			ctx.status(202); 
			
		} else {
			
			log.warn("User Failed to Login!");
			
			ctx.status(401);
			ctx.result("login failed");
		} 
		
	}; 
	
	public Handler logoutHandler = (ctx) -> {
		if(ses != null) {
			ses = null;
			currentUser = null;
			
			log.info("User Logged Out");
			ctx.status(200);
		} else {
			log.warn("No user to log out");
			ctx.status(400);
		}
	};
	
	
}
