package com.project.main;

public class Expense {
	private int requestID;
    private double requestAmount;
    private int requestType;
    private boolean requestStatus;
    private String requestDescription;

    public Expense(){

    }

    public Expense(int id, double amount, int type, boolean status, String description) {
        this.requestID = id;
        this.requestAmount = amount;
        this.requestType = type;
        this.requestStatus = status;
        this.requestDescription = description;
    }

    public int getRequestId() {
        return requestID;
    }

    /*public void setId(int id) {
        this.requestID = id;
    }*/

    public double getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(double amount) {
        this.requestAmount = amount;
    }

    public int getRequestType() {
        return requestType;
    }

    public void setRequestType(int type) {
        this.requestType = type;
    }
    
    public boolean getRequestStatus() {
    	return this.requestStatus;
    }
    
    public void setRequestStatus(boolean status) {
    	this.requestStatus = status;
    }
    
    public String getRequestDescription() {
    	return this.requestDescription;
    }
    
    public void setRequestDescription(String description) {
    	this.requestDescription = description;
    }
    
}
