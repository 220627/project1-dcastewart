package com.revature.models;

public class ReimbType {
	
	private int reimb_type_id;
	private String reimb_type;
	
	public ReimbType(int type_id) {
		reimb_type_id = type_id;
		switch (type_id) {
		case 1:
			reimb_type = "Lodging";
			break;
		case 2:
			reimb_type = "Travel";
			break;
		case 3:
			reimb_type = "Food";
			break;
		case 4:
			reimb_type = "Other";
			break;
		}
	}

	public ReimbType(int reimb_type_id, String reimb_type) {
		super();
		this.reimb_type_id = reimb_type_id;
		this.reimb_type = reimb_type;
	}

	public int getReimb_type_id() {
		return reimb_type_id;
	}

	public void setReimb_type_id(int reimb_type_id) {
		this.reimb_type_id = reimb_type_id;
	}

	public String getReimb_type() {
		return reimb_type;
	}

	public void setReimb_type(String reimb_type) {
		this.reimb_type = reimb_type;
	}

	@Override
	public String toString() {
		return "ReimbType [reimb_type_id=" + reimb_type_id + ", reimb_type=" + reimb_type + "]";
	}
	
	

}
