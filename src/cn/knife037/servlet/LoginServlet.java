package cn.knife037.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String username = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
		
		if(username == null || password == null) {
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/login.html");
			view.forward(request, response);
			return ;
		}
		
		Connection conn = DbUtil.getConn();
		String sql = "select * from users where username=?";
		PreparedStatement pstmt = DbUtil.preparedStatement(conn, sql);
		ResultSet rs = null;
		
		try {
			pstmt.setString(1, username);
			rs = DbUtil.executeQuery(pstmt);
			
			if(rs.next()) {
				String pwd = rs.getString("password");
				if(password.equals(pwd)) {
					HttpSession session = request.getSession();
					session.setAttribute("username", username);
					    
					response.sendRedirect("index");
					return ;
				} else {
					response.sendRedirect("login");
					return ;
				}
			} else {
				response.sendRedirect("login");
				return ;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(conn);
			DbUtil.close(pstmt);
			DbUtil.close(rs);
		}
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
