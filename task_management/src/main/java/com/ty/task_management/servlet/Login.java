package com.ty.task_management.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ty.task_management.dao.UserInfoDao;
import com.ty.task_management.dto.UserInfo;

@WebServlet(value = "/login")
public class Login extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter("email");
		String password = req.getParameter("password");

		UserInfoDao dao = new UserInfoDao();
		UserInfo info = dao.findUserByEmail(email);
		String role = info.getRole();

		if (email.equals(info.getEmail()) && password.equals(info.getPassword()) && role.equals("Manager")) {
			req.setAttribute("name", info.getName());
			RequestDispatcher dispatcher = req.getRequestDispatcher("home.jsp");
			dispatcher.forward(req, resp);

		} else {
			System.out.println("<html><body><h1>Invalid Credentials</h1></body></html>");
			RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
