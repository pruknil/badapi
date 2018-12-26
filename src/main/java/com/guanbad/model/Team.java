package com.guanbad.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mysema.query.annotations.QueryEntity;
@QueryEntity
@Document
public class Team implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5589204835158316656L;
	@Id
	public ObjectId _id;
	private String name;
	private String contactNo;
	private String description;
	private String lineId;
	private String facebook;
	private Calendar starttime;
	private String picture;
	private String thbUrl;
	private LocalDate thbLastUpd;
	private List<ActiveDay> activeDay;
	
	
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

	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLineId() {
		return lineId;
	}
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	public String getFacebook() {
		return facebook;
	}
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	public String getThbUrl() {
		return thbUrl;
	}
	public void setThbUrl(String thbUrl) {
		this.thbUrl = thbUrl;
	}
	public LocalDate getThbLastUpd() {
		return thbLastUpd;
	}
	public void setThbLastUpd(LocalDate thbLastUpd) {
		this.thbLastUpd = thbLastUpd;
	}
	public List<ActiveDay> getActiveDay() {
		return activeDay;
	}
	public void setActiveDay(List<ActiveDay> activeDay) {
		this.activeDay = activeDay;
	}

	
}
