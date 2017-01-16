package cn.knife037.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.knife037.bean.ArticleBean;

public class ViewArticleServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ViewArticleServlet() {
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
		int articleID = Integer.parseInt(request.getParameter("id"));
		
		if(username == null || articleID == 0) {
			response.sendRedirect("login");
			return ;
		}
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DbUtil.getConn();
			String sql = "select * from articles where id=?";
			pstmt = DbUtil.preparedStatement(conn, sql);
			pstmt.setInt(1, articleID);
			rs = DbUtil.executeQuery(pstmt);
			rs.next();
			String title = rs.getString("title");
			String cont = transformHTML(rs.getString("cont"));
			Date date = rs.getDate("date");
			ArticleBean article = new ArticleBean(articleID, title, cont, date);
			
			request.setAttribute("article", article);
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/viewArticle.jsp");
			view.forward(request, response);
			
		} catch(SQLException e) {
			DbUtil.close(conn);
			DbUtil.close(pstmt);
			DbUtil.close(rs);
		}
		
		
	}

	private String transformHTML(String txt) {
		String html = txt;
		html = "<p>" + txt.replace("\r\n", "</p><br>\r\n<p>");
		html = html.replace(" ", "&nbsp;");
		return html;
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
