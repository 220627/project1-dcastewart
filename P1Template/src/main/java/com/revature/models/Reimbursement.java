package com.revature.models;

import java.sql.Timestamp;

import com.revature.daos.UserDAO;

public class Reimbursement {
	
	private int reimb_id;
	private double reimb_amt;
	private Timestamp reimb_submitted;
	private User reimb_author;
	private ReimbStatus reimbStatus;
	private ReimbType reimbType;
	private String reimbDescription;
	private UserDAO uDAO = new UserDAO();
	
	public Reimbursement(int reimb_id, double reimb_amt, Timestamp reimb_submitted, int reimb_author,
			int reimbStatus, int reimbType, String reimbDescription) {
		super();
		this.reimb_id = reimb_id;
		this.reimb_amt = reimb_amt;
		this.reimb_submitted = reimb_submitted;
		this.reimb_author = uDAO.getUserByID(reimb_author);
		this.reimbStatus = new ReimbStatus(reimbStatus);
		this.reimbType = new ReimbType(reimbType);
		this.reimbDescription = reimbDescription;
	}
	
	public Reimbursement(double reimb_amt, Timestamp reimb_submitted, int reimb_author,
			int reimbStatus, int reimbType, String reimbDescription) {
		super();
		this.reimb_amt = reimb_amt;
		this.reimb_submitted = reimb_submitted;
		this.reimb_author = uDAO.getUserByID(reimb_author);
		this.reimbStatus = new ReimbStatus(reimbStatus);
		this.reimbType = new ReimbType(reimbType);
		this.reimbDescription = reimbDescription;
	}
	
	public Reimbursement(int reimb_id, double reimb_amt, int reimb_author,
			int reimbStatus, int reimbType, String reimbDescription) {
		super();
		this.reimb_id = reimb_id;
		this.reimb_amt = reimb_amt;
		this.reimb_submitted = new Timestamp(System.currentTimeMillis());
		this.reimb_author = uDAO.getUserByID(reimb_author);
		this.reimbStatus = new ReimbStatus(reimbStatus);
		this.reimbType = new ReimbType(reimbType);
		this.reimbDescription = reimbDescription;
	}
	
	public Reimbursement(double reimb_amt, int reimb_author,
			int reimbStatus, int reimbType, String reimbDescription) {
		super();
		this.reimb_amt = reimb_amt;
		this.reimb_submitted = new Timestamp(System.currentTimeMillis());
		this.reimb_author = uDAO.getUserByID(reimb_author);
		this.reimbStatus = new ReimbStatus(reimbStatus);
		this.reimbType = new ReimbType(reimbType);
		this.reimbDescription = reimbDescription;
	}
	
	public Reimbursement(int reimb_id, double reimb_amt, Timestamp reimb_submitted, User reimb_author,
			ReimbStatus reimbStatus, ReimbType reimbType, String reimbDescription) {
		super();
		this.reimb_id = reimb_id;
		this.reimb_amt = reimb_amt;
		this.reimb_submitted = reimb_submitted;
		this.reimb_author = reimb_author;
		this.reimbStatus = reimbStatus;
		this.reimbType = reimbType;
		this.reimbDescription = reimbDescription;
	}

	public int getReimb_id() {
		return reimb_id;
	}

	public void setReimb_id(int reimb_id) {
		this.reimb_id = reimb_id;
	}

	public double getReimb_amt() {
		return reimb_amt;
	}

	public void setReimb_amt(double reimb_amt) {
		this.reimb_amt = reimb_amt;
	}

	public Timestamp getReimb_submitted() {
		return reimb_submitted;
	}

	public void setReimb_submitted(Timestamp reimb_submitted) {
		this.reimb_submitted = reimb_submitted;
	}

	public User getReimb_author() {
		return reimb_author;
	}

	public void setReimb_author(User reimb_author) {
		this.reimb_author = reimb_author;
	}

	public ReimbStatus getReimbStatus() {
		return reimbStatus;
	}

	public void setReimbStatus(ReimbStatus reimbStatus) {
		this.reimbStatus = reimbStatus;
	}

	public ReimbType getReimbType() {
		return reimbType;
	}

	public void setReimbType(ReimbType reimbType) {
		this.reimbType = reimbType;
	}
	
	

	public String getReimbDescription() {
		return reimbDescription;
	}

	public void setReimbDescription(String reimbDescription) {
		this.reimbDescription = reimbDescription;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimb_id=" + reimb_id + ", reimb_amt=" + reimb_amt + ", reimb_submitted="
				+ reimb_submitted + ", reimb_author=" + reimb_author + ", reimbStatus=" + reimbStatus + ", reimbType="
				+ reimbType + ", reimbDescription=" + reimbDescription + "]";
	}

	
	
	
	
	
	
	

}
