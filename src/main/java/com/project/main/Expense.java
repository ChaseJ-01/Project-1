package com.project.main;

public class Expense {
	private int requestID;
	private int employeeID;
    private double requestAmount;
    private String requestType;
    private String requestStatus;
    private String requestDescription;
    private String requestTimestamp;

    public Expense(){

    }

    public Expense(int id, int employeeId, double amount, String type, String status, String description, String timestamp) {
        this.requestID = id;
        this.employeeID = employeeId;
        this.requestAmount = amount;
        this.requestType = type;
        this.requestStatus = status;
        this.requestDescription = description;
        this.requestTimestamp = timestamp;
    }

    public int getRequestId() {
        return requestID;
    }

    public double getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(double amount) {
        this.requestAmount = amount;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String type) {
        this.requestType = type;
    }
    
    public String getRequestStatus() {
    	return this.requestStatus;
    }
    
    public void setRequestStatus(String status) {
    	this.requestStatus = status;
    }
    
    public String getRequestDescription() {
    	return this.requestDescription;
    }
    
    public void setRequestDescription(String description) {
    	this.requestDescription = description;
    }

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getRequestTimestamp() {
		return requestTimestamp;
	}

	public void setRequestTimestamp(String requestTimestamp) {
		this.requestTimestamp = requestTimestamp;
	}
    
}
