package com.example.lowbus.util;



public class user_Info{
	private String name;  // ?�름
	private String time;  // ?�름
	private boolean check=false;
	
	public String getName() {
		return name;
	}
	public void setName(String subject) {
		this.name = subject;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String subject) {
		this.time = subject;
	}
	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}
	public user_Info() {
		super();
		// TODO Auto-generated constructor stub
	}
	public user_Info(String name, String time, boolean check) {
		super();
		this.name=name;
		this.time=time;
		this.check=check;
	}
	
	
}
