package com.ty.task_management.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ty.task_management.dao.TaskDao;
import com.ty.task_management.dao.UserInfoDao;
import com.ty.task_management.dto.Task;
import com.ty.task_management.dto.UserInfo;

@WebServlet("/task")
public class TaskCreation extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String assignTo = req.getParameter("assign");
		String description = req.getParameter("description");
		int id = Integer.parseInt(req.getParameter("userid"));

		UserInfoDao dao = new UserInfoDao();
		UserInfo info = dao.findUserById(id);
		Task task = new Task();
		task.setAssignedTo(assignTo);
		task.setDescription(description);
		TaskDao tdao = new TaskDao();
		List<Task> list = info.getTasks();
		if (list != null) {
			list.add(task);
		} else {
			list.add(task);
		}

		PrintWriter writer = resp.getWriter();

		try {

			if (info != null) {
				tdao.saveUser(task);
				info.setTasks(list);
				dao.updateUser(info);

				writer.print("<html><body><h1>Task Added Succesfully</h1></body></html>");
				RequestDispatcher dispatcher = req.getRequestDispatcher("home.jsp");
				dispatcher.include(req, resp);
			}
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}

	}

}
