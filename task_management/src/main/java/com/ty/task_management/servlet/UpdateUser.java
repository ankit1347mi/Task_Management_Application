package com.ty.task_management.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ty.task_management.dao.TaskDao;
import com.ty.task_management.dao.UserInfoDao;
import com.ty.task_management.dto.UserInfo;

@WebServlet(value = "/update")
public class UpdateUser extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		int userId = Integer.parseInt(req.getParameter("userId"));

		TaskDao dao = new TaskDao();
		dao.updateUser(id, "Completed");
		
		UserInfoDao userDao = new UserInfoDao();
		UserInfo user = userDao.findUserById(userId);
		
		req.setAttribute("user", user);
		RequestDispatcher dispatcher = req.getRequestDispatcher("welcome.jsp");
		dispatcher.forward(req, resp);
	}
}
