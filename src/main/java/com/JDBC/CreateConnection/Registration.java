package com.JDBC.CreateConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Registration() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("user_name");
		String pass_word = request.getParameter("user_password");
		String email = request.getParameter("user_email");
		String url = "jdbc:mysql://localhost:3306/Registration";
		String userId = "root";
		String password = "Lucifer@23";

		// now we jave to establish connection with dataBase
		try {
			// Step 1: Register driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Step 2: Get Connection Object
			Connection con = DriverManager.getConnection(url, userId, password);
			
			// STEP 3: Create Statement Object
			PreparedStatement ps = con.prepareStatement("insert into register values(?,?,?)");
			ps.setString(1, name);
			ps.setString(2, pass_word);
			ps.setString(3, email);
			int i =ps.executeUpdate();
			
			if(i>0) {
				out.println("You have successfuly registered");
			}
			else {
				out.println("Error");
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
