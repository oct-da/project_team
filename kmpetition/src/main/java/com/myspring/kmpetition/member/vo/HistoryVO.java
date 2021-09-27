package com.myspring.kmpetition.member.vo;

import java.sql.Timestamp;
import java.sql.Date;

public class HistoryVO {
	private String id;
	private Timestamp viewDate;
	private int no;
	
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
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}	
}
