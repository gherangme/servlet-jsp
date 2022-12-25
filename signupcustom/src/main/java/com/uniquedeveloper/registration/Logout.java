package com.uniquedeveloper.registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		
		
		Time lastTime = new Time(session.getLastAccessedTime());
		System.out.println("Last Date: "+lastTime);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/custom?useSSL=false","root","");
			PreparedStatement pst = con.prepareStatement("update users set lasttime=? where uemail=?");
				pst.setTime(1, lastTime);
				pst.setString(2,(String) session.getAttribute("name"));
				pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		System.out.println(session.getAttribute("name"));
		session.invalidate();
		response.sendRedirect("login.jsp");
	}
}
