package MyPackageServlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

//@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uname = request.getParameter("name");
		String uemail = request.getParameter("email");
		String upwd = request.getParameter("pass");
		String umobile = request.getParameter("contact");

		RequestDispatcher dispatcher = null;
		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Load the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Database connection details
			String url = "jdbc:mysql://localhost:3306/loginAndRegister2";
			String dbUsername = "root";
			String dbPassword = "9828807288";

			// Establish connection
			conn = DriverManager.getConnection(url, dbUsername, dbPassword);

			// SQL query with column names
			String query = "INSERT INTO users (name, email, password, contact) VALUES (?, ?, ?, ?)";

			// Create PreparedStatement
			pstm = conn.prepareStatement(query);
			pstm.setString(1, uname);
			pstm.setString(2, uemail);
			pstm.setString(3, upwd); // Consider hashing the password before storing it
			pstm.setString(4, umobile);

			// Execute update
			int rowCount = pstm.executeUpdate();

			// Set status attribute
			if (rowCount > 0) {
				request.setAttribute("status", "success");
			} else {
				request.setAttribute("status", "failed");
			}

			// Forward to the registration page to show status
			dispatcher = request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
			System.out.println("Data Inserted successfully!");

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("status", "failed");
			request.setAttribute("message", "An error occurred: " + e.getMessage());
			// Forward to registration page with error message
			dispatcher = request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
		} finally {
			// Close resources
			try {
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}