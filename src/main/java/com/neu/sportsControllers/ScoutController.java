package com.neu.sportsControllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.dao.ScoutDAO;
import com.neu.dao.DAO;
import com.neu.dao.UserDAO;
import com.neu.exception.ApplyException;
import com.neu.exception.ScoutException;
import com.neu.exception.AppPostException;
import com.neu.exception.MessageException;
import com.neu.exception.UserException;
import com.neu.filter.XSSFilter;
import com.neu.pojo.Apply;
import com.neu.pojo.AppPost;
import com.neu.pojo.Message;
import com.neu.pojo.Person;
import com.neu.pojo.User;
import com.neu.validator.AppPostValidator;
import com.neu.validator.UserValidator;

@Controller
@RequestMapping("/scout/*")
public class ScoutController extends DAO{
	
	@Autowired
	@Qualifier("employerDao")
	ScoutDAO employerDao;
	
	@Autowired
	@Qualifier("appPostValidator")
	AppPostValidator validator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = "/scout/", method = RequestMethod.GET)
	protected String goToUserHome(HttpServletRequest request) throws Exception {
		return "scout-success";
	}
	
	@RequestMapping(value = "/scout/search", method = RequestMethod.GET)
	public ModelAndView listEmployee(HttpServletRequest request) throws Exception {

		try {			
			List<Person> person = employerDao.list();
			return new ModelAndView("player-list", "person", person);
			
		} catch (ScoutException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
		
		
	}
	
	@RequestMapping(value = "/scout/searchAppPost", method = RequestMethod.GET)
	public ModelAndView listPostedApps(HttpServletRequest request) throws Exception {

		try {			
			System.out.println("Inside app post search controller");
			Person person=(Person)request.getSession().getAttribute("user");
			//System.out.println("PERSONID" + person.getPersonID());
			List<AppPost> appPost = employerDao.listAppPost(person);
			//System.out.println("hi" + Post.size());
			return new ModelAndView("appPostSearch-success", "appPost", appPost);
			
		} catch (AppPostException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while displaying  post list");
		}
		
		
	}
	
	@RequestMapping(value = "/scout/appPost", method = RequestMethod.GET)
	public ModelAndView addAppPost(HttpServletRequest request) throws Exception {

		//System.out.println("registerUser 1");
		return new ModelAndView("app-post", "appPost", new AppPost());

		
	}
	
	@RequestMapping(value = "/scout/appPost", method = RequestMethod.POST)
	public ModelAndView addAppNewPost(HttpServletRequest request,  @ModelAttribute("Post") AppPost appPost, BindingResult result) throws Exception {

		String appid=appPost.getAppID();
		String appID= XSSFilter.removeXSS(appid);
		appPost.setAppID(appID);
		
		String apptitle=appPost.getAppTitle();
		String appTitle= XSSFilter.removeXSS(apptitle);
		appPost.setAppTitle(appTitle);
		
		String teamName=appPost.getTeamName();
		String companyName= XSSFilter.removeXSS(teamName);
		appPost.setTeamName(teamName);;
		
		String desc=appPost.getDescription();
		String description= XSSFilter.removeXSS(desc);
		appPost.setDescription(description);
		
		String playingPos=appPost.getPlayingPosition();
		String playingPosition= XSSFilter.removeXSS(playingPos);
		appPost.setPlayingPosition(playingPosition);
		
		String loc=appPost.getCampus();
		String location= XSSFilter.removeXSS(loc);
		appPost.setCampus(location);;
		
		String pos=appPost.getNoOfPosition();
		String noOfPosition= XSSFilter.removeXSS(pos);
		appPost.setNoOfPosition(noOfPosition);
		
		//System.out.print("Inside apppost post method");
		validator.validate(appPost, result);

		if (result.hasErrors()) {
			return new ModelAndView("app-post", "appPost", appPost);
		}

		try {

			System.out.print("Add new  post");
			Date d = new Date();
	        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	        String postedOnDate = format.format(d);
	        appPost.setPostedOn(postedOnDate);
	        Person person=(Person)request.getSession().getAttribute("user");
	        appPost.setPerson(person);
	        //System.out.println("Person name " + person.getFirstName());
			AppPost jp = employerDao.create(appPost);
			
			request.getSession().setAttribute("Post", jp);
			return new ModelAndView("appPost-success", "appPost", jp);
			

		}  catch (AppPostException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while creating new  post");
		}
		
	}
	
	@RequestMapping(value = "/scout/deletePostedApp", method = RequestMethod.GET)
	public ModelAndView deletePostedApp(HttpServletRequest request) throws Exception {
		System.out.println("apply 1");
		HttpSession session = (HttpSession) request.getSession();
		String id=request.getParameter("ID");
		session.setAttribute("id", id);
		//System.out.println("Inside employee Controller get method-->" + id);
		try {
			
			System.out.println("Inside player Controller post method----" + id);
            
			long newid=Long.parseLong(id);
			
			employerDao.delete(newid);
			return new ModelAndView("delete-success");
			

		}  catch (AppPostException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while applying");
		}
		
	}
	
	@RequestMapping(value = "/scout/updatePostedApps", method = RequestMethod.GET)
	public ModelAndView updateAppPost(HttpServletRequest request) throws Exception {

		HttpSession session = (HttpSession) request.getSession();
		String id=request.getParameter("ID");
		session.setAttribute("id", id);
		long appid=Long.parseLong(id);
		AppPost appPost=employerDao.updatePosted(appid);
		request.setAttribute("appPost", appPost);
		System.out.println("Update app post" + appPost.getTeamName());
		return new ModelAndView("updatePostedApp", "appPost", appPost);
		

		
	}
	
	@RequestMapping(value = "/scout/updatePostedApps", method = RequestMethod.POST)
	public ModelAndView updateExistingAppPost(HttpServletRequest request, @ModelAttribute("Post") AppPost appPost, BindingResult result) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		String id=(String)session.getAttribute("id");
		
		String appid=appPost.getAppID();
		String appID= XSSFilter.removeXSS(appid);
		appPost.setAppID(appID);
		
		String apptitle=appPost.getAppTitle();
		String appTitle= XSSFilter.removeXSS(apptitle);
		appPost.setAppTitle(appTitle);
		
		String teamName=appPost.getTeamName();
		String teamname= XSSFilter.removeXSS(teamName);
		appPost.setTeamName(teamname);
		
		String desc=appPost.getDescription();
		String description= XSSFilter.removeXSS(desc);
		appPost.setDescription(description);
		
		String durat=appPost.getPlayingPosition();
		String playingPosition= XSSFilter.removeXSS(durat);
		appPost.setPlayingPosition(playingPosition);
		
		String loc=appPost.getCampus();
		String campus= XSSFilter.removeXSS(loc);
		appPost.setCampus(campus);
		
		String pos=appPost.getNoOfPosition();
		String noOfPosition= XSSFilter.removeXSS(pos);
		appPost.setNoOfPosition(noOfPosition);
	
		System.out.println("Inside update post method");
		validator.validate(appPost, result);
		
		if (result.hasErrors()) {
			return new ModelAndView("appPostSearch-success", "appPost", appPost);
		}

		try {
			long newid=Long.parseLong(id);
			AppPost jp = employerDao.updateExistingAppPost(appPost, newid);
			
			request.getSession().setAttribute("appPost", jp);
			return new ModelAndView("updateAppPost-success", "appPost", jp);
			

		}  catch (AppPostException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while applying");
		}

	}
	
	@RequestMapping(value = "/scout/viewApplied", method = RequestMethod.GET)
	public ModelAndView listEmployeeApplied(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		String id=request.getParameter("ID");
		session.setAttribute("id", id);
		long appid=Long.parseLong(id);
		
		try {			
			//System.out.println("Inside app post search controller");
			
			List<Apply> apply = employerDao.listEmployeeApplied(appid);
			//System.out.println("hi" + appPost.size());
			return new ModelAndView("playerAppliedSearch", "apply", apply);
			
		} catch (ApplyException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while displaying app post list");
		}
		
		
	}
	
	
	@RequestMapping(value = "/scout/messageInbox", method = RequestMethod.GET)
	public ModelAndView listMessageInbox(HttpServletRequest request) throws Exception {

		try {			
			Person person=(Person)request.getSession().getAttribute("user");
			System.out.println("PERSONID" + person.getPersonID());
			
			List<Message> message = employerDao.listMessage(person);
			System.out.println("hi message size is-->" + message.size());
			return new ModelAndView("messageListScout", "message", message);
			
		} catch (MessageException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while displaying applied apps");
		}
		
		
	}
	
	
}
