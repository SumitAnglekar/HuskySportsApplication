package com.neu.Pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class PlayerApplicants {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long applicantId;
	
	@Column
	private String first;
	
	@Column
	private String last;
	
	@Column
	private String email;
	
	@Column
	private String nuid;
	
	@Column
	private String playingposition;
	
	@Column
	private String major;
	
	@Column
	private String graduation;
	

	@Column
	private String aboutme;
	
	@Column
	private String skills;
	
	public long getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(long applicantId) {
		this.applicantId = applicantId;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNuid() {
		return nuid;
	}

	public void setNuid(String nuid) {
		this.nuid = nuid;
	}

	public String getGraduation() {
		return graduation;
	}

	public void setGraduation(String graduation) {
		this.graduation = graduation;
	}
	
	public String getPlayingposition() {
		return playingposition;
	}

	public void setPlayingposition(String playingposition) {
		this.playingposition = playingposition;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getAboutme() {
		return aboutme;
	}

	public void setAboutme(String aboutme) {
		this.aboutme = aboutme;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getWhichclass() {
		return whichclass;
	}

	public void setWhichclass(String whichclass) {
		this.whichclass = whichclass;
	}

	public MultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}

	public MultipartFile getResume() {
		return resume;
	}

	public void setResume(MultipartFile resume) {
		this.resume = resume;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getResumePath() {
		return resumePath;
	}

	public void setResumePath(String resumePath) {
		this.resumePath = resumePath;
	}

	public String getSaveorupdate() {
		return saveorupdate;
	}

	public void setSaveorupdate(String saveorupdate) {
		this.saveorupdate = saveorupdate;
	}

	public int getAuthkey1() {
		return authkey1;
	}

	public void setAuthkey1(int authkey1) {
		this.authkey1 = authkey1;
	}

	public int getAuthkey2() {
		return authkey2;
	}

	public void setAuthkey2(int authkey2) {
		this.authkey2 = authkey2;
	}

	@Column
	private String whichclass;
	
	@Transient
	private MultipartFile photo;
	
	@Transient
	private MultipartFile resume;
	
	@Column
	private String photoPath;
	
	@Column
	private String resumePath;
	
	@Column
	private String saveorupdate;
	
	@Column
	private int authkey1;
	
	@Column
	private int authkey2;
}
