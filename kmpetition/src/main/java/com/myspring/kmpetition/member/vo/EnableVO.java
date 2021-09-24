package com.myspring.kmpetition.member.vo;

import java.sql.Timestamp;
import java.sql.Date;

public class EnableVO {
	private String id;
	private int failcount;
	private Timestamp time;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getFailCount() {
		return failcount;
	}
	public void setFailCount(int failcount) {
		this.failcount = failcount;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	
}
