package com.care.root.member.dto;

public class MemberDTO {
	private String id;
	private String pwd;
	private String name;
	private String addr;
	private java.sql.Date limitTime;
	private String sessionId;
	public java.sql.Date getLimitTime() {
		return limitTime;
	}
	public void setLimitTime(java.sql.Date limitTime) {
		this.limitTime = limitTime;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
}
