package com.neu.sportsControllers;

import java.text.SimpleDateFormat;
import java.util.Date;

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

import com.neu.dao.MessageDAO;
import com.neu.exception.MessageException;
import com.neu.filter.XSSFilter;
import com.neu.pojo.Message;
import com.neu.pojo.Person;
import com.neu.validator.MessageValidator;

@Controller
@RequestMapping("/message/*")
public class MessageController {

	@Autowired
	@Qualifier("messageDao")
	MessageDAO messageDao;
	
	@Autowired
	@Qualifier("messageValidator")
	MessageValidator validator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = "/message/messageScout", method = RequestMethod.GET)
	public ModelAndView sendMessage(HttpServletRequest request) throws Exception {

		HttpSession session = (HttpSession) request.getSession();
		String id=request.getParameter("personID");
		session.setAttribute("id", id);
		System.out.println("Message To in get" + id);
		return new ModelAndView("messageScout", "message", new Message());

		
	}
	
	@RequestMapping(value = "/message/messageScout", method = RequestMethod.POST)
	public ModelAndView sendNewMessage(HttpServletRequest request,  @ModelAttribute("message") Message message, BindingResult result) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		String id=(String)session.getAttribute("id");
		System.out.println("Message To " + id);
		
		String messa=message.getMessage();
		String mess= XSSFilter.removeXSS(messa);
		message.setMessage(mess);
		
		validator.validate(message, result);

		if (result.hasErrors()) {
			return new ModelAndView("listScout", "message", message);
		}
		
		try {

			System.out.print("Send New Message");
			Date d = new Date();
	        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	        String postedOnDate = format.format(d);
	        message.setMessageSentOn(postedOnDate);
	        
	        Person person=(Person)request.getSession().getAttribute("user");
	        message.setMessageBy(person.getPersonID());
	        System.out.println("Person name " + person.getFirstName());
			
	        message.setMessageByName(person.getFirstName() + " " + person.getLastName());
	        
	        long messageTo=Long.parseLong(id);
	        
	        message.setMessageTo(messageTo);
	        Message mes = messageDao.createMessage(message);
			
			request.getSession().setAttribute("message", mes);
			return new ModelAndView("messageScoutSuccess", "message", mes);
			

		}  catch (MessageException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while creating new application post");
		}
		
	}
	
	@RequestMapping(value = "/message/messageReplyScout", method = RequestMethod.GET)
	public ModelAndView replyEmployer(HttpServletRequest request) throws Exception {

		HttpSession session = (HttpSession) request.getSession();
		String id=request.getParameter("messageBy");
		session.setAttribute("id", id);
		System.out.println("Message To in get" + id);
		return new ModelAndView("messageScout", "message", new Message());

		
	}
	
	@RequestMapping(value = "/message/messageReplyScout", method = RequestMethod.POST)
	public ModelAndView replyEmployerr(HttpServletRequest request,  @ModelAttribute("message") Message message, BindingResult result) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		String id=(String)session.getAttribute("id");
		System.out.println("Message To " + id);
		
		String messa=message.getMessage();
		String mess= XSSFilter.removeXSS(messa);
		message.setMessage(mess);
		
		validator.validate(message, result);

		if (result.hasErrors()) {
			return new ModelAndView("listScout", "message", message);
		}
		
		try {

			System.out.print("Send New Message");
			Date d = new Date();
	        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	        String postedOnDate = format.format(d);
	        message.setMessageSentOn(postedOnDate);
	        
	        Person person=(Person)request.getSession().getAttribute("user");
	        message.setMessageBy(person.getPersonID());
	        System.out.println("Person name " + person.getFirstName());
			
	        message.setMessageByName(person.getFirstName() + " " + person.getLastName());
	        
	        long messageTo=Long.parseLong(id);
	        
	        message.setMessageTo(messageTo);
	        Message mes = messageDao.createMessage(message);
			
			request.getSession().setAttribute("message", mes);
			return new ModelAndView("messageScoutSuccess", "message", mes);
			

		}  catch (MessageException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while creating new application post");
		}
		
	}
	
	@RequestMapping(value = "/message/messagePlayer", method = RequestMethod.GET)
	public ModelAndView sendEmployeeMessage(HttpServletRequest request) throws Exception {

		HttpSession session = (HttpSession) request.getSession();
		String id=request.getParameter("personID");
		session.setAttribute("id", id);
		
		return new ModelAndView("messagePlayer", "message", new Message());

		
	}
	
	@RequestMapping(value = "/message/messagePlayer", method = RequestMethod.POST)
	public ModelAndView sendEmployeeNewMessage(HttpServletRequest request,  @ModelAttribute("message") Message message, BindingResult result) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		String id=(String)session.getAttribute("id");
		System.out.println("Message To " + id);
		
		String messa=message.getMessage();
		String mess= XSSFilter.removeXSS(messa);
		message.setMessage(mess);
		
		validator.validate(message, result);

		if (result.hasErrors()) {
			return new ModelAndView("player-list", "message", message);
		}
		
		try {

			System.out.print("Send New Message");
			Date d = new Date();
	        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	        String postedOnDate = format.format(d);
	        message.setMessageSentOn(postedOnDate);
	        
	        Person person=(Person)request.getSession().getAttribute("user");
	        message.setMessageBy(person.getPersonID());
	        System.out.println("Person name " + person.getFirstName());
			
	        message.setMessageByName(person.getFirstName() + " " + person.getLastName());
	        
	        long messageTo=Long.parseLong(id);
	        
	        message.setMessageTo(messageTo);
	        Message mes = messageDao.createMessage(message);
			
			request.getSession().setAttribute("message", mes);
			return new ModelAndView("message-success", "message", mes);
			

		}  catch (MessageException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while creating new application post");
		}
		
	}
	
	@RequestMapping(value = "/message/messageReplyPlayer", method = RequestMethod.GET)
	public ModelAndView replyEmployee(HttpServletRequest request) throws Exception {

		HttpSession session = (HttpSession) request.getSession();
		String id=request.getParameter("messageBy");
		session.setAttribute("id", id);
		
		return new ModelAndView("messageReplyPlayer", "message", new Message());

		
	}
	
	@RequestMapping(value = "/message/messageReplyPlayer", method = RequestMethod.POST)
	public ModelAndView replyEmployeer(HttpServletRequest request,  @ModelAttribute("message") Message message, BindingResult result) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		String id=(String)session.getAttribute("id");
		System.out.println("Message To " + id);
		
		String messa=message.getMessage();
		String mess= XSSFilter.removeXSS(messa);
		message.setMessage(mess);
		
		validator.validate(message, result);

		if (result.hasErrors()) {
			return new ModelAndView("player-list", "message", message);
		}
		
		try {

			System.out.print("Send New Message");
			Date d = new Date();
	        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	        String postedOnDate = format.format(d);
	        message.setMessageSentOn(postedOnDate);
	        
	        Person person=(Person)request.getSession().getAttribute("user");
	        message.setMessageBy(person.getPersonID());
	        System.out.println("Person name " + person.getFirstName());
			
	        message.setMessageByName(person.getFirstName() + " " + person.getLastName());
	        
	        long messageTo=Long.parseLong(id);
	        
	        message.setMessageTo(messageTo);
	        Message mes = messageDao.createMessage(message);
			
			request.getSession().setAttribute("message", mes);
			return new ModelAndView("message-success", "message", mes);
			

		}  catch (MessageException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while creating new application post");
		}
		
	}
	
}
