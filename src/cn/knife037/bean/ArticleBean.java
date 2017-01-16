package cn.knife037.bean;

import java.sql.Date;

public class ArticleBean {
	
	private int id;
	
	private String title;
	
	private String cont;
	
	private Date date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCont() {
		return cont;
	}

	public void setCont(String cont) {
		this.cont = cont;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ArticleBean() {
		
	}
	
	public ArticleBean(int id, String title, String cont, Date date) {
		super();
		this.id = id;
		this.title = title;
		this.cont = cont;
		this.date = date;
	}
	
	
}
