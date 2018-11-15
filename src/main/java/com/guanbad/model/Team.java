package com.guanbad.model;

import java.util.Calendar;

public class Team {
	private String name;
	private Calendar starttime;
	private String picture;
	private Court court;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Calendar getStarttime() {
		return starttime;
	}
	public void setStarttime(Calendar starttime) {
		this.starttime = starttime;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Court getCourt() {
		return court;
	}
	public void setCourt(Court court) {
		this.court = court;
	}
	
}
