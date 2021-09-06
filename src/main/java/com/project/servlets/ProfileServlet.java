package com.project.servlets;

import java.io.PrintWriter;

import com.project.main.User;
import com.project.main.UserDAO;
import com.project.main.UserDAOFactory;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ProfileServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		try(PrintWriter out = res.getWriter()){
			HttpSession ses = req.getSession();
			
			System.out.println("Beginning checks - ProfileServlet line 20");
			if(ses.getAttribute("user_id") == null || ses.getAttribute("user_type") == null || ses.getAttribute("user_name") == null) {
				System.out.println("Check Failed - ProfileServlet line 22");
				RequestDispatcher rd = req.getRequestDispatcher("/logout");
				rd.forward(req, res);
			}
			if(ses.getAttribute("user_type").equals("Manager")) {
				System.out.println("Checked as Manager - ProfileServlet line 27");
				RequestDispatcher rd = req.getRequestDispatcher("/manager.html");
				rd.include(req, res);
			}
			if(ses.getAttribute("user_type").equals("Employee")) {
				System.out.println("Checked as Employee - ProfileServlet line 32");
				RequestDispatcher rd = req.getRequestDispatcher("/employee.html");
				rd.include(req, res);
			}
			
			out.println("<script language='Javascript'>");
			out.println("document.getElementById('header').innerHTML = 'Welcome, ' + '" + ses.getAttribute("user_name") + "'");
			out.println("</script>");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
