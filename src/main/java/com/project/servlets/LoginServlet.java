package com.project.servlets;

import java.io.PrintWriter;

import com.project.main.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		try(PrintWriter out = res.getWriter()){
			String email = req.getParameter("user_email");
			String password = req.getParameter("user_pass");
			
			//TODO search database for email/password combination, then check user type
			//These are dummy objects, feel free to mess with them
			User employee = new User(1, "Mark", "Johns", "employee@gmail.com", "employee", "employee",  "Employee");
			User admin = new User(2, "Gina", "Hall", "admin@gmail.com", "admin", "admin", "Admin");
			
			//This is dummy code to get a single User object
			//TODO replace this code with something that searches the database for a valid User object
			//This code should check the user type and send them to employee.html if they are an employee or manager.html if they are a manager
			if(!email.equals("") && !password.equals("")) {
				if(email.equals(employee.getEmail()) && password.equals(employee.getHash())) {
					RequestDispatcher rd = req.getRequestDispatcher("/employee.html");
					HttpSession ses = req.getSession();
					ses.setAttribute("user_id", employee.getId());
					ses.setAttribute("user_type", employee.getType());
					rd.forward(req, res);
				} else if(email.equals(admin.getEmail()) && password.equals(admin.getHash())) {
					RequestDispatcher rd = req.getRequestDispatcher("/manager.html");
					HttpSession ses = req.getSession();
					ses.setAttribute("user_id", admin.getId());
					ses.setAttribute("user_type", admin.getType());
					rd.forward(req, res);
				} else {
					out.println("Invalid username/password");
					RequestDispatcher rd = req.getRequestDispatcher("/index.html");
					rd.include(req, res);
				}
			} else {
				out.println("Invalid username/password");
				RequestDispatcher rd = req.getRequestDispatcher("/index.html");
				rd.include(req, res);
			}
		} catch (Exception e) { //TODO Change exception
			e.printStackTrace();
		}
	}
}
