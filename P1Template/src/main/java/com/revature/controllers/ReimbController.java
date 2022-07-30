package com.revature.controllers;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.revature.daos.ReimbDAO;
import com.revature.models.Reimbursement;

import io.javalin.http.Handler;

public class ReimbController {
	
	ReimbDAO rDAO = new ReimbDAO();
	
	
	public Handler getAllReimbsHandler = (ctx) -> {
		
		if(AuthController.ses != null) {
			Gson gson = new Gson();
			ArrayList<Reimbursement> reimbs;
			if(AuthController.currentUser.getRole().getRole_id() == 1) {
				reimbs = rDAO.getAllReimbursements();
			} else {
				reimbs = rDAO.getUserReimbursements(AuthController.currentUser.getUser_id());
			}
			
			String jsonReimbs = gson.toJson(reimbs);
			ctx.result(jsonReimbs);
			ctx.status(200);
		} else {
			ctx.result("You are not logged in");
			ctx.status(401);
		}
		
	};
	
	
	public Handler createReimbHandler = (ctx) -> {
		
		if(AuthController.ses != null) {
			if(AuthController.currentUser.getRole().getRole_id() == 2) {
				
				String body = ctx.body();
				Gson gson = new Gson();
				
				Reimbursement r = gson.fromJson(body, Reimbursement.class);
				
				if(rDAO.insertReimb(r)) {
					ctx.status(202);
				} else {
					ctx.status(406);
				}
				
			} else {
				ctx.result("Only employees may file a reimbursement request");
				ctx.status(401);
			}
		} else {
			ctx.result("You are not logged in");
			ctx.status(401);
		}
		
		
	};
	
	
	public Handler editStatusHandler = (ctx) -> {
		
		if(AuthController.ses != null) {
			if(AuthController.currentUser.getRole().getRole_id() == 2) {
				String body = ctx.body();
				Gson gson = new Gson();
				int newStatus = gson.fromJson(body, Integer.class);
				int id = Integer.valueOf(ctx.pathParam("id"));
				if(rDAO.editStatus(id, newStatus)) {
					ctx.status(202);
				} else {
					ctx.result("Status was not updated successfully");
					ctx.status(406);
				}
				
				
			} else {
				ctx.result("Only managers can change reimbursement requests statuses");
				ctx.status(401);
			}
		} else {
			ctx.result("You are not logged in");
			ctx.status(401);
		}
		
	};
	
	public Handler getReimbsByStatusHandler = (ctx) -> {
		
		if(AuthController.ses != null) {
			
			int status = Integer.valueOf(ctx.pathParam("status"));
			Gson gson = new Gson();
			ArrayList<Reimbursement> reimbs;
			if(AuthController.currentUser.getRole().getRole_id() == 1) {
				reimbs = rDAO.getReimbsByStatus(status);
				String jsonReimbs = gson.toJson(reimbs);
				ctx.result(jsonReimbs);
				ctx.status(200);
			} else {
				ctx.result("Only Managers may sort by status");
				ctx.status(401);
			}
			
			
		} else {
			ctx.result("You are not logged in");
			ctx.status(401);
		}
		
	};
	
	
	
	
	
	
	
	
	
	
	
	
	

}
