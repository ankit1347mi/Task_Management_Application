package com.ty.task_management.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ty.task_management.dao.UserInfoDao;
import com.ty.task_management.dto.UserInfo;

@WebServlet(value = "/deleteuser")
public class DeleteUser extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));

		UserInfoDao dao = new UserInfoDao();
		PrintWriter printWriter = resp.getWriter();
		boolean value = dao.deleteUser(id);
		if (value) {
			printWriter.print("<html><body>");
			printWriter.print("<h1>Deleted Successfully</h1>");
			printWriter.print("</body></html>");

			List<UserInfo> users = dao.findAllUser();
			printWriter.print("<html><body>");
			printWriter.print("<table border=");
			printWriter.print("2px solid" + ">");
			printWriter.print("<tr><th>ID</th><th>UserName</th><th>Email</th><th>Delete</th></tr>");
			for (UserInfo user2 : users) {

				printWriter.print("<tr>");
				printWriter.print("<td>" + user2.getId() + "</td>");
				printWriter.print("<td>" + user2.getName() + "</td>");
				printWriter.print("<td>" + user2.getEmail() + "</td>");
				printWriter.print("<td>" + user2.getRole() + "</td>");
				printWriter.print("<td><a href=deleteuser?id=" + user2.getId() + ">delete</a></td>");
				printWriter.print("</tr>");

			}
			printWriter.print("</table></body></html>");

		} else {
			printWriter.print("<html><body>");
			printWriter.print("<h1>Failed to Deleted</h1>");
			printWriter.print("</body></html>");
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
