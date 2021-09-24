package com.myspring.kmpetition.myPage.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("myPageVO")
public class MyPageVO {
	private Date viewdate;
	private String title;
	private int no;
	private String rawdata;

	public Date getViewdate() {
		return viewdate;
	}

	public void setViewdate(Date viewdate) {
		this.viewdate = viewdate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getRawdata() {
		return rawdata;
	}

	public void setRawdata(String rawdata) {
		this.rawdata = rawdata;
	}

}
