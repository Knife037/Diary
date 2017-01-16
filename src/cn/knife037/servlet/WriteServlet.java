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

import org.markdown4j.Markdown4jProcessor;

public class WriteServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public WriteServlet() {
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

		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String title = request.getParameter("title");
		String cont = request.getParameter("cont");
		
		if(username == null) {
			response.sendRedirect("login");
			return ;
		}
		
		if(title == null || cont == null) {
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/write.jsp");
			view.forward(request, response);
			return ;
		}
		
		Connection conn = DbUtil.getConn();
		String sql = "select * from users where username=?";
		ResultSet rs = null;
		PreparedStatement pstmt = DbUtil.preparedStatement(conn, sql);
		try {
			conn.setAutoCommit(false);
			
			cont = new Markdown4jProcessor().process(cont);
			
			pstmt.setString(1, username);
			rs = DbUtil.executeQuery(pstmt);
			rs.next();
			int userID = rs.getInt("id");
			
			DbUtil.close(rs);
			DbUtil.close(pstmt);
			
			sql = "insert articles(title,date,cont) values(?,?,?)";
			pstmt = DbUtil.preparedStatement(conn, sql);
			pstmt.setString(1, title);
			pstmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
			pstmt.setString(3, cont);
			DbUtil.update(pstmt);
			
			DbUtil.close(rs);
			DbUtil.close(pstmt);
			
			sql = "select last_insert_id()";
			pstmt = DbUtil.preparedStatement(conn, sql);
			rs = DbUtil.executeQuery(pstmt);
			rs.next();
			int articleID = rs.getInt("last_insert_id()");
			
			DbUtil.close(rs);
			DbUtil.close(pstmt);
			
			sql = "insert mapping(userID,articleID) values(?,?)";
			pstmt = DbUtil.preparedStatement(conn, sql);
			pstmt.setInt(1, userID);
			pstmt.setInt(2, articleID);
			DbUtil.update(pstmt);
			
			conn.commit();
			conn.setAutoCommit(true);
			
			response.sendRedirect("index");
			return ;
			
		} catch (SQLException e) {
			e.printStackTrace();
			DbUtil.close(rs);
			DbUtil.close(pstmt);
			DbUtil.close(conn);
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
