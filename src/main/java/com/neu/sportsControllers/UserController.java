package com.neu.sportsControllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.dao.UserDAO;
import com.neu.exception.UserException;
import com.neu.pojo.User;
import com.neu.validator.UserValidator;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/user/*")
public class UserController {

	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;

	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;
	
	@RequestMapping(value = "/user/access", method = RequestMethod.GET)
    protected String denyAccess(HttpServletRequest request) throws Exception {
        return "access";
    }

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	protected String goToUserHome(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		return "user-success";
	}
	
	@RequestMapping(value="/user/logout", method = RequestMethod.GET)
    protected String logOut(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        session.invalidate();
        System.out.println("Log Out Successful");
        return "logout";
    }
	
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	protected String loginUser(HttpServletRequest request) throws Exception {

		HttpSession session = (HttpSession) request.getSession();
		
		try {

			//System.out.print("loginUser");

			User u = userDao.get(request.getParameter("username"), request.getParameter("password"));
			
			if(u == null){
				System.out.println("UserName/Password does not exist");
				session.setAttribute("errorMessage", "UserName/Password does not exist");
				return "error";
			}
			
			session.setAttribute("user", u);
			
			System.out.println(u.getRole());
			if(u.getRole().equalsIgnoreCase("scout")){
			return "scout-success";
			}
			else{
				return "player-success";
			}

		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			session.setAttribute("errorMessage", "error while login");
			return "error";
		}

	}
	
	@RequestMapping(value = "/user/register", method = RequestMethod.GET)
	protected ModelAndView registerUser() throws Exception {
		//System.out.println("registerUser");

		return new ModelAndView("register", "user", new User());

	}
	
	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	protected ModelAndView registerNewUser(HttpServletRequest request,  @ModelAttribute("user") User user, BindingResult result) throws Exception {
		//System.out.print("registerNewUser1");
		validator.validate(user, result);

		if (result.hasErrors()) {
			return new ModelAndView("register", "user", user);
		}

		try {

			//System.out.print("registerNewUser");

			User u = userDao.register(user);
			
			request.getSession().setAttribute("user", u);
			try {
				SendEmail(user);
			}catch(Exception e) {
				e.printStackTrace();
			}
			//System.out.println(u.getRole());
			if(u.getRole().equalsIgnoreCase("scout")){
				
			return new ModelAndView("scout-success", "user", u);
			
			}
			else{
				return new ModelAndView("player-success", "user", u);
			}

		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
		
	}

	private void SendEmail(User user) throws EmailException{
		Email email = new SimpleEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("noreplyneu534@gmail.com", "Sumit@1234"));
		email.setSSLOnConnect(true);
		email.setFrom("noreplyneu534@gmail.com");
		email.setSubject("Don't reply:You have successfully registered !");
		email.setMsg("Welcome to Husky Sport Management System !");
		email.addTo(user.getEmail().getEmailAddress());
		email.send();
	}

}
