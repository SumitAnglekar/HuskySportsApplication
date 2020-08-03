package com.neu.sportsControllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.neu.dao.PlayerDAO;
import com.neu.exception.ApplyException;
import com.neu.exception.AppPostException;
import com.neu.exception.MessageException;
import com.neu.exception.PlayerException;
import com.neu.filter.XSSFilter;
import com.neu.pojo.Apply;
import com.neu.pojo.Message;
import com.neu.pojo.AppPost;
import com.neu.pojo.Person;
import com.neu.validator.ApplyValidator;

@Controller
@RequestMapping("/player/*")
public class PlayerController {

	@Autowired
	@Qualifier("playerDao")
	PlayerDAO playerDao;
	
	@Autowired
	@Qualifier("applyValidator")
	ApplyValidator validator;
	
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = "/player/", method = RequestMethod.GET)
	protected String goToUserHome(HttpServletRequest request) throws Exception {
		return "player-success";
	}
	
	@RequestMapping(value = "/player/appSearch", method = RequestMethod.GET)
	public ModelAndView listPlayer(HttpServletRequest request) throws Exception {
		//HttpSession session = (HttpSession) request.getSession();
		try {	
			List<AppPost> appPost = playerDao.list();
			//session.setAttribute("appPost", appPost);
			return new ModelAndView("appPostSearchPlayer-Success", "appPost", appPost);
			
		} catch (PlayerException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
		
		
	}
	
	@RequestMapping(value = "/player/apply", method = RequestMethod.GET)
	public ModelAndView apply(HttpServletRequest request) throws Exception {
		System.out.println("apply 1");
		HttpSession session = (HttpSession) request.getSession();
		String id=request.getParameter("ID");
		session.setAttribute("id", id);
		
		String appid=request.getParameter("appID");
		session.setAttribute("appid", appid);
		
		String apptitle=request.getParameter("appTitle");
		session.setAttribute("apptitle", apptitle);
		
		String teamName=request.getParameter("teamName");
		session.setAttribute("teamName", teamName);
		
		String description=request.getParameter("description");
		session.setAttribute("description", description);
		
		String campus=request.getParameter("campus");
		session.setAttribute("campus", campus);
		
		String playingPosition=request.getParameter("playingPosition");
		session.setAttribute("playingPosition", playingPosition);
		
		String noOfPosition=request.getParameter("noOfPosition");
		session.setAttribute("noOfPosition", noOfPosition);
		
		String postedOn=request.getParameter("postedOn");
		session.setAttribute("postedOn", postedOn);

		String type=request.getParameter("type");
		session.setAttribute("type", type);
		
		System.out.println("appID in GET" + appid);
		return new ModelAndView("apply", "apply", new Apply());

		
	}
	
	@RequestMapping(value = "/player/apply", method = RequestMethod.POST)
	public ModelAndView applyApp(HttpServletRequest request, @ModelAttribute("apply") Apply apply, BindingResult result) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		String id=(String)session.getAttribute("id");
		String appid=(String)session.getAttribute("appid");
		String apptitle=(String)session.getAttribute("apptitle");
		String campus=(String)session.getAttribute("campus");
		String teamName=(String)session.getAttribute("teamName");
		String description=(String)session.getAttribute("description");
		String playingPosition=(String)session.getAttribute("playingPosition");
		String noOfPosition=(String)session.getAttribute("noOfPosition");
		String type=(String)session.getAttribute("type");
		String postedOn=(String)session.getAttribute("postedOn");
		
		/*String res=apply.getResume();
		String resume= XSSFilter.removeXSS(res);
		apply.setResume(resume);*/
		
		System.out.println("AppID in post " + id + appid + apptitle + teamName + description + playingPosition + noOfPosition + postedOn);
		Person person=(Person)request.getSession().getAttribute("user");
		session.getAttribute("user");
		
		
		System.out.println("Inside apply post method");
		validator.validate(apply, result);
		
		if (result.hasErrors()) {
			return new ModelAndView("appPostSearchPlayer-Success", "apply", apply);
		}

		try {
			long newid=Long.parseLong(id);
			apply.setAppPostApply(newid);
			apply.setPersonApply(person.getPersonID());
			apply.setFirstName(person.getFirstName());
			apply.setLastName(person.getLastName());
			apply.setTeamName(teamName);
			apply.setDescription(description);
			apply.setPlayingPosition(playingPosition);
			apply.setAppID(appid);
			apply.setAppTitle(apptitle);
			apply.setCampus(campus);
			apply.setNoOfPosition(noOfPosition);
			apply.setPostedOn(postedOn);
			apply.setType(type);
			Apply app = playerDao.applyApp(apply);
			
			request.getSession().setAttribute("apply", app);
			return new ModelAndView("apply-success", "apply", app);
			

		}  catch (AppPostException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while applying");
		}
		
	}
	
	@RequestMapping(value = "/player/searchApplied", method = RequestMethod.GET)
	public ModelAndView listPostedApps(HttpServletRequest request) throws Exception {

		try {			
			Person person=(Person)request.getSession().getAttribute("user");
			System.out.println("PERSONID" + person.getPersonID());
			
			List<Apply> apply = playerDao.listAppiedApps(person);
			System.out.println("hi" + apply.size());
			return new ModelAndView("playerViewApplied", "apply", apply);
			
		} catch (ApplyException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while displaying applied applications");
		}
		
		
	}
	
	@RequestMapping(value = "/player/deleteApplication", method = RequestMethod.GET)
	public ModelAndView deleteApplication(HttpServletRequest request) throws Exception {
		System.out.println("apply 1");
		HttpSession session = (HttpSession) request.getSession();
		String id=request.getParameter("ID");
		session.setAttribute("id", id);
		try {
			
			long newid=Long.parseLong(id);
			
			playerDao.delete(newid);
			return new ModelAndView("deleteApplicationSuccess");
			

		}  catch (ApplyException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while deleting application");
		}
		
	}
	
	
	@RequestMapping(value = "/player/updateApplication", method = RequestMethod.GET)
	public ModelAndView updateApplication(HttpServletRequest request) throws Exception {

		HttpSession session = (HttpSession) request.getSession();
		String id=request.getParameter("ID");
		session.setAttribute("id", id);
		long applyid=Long.parseLong(id);
		Apply apply=playerDao.updateApplication(applyid);
		request.setAttribute("apply", apply);
		return new ModelAndView("applicationUpdate", "apply", apply);
		

		
	}
	
	@RequestMapping(value = "/player/updateApplication", method = RequestMethod.POST)
	public ModelAndView updateExistingApplication(HttpServletRequest request, @ModelAttribute("apply") Apply apply, BindingResult result) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		String id=(String)session.getAttribute("id");
		
		/*String res=apply.getResume();
		String resume= XSSFilter.removeXSS(res);
		apply.setResume(resume);*/
	
		System.out.println("Inside update post method");
		validator.validate(apply, result);
		
		if (result.hasErrors()) {
			return new ModelAndView("playerAppliedSearch", "apply", apply);
		}

		try {
			long newid=Long.parseLong(id);
			Apply app = playerDao.updateExistingApplication(apply, newid);
			
			request.getSession().setAttribute("apply", app);
			return new ModelAndView("updateApplicationSuccess", "apply", app);
			

		}  catch (ApplyException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while applying");
		}

	}
	
	@RequestMapping(value = "/player/message", method = RequestMethod.GET)
	public ModelAndView listEmployer(HttpServletRequest request) throws Exception {

		try {			
			List<Person> person = playerDao.listEmployer();
			return new ModelAndView("listScout", "person", person);
			
		} catch (PlayerException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while displaying employer");
		}
		
		
	}
	
	
	@RequestMapping(value = "/player/messageInbox", method = RequestMethod.GET)
	public ModelAndView listMessageInbox(HttpServletRequest request) throws Exception {

		try {			
			Person person=(Person)request.getSession().getAttribute("user");
			System.out.println("PERSONID" + person.getPersonID());
			
			List<Message> message = playerDao.listMessage(person);
			System.out.println("hi" + message.size());
			return new ModelAndView("messageList", "message", message);
			
		} catch (MessageException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while displaying applied applications");
		}
		
		
	}
	
	
}
