package MyPackageServlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uname = request.getParameter("username");
		String upwd = request.getParameter("password");

		RequestDispatcher dispatcher = null;
		HttpSession session = request.getSession();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

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
			String query = "SELECT * FROM users WHERE name = ? AND password = ?";

			// Create PreparedStatement
			pstm = conn.prepareStatement(query);
			pstm.setString(1, uname);
			pstm.setString(2, upwd);

			rs = pstm.executeQuery();
			if (rs.next()) {
				session.setAttribute("name", rs.getString("name"));

				// Forward to the index page after successful login
				dispatcher = request.getRequestDispatcher("index.jsp");
			} else {
				request.setAttribute("status", "failed");
				dispatcher = request.getRequestDispatcher("login.jsp");
			}
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("status", "failed");
			dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		} finally {
			// Close resources
			try {
				if (rs != null)
					rs.close();
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