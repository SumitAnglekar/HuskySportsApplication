package com.neu.pojo;

import java.sql.Date;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="apppost_table")
public class AppPost {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique=true, nullable = false)
	private long ID;
	
	@Column(name = "appID")
	private String appID;
	
	@Column(name ="appTitle")
	private String appTitle;
	
	@Column(name = "teamName")
	private String teamName;
	
	@Column(name ="campus")
	private String campus;
	
	@Column(name ="type")
	private String type="fullTime";
	
	@Column(name = "description")
	private String description;
	
	@Column(name ="playingPosition")
	private String playingPosition;
	
	@Column(name = "noOfPosition")
	private String noOfPosition;
	
	@Column(name = "postedOn")
	private String postedOn;

	@ManyToOne
	@JoinColumn(name="person_id")
	private Person person;
	
	

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNoOfPosition() {
		return noOfPosition;
	}

	public void setNoOfPosition(String noOfPosition) {
		this.noOfPosition = noOfPosition;
	}

	public String getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(String postedOn) {
		this.postedOn = postedOn;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public String getPlayingPosition() {
		return playingPosition;
	}

	public void setPlayingPosition(String playingPosition) {
		this.playingPosition = playingPosition;
	}

	public String getAppID() {
		return appID;
	}

	public void setAppID(String appID) {
		this.appID = appID;
	}

	public String getAppTitle() {
		return appTitle;
	}

	public void setAppTitle(String appTitle) {
		this.appTitle = appTitle;
	}
		
	
}
