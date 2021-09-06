package com.project.servlets;

import java.io.PrintWriter;

import com.project.main.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		try(PrintWriter out = res.getWriter()){
			HttpSession ses = req.getSession(false);
			ses.removeAttribute("user_id");
			ses.removeAttribute("user_type");
			ses.removeAttribute("user_name");
			
			RequestDispatcher rd = req.getRequestDispatcher("/index.html");
			rd.forward(req, res);
		} catch (Exception e) { //TODO Change exception
			e.printStackTrace();
		}
	}
}