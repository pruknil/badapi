package com.guanbad.model;

import java.util.Calendar;

public class Team {
	private String name;
	private Calendar starttime;
	private String picture;
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
	
}
