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

@WebServlet(value = "/ulogin")
public class UserLogin extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		UserInfoDao infoDao = new UserInfoDao();
		UserInfo info = infoDao.findUserByEmail(email);
		req.setAttribute("user", info);
		String role = info.getRole();
		PrintWriter printWriter = resp.getWriter();
		printWriter.print("Logged in Suucessfully");
		if (email.equals(info.getEmail()) && password.equals(info.getPassword()) && role.equals("Employee")) {
			req.setAttribute("name", info.getName());
			RequestDispatcher dispatcher = req.getRequestDispatcher("welcome.jsp");
			dispatcher.forward(req, resp);

		} else {
			printWriter.print("Invalid Credentials");
			RequestDispatcher dispatcher = req.getRequestDispatcher("ulogin.jsp");
			dispatcher.include(req, resp);
		}
	}
}
