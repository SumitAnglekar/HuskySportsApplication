package com.neu.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="apply_table")
public class Apply {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique=true, nullable = false)
	private long ID;
	
	@Column(name ="resume", length=10000)
	private String resume;
	
	@Column(name="person_id")
	private long personApply;
	
	@Column(name="app_id")
	private long appPostApply;
	
	@Column(name = "appID")
	private String appID;
	
	@Column(name ="firstName")
	private String firstName;
	
	@Column(name ="lastName")
	private String lastName;
	
	@Column(name ="appTitle")
	private String appTitle;
	
	@Column(name = "teamName")
	private String teamName;
	
	@Column(name ="campus")
	private String campus;
	
	@Column(name ="type")
	private String type;
	
	@Column(name = "description")
	private String description;
	
	@Column(name ="playingPosition")
	private String playingPosition;
	
	@Column(name = "noOfPosition")
	private String noOfPosition;
	
	@Column(name = "postedOn")
	private String postedOn;

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public long getPersonApply() {
		return personApply;
	}

	public void setPersonApply(long personApply) {
		this.personApply = personApply;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public long getAppPostApply() {
		return appPostApply;
	}

	public void setAppPostApply(long appPostApply) {
		this.appPostApply = appPostApply;
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
