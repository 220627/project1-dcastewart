package com.revature.controllers;

import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.revature.daos.ReimbDAO;
import com.revature.models.ReimbDTO;
import com.revature.models.Reimbursement;

import io.javalin.http.Handler;

public class ReimbController {
	
	public static Logger log = LogManager.getLogger();
	
	ReimbDAO rDAO = new ReimbDAO();
	AuthController ac = new AuthController();
	
	
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
			
			log.info("User accessed all reimbursements available to them");
			
			ctx.result(jsonReimbs);
			ctx.status(200);
		} else {
			log.warn("Unauthorized user attempted to get all reimbursements");
			ctx.result("You are not logged in");
			ctx.status(401);
		}
		
	};
	
	
	public Handler createReimbHandler = (ctx) -> {
		
		if(AuthController.ses != null) {
			if(AuthController.currentUser.getRole().getRole_id() == 2) {
				
				String body = ctx.body();
				Gson gson = new Gson();
				
				ReimbDTO r = gson.fromJson(body, ReimbDTO.class);
				
				if(rDAO.insertReimb(r)) {
					ctx.status(202);
					log.info("Employee created a reimbursement request");
				} else {
					log.warn("Employee made an invalid reimbursement request");
					ctx.status(406);
				}
				
			} else {
				log.warn("Manager attempted to create a reimbursement request");
				ctx.result("Only employees may file a reimbursement request");
				ctx.status(401);
			}
		} else {
			log.warn("Unauthorized User attempted to create a reimbursement request");
			ctx.result("You are not logged in");
			ctx.status(401);
		}
		
		
	};
	
	
	public Handler editStatusHandler = (ctx) -> {
		
		if(AuthController.ses != null) {
			if(AuthController.currentUser.getRole().getRole_id() == 1) {
				String body = ctx.body();
				Gson gson = new Gson();
				int newStatus = gson.fromJson(body, Integer.class);
				int id = Integer.valueOf(ctx.pathParam("id"));
				if(rDAO.editStatus(id, newStatus)) {
					ctx.status(202);
					log.info("Manager changed a reimbursement request status");
				} else {
					ctx.result("Status was not updated successfully");
					ctx.status(406);
				}
				
				
			} else {
				log.warn("Employee attempted to change reimbursement request status");
				
				ctx.result("Only managers can change reimbursement requests statuses");
				
				ctx.status(401);
				
			}
		} else {
			log.warn("Unauthorized User attempted to change reimbursement request status");
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
				
				log.info("Manager accessed reimbursements of all employees");
				
				ctx.result(jsonReimbs);
				ctx.status(200);
				
			} else {
				ctx.result("Only Managers may sort by status");
				ctx.status(401);
				log.warn("Employee Attempted to access reimbursements for all employees");
			}
			
			
		} else {
			ctx.result("You are not logged in");
			ctx.status(401);
			log.warn("Unauthorized User attempted to access all reimbursements");
		}
		
	};
	
	
	
	
	
	
	
	
	
	
	
	
	

}
