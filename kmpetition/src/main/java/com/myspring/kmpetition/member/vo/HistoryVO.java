package com.myspring.kmpetition.member.vo;

import java.sql.Timestamp;
import java.sql.Date;

public class HistoryVO {
	private String id;
	private Timestamp viewDate;
	private String url;
	private String title;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Timestamp getViewDate() {
		return viewDate;
	}
	public void setViewDate(Timestamp viewDate) {
		this.viewDate = viewDate;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
