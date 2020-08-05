package com.servletproject.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servletproject.impl.UserDaoImpl;
import com.servletproject.pojo.User;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/register")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get data from request object as parameter
		UserDaoImpl userImpl = new UserDaoImpl();
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		String name=req.getParameter("name");
		String dob=req.getParameter("dob");
		
		PrintWriter pw = resp.getWriter();
			
		// put all deata into user object that is required to call register function
		User user = new User();

		user.setUsername(req.getParameter("username"));
		user.setPassword((req.getParameter("password")));
		user.setName(req.getParameter("name"));
		user.setDate(req.getParameter("dob"));

		boolean registerUserStatus = userImpl.register(user);
		resp.setContentType("text/html");
			RequestDispatcher dis1=req.getRequestDispatcher("menu");

		if (registerUserStatus) {

			RequestDispatcher dis = req.getRequestDispatcher("Login.html");
			
			dis1.include(req, resp);
			pw.println("<center><p style='color:green'>User Registerd sucessfully</p></center>" );
			dis.include(req, resp);
			

		} else {
			RequestDispatcher dis = req.getRequestDispatcher("Register.html");

			dis1.include(req, resp);
			pw.println("<center><p style='color:red'>User Registerd Failed</p></center>" );
			dis.include(req, resp);

		}
	} 

}
