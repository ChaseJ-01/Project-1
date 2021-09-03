package com.project1.main;

public class User {
	int id;
	String first_name;
	String last_name;
	String email;
	String hash;
	String salt;
	String type;
	
	public User() {}
	
	public User(int id, String first_name, String last_name, String email, String hash, String salt, String type) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.hash = hash;
		this.salt = salt;
		this.type = type;
	}
	
	//There's no setter for id because SQL will generate one for you.
	public int getId() {
		return id;
	}
	public String getFirstName() {
		return first_name;
	}
	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}
	public String getLastName() {
		return last_name;
	}
	public void setLastName(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	//Returns full name
	public String getName() {
		return first_name + " " + last_name;
	}
	
	
}
