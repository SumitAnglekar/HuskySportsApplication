package com.neu.SportsManagement;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.neu.Dao.HuskyDao;
import com.neu.Exception.HuskyException;
import com.neu.Pojo.Husky;
import com.neu.validation.HuskyValidation;

/**
 * Handles requests for the application home page.
 */

@Controller
@RequestMapping("/home")
public class HomeController {
	

	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String viewForm(HttpServletRequest request, Model model, Husky customer, HuskyValidation customerValidation) {
		
		model.addAttribute("customer", new Husky());
		return "register";
	}
	
	@RequestMapping(value= "/register", method = RequestMethod.POST)
	public String saveForm(@ModelAttribute("customer") Husky customer, HuskyDao customerDao,BindingResult br, HttpSession session,HuskyValidation customerValidation) throws HuskyException {
		
		
		customerValidation.validate(customer,br);
		
		if(br.hasErrors()) {
			return "register";
		}
		else {
			customerDao.saveCustomer(customer);
			session.setAttribute("customer", customer);
			try {
				SendEmail(customer);
			}catch(Exception e) {
				e.printStackTrace();
			}
			return "successregister";
		}
		
	}
	
	private void SendEmail(Husky customer) throws EmailException {
		Email email = new SimpleEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("rickdale84@gmail.com", "rickdale84"));
		email.setSSLOnConnect(true);
		email.setFrom("rickdale84@gmail.com");
		email.setSubject("Successfully registered");
		email.setMsg("Welcome to Northeastern");
		email.addTo(customer.getEmailAddress());
		email.send();
		
	}

	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String viewLogin(HttpServletRequest request, Model model, Husky customer) {
		
		model.addAttribute("customer", customer);
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("customer")Husky customer,Model model, HuskyDao customerDao, HttpSession session) {
		
		if(customer.getEmailAddress()!=null && customer.getPassword()!= null && session.getAttribute("customer") ==null) {
			customer = customerDao.customerLogin(customer);
			if(customer!=null) {
				session.setAttribute("customer", customer);
				return new ModelAndView("success","customer",customer);
			}
			else {
				model.addAttribute("failed", "Login Failed");
				return new ModelAndView("login","customer",new Husky());
			}
		}
		else {
			return new ModelAndView("login","customer",new Husky());
		}
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(Model model, HttpSession session) {
		
		session.removeAttribute("customer");
		return "redirect: login";
		
	}
	
	//Used to redirect to success.jsp
	@RequestMapping(value="/success", method = RequestMethod.GET)
	public String successView(Model model) {
		
		model.addAttribute("success", new Husky());
		return "success";
	}


	
//	 @RequestMapping("/")
//	    public String home(){
//	        return "home";
//	    }
//
//	    @RequestMapping("/login")
//	    public String login(@RequestParam(value="error",required = false)String error, @RequestParam(value="logout",required = false)String logout, Model model){
//	        if (error!=null){
//	            model.addAttribute("error","Invalid username and password");
//	        }
//
//	        if(logout!=null){
//	            model.addAttribute("msg", "You have logged out successfully");
//	        }
//
//	        return "login";
//	    }
//	    
	    

	    @RequestMapping("/about")
	    public String about() {
	        return "about";
	    }
	
}
