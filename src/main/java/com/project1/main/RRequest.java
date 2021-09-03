package com.project1.main;

//Reimbursement Request
public class RRequest {
	int id;
	int user_id;
	double amount;
	String type;
	String status;
	String description;
	
	public RRequest() {}
	
	public RRequest(int id, int user_id, double amount, String type, String status, String description) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.amount = amount;
		this.type = type;
		this.status = status;
		this.description = description;
	}
	
	//There's no setter for id because SQL will generate an ID for you.
	public int getId() {
		return id;
	}
	public int getUserId() {
		return user_id;
	}
	public void setUserId(int user_id) {
		this.user_id = user_id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
