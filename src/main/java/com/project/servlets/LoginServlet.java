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

public class LoginServlet extends HttpServlet{
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		try(PrintWriter out = res.getWriter()){
			String email = req.getParameter("user_email");
			String password = req.getParameter("user_pass");
			HttpSession ses = req.getSession();
			UserDAO dao = UserDAOFactory.getUserDAO();
			
			if(!email.equals("") && !password.equals("")) {
				User user = dao.loginUser(email, password);
				if(user == null) {
					out.println("<script language='Javascript'>alert('Invalid username/password combination!')</script>");
					RequestDispatcher rd = req.getRequestDispatcher("/index.html");
					rd.include(req, res);
				} else {
					ses = req.getSession();
					ses.setAttribute("user_id", user.getId());
					ses.setAttribute("user_type", user.getType());
					ses.setAttribute("user_name", user.getFirstName());
					
					res.sendRedirect("profile");
				}
			} else {
				out.println("Invalid username/password");
				RequestDispatcher rd = req.getRequestDispatcher("/index.html");
				rd.forward(req, res);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
