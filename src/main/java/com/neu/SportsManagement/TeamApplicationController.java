package com.neu.SportsManagement;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.neu.Dao.PlayerApplicantsDAO;
import com.neu.Pojo.PlayerApplicants;

import javassist.expr.NewArray;



@Controller
public class TeamApplicationController {
	@Autowired
	private ServletContext application;   //only static global instances to be AutoWired
	
	@PostConstruct
	public void init() {
		// initialize global instances
	}

	@PreDestroy
	public void destroy() throws Exception {
		// do cleanup
	}

	@RequestMapping(value ="soccer/apply.htm", method = RequestMethod.GET)
	public String showForm(HttpServletRequest request, ModelMap model) {
		//command object
		//model.addAttribute("applicant", applicant);
		request.setAttribute("action", "save");
		model.addAttribute("applicant", new PlayerApplicants());
		return "teamApplicationForm";
	}
	
	@RequestMapping(value = "soccer/apply.htm", method = RequestMethod.POST)
	public String handleUpload(@ModelAttribute("applicant") PlayerApplicants applicant, PlayerApplicantsDAO applicantDAO) throws IllegalStateException, IOException {
		
		String localPath="C:\\Users\\Sumit\\OneDrive\\Desktop";
		String photoNewName= generateFileName(applicant.getPhoto());
		
		applicant.getPhoto().transferTo(new File(localPath, applicant.getPhoto().getOriginalFilename()));
		
		String resumeNewName= generateFileName(applicant.getResume());
		
		try {
			File file= new File(localPath+resumeNewName);
			FileOutputStream fos= new FileOutputStream(file);
			fos.write(applicant.getResume().getBytes());
			fos.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		applicant.setPhotoPath(localPath+"\\"+photoNewName);
		applicant.setResumePath(localPath+"\\"+resumeNewName);
		applicantDAO.create(applicant);
		try {
			SendEmail(applicant);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "applied-success";
	}
	
	private String generateFileName(MultipartFile multiPartFile) {
		return new Date().getTime()+"-"+multiPartFile.getOriginalFilename().replace(" ","_");
		
	}
	
	private void SendEmail(PlayerApplicants applicant) throws EmailException{
		Email email = new SimpleEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("rickdale84@gmail.com", "rickdale84"));
		email.setSSLOnConnect(true);
		email.setFrom("rickdale84@gmail.com");
		email.setSubject("Registration Successful");
		email.setMsg("Hi "+applicant.getFirst()+"you have successfully registered for Northeastern Team");
		email.addTo(applicant.getEmail());
		email.send();
		
	}
}
