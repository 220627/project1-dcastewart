package com.revature.models;

public class ReimbDTO {
	private String reimb_amt;
	private String reimb_description;
	public String getReimb_amt() {
		return reimb_amt;
	}
	public void setReimb_amt(String reimb_amt) {
		this.reimb_amt = reimb_amt;
	}
	public String getReimb_description() {
		return reimb_description;
	}
	public void setReimb_description(String reimb_description) {
		this.reimb_description = reimb_description;
	}
	public ReimbDTO(String reimb_amt, String reimb_description) {
		super();
		this.reimb_amt = reimb_amt;
		this.reimb_description = reimb_description;
	}
	
	

}
