package com.guanbad.model;

import java.io.Serializable;
import java.util.Calendar;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mysema.query.annotations.QueryEntity;
@QueryEntity
@Document
public class Team implements Serializable {
	@Id
	public ObjectId _id;
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
	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	
}
