package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.daos.UserDAO;
import com.revature.models.User;

import io.javalin.http.Handler;

public class UserController {
	
	UserDAO uDAO = new UserDAO();
	
	public Handler getUserByIDHandler = (ctx) -> {
		
		if(AuthController.ses != null) {
			int id = Integer.valueOf(ctx.pathParam("id"));
			
			User user = uDAO.getUserByID(id);
			
			Gson gson = new Gson();
			
			String jsonUser = gson.toJson(user);
			
			ctx.result(jsonUser);
			ctx.status(200);
			
		} else {
			ctx.result("You are not logged in");
			ctx.status(401);
		}
		
	};

}
