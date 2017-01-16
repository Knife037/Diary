package cn.knife037.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.knife037.bean.ArticleBean;

public class ArticleServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ArticleServlet() {
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

		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		if(username == null) {
			response.sendRedirect("login");
			return ;
		}
		
		Connection conn = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DbUtil.getConn();
			String sql = "select * from users where username=?";
			pstmt = DbUtil.preparedStatement(conn, sql);
			pstmt.setString(1, username);
			rs = DbUtil.executeQuery(pstmt);
			rs.next();
			
			int userID = rs.getInt("id");
			DbUtil.close(rs);
			DbUtil.close(pstmt);
			
			sql = "select * from mapping where userID=?";
			pstmt = DbUtil.preparedStatement(conn, sql);
			pstmt.setInt(1, userID);
			rs = DbUtil.executeQuery(pstmt);
			
			ArrayList<ArticleBean> articles = new ArrayList<ArticleBean>();
			while(rs.next()) {
				int articleID = rs.getInt("articleID");
				
				int id = 0;
				String title = null;
				String cont = null;
				Date date = null;
				
				PreparedStatement pstmt0 = null;
				ResultSet rs0 = null;
				try {
					String sql0 = "select * from articles where id=?";
					pstmt0 = DbUtil.preparedStatement(conn, sql0);
					pstmt0.setInt(1, articleID);
					
					rs0 = DbUtil.executeQuery(pstmt0);
					rs0.next();
					
					id = rs0.getInt("id");
					title = new String(rs0.getString("title"));
					cont = rs0.getString("cont");
					cont = cont.substring(0, 100 > cont.length() ? cont.length() : 100) + "......";
					date = rs0.getDate("date");
					articles.add(new ArticleBean(id, title, cont, date));
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			//System.out.println(articles.size());
			request.setAttribute("articles", articles);
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/article.jsp");
			view.forward(request, response);
			
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
