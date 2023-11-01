package com.ty.task_management.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ty.task_management.dao.UserInfoDao;
import com.ty.task_management.dto.UserInfo;

@WebServlet(value = "/data")
public class Data extends HttpServlet {
	
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		long phone = Long.parseLong(req.getParameter("phone"));
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		UserInfo info = new UserInfo();
		info.setEmail(email);
		info.setName(name);
		info.setPassword(password);
		info.setPhone(phone);
		
		

		UserInfoDao dao = new UserInfoDao();
		try {
			dao.saveUser(info);
			RequestDispatcher dispatcher = req.getRequestDispatcher("first.jsp");
			dispatcher.forward(req, resp);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}

	}
}
