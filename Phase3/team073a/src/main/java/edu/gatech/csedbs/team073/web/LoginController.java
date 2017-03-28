/**
 * 
 */
package edu.gatech.csedbs.team073.web;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.gatech.csedbs.team073.dao.UserDAO;
import edu.gatech.csedbs.team073.model.User;


/**
 * @author jgeorge
 *
 */
@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping(value="/loginForm", method = RequestMethod.GET)
	public ModelAndView login(ModelAndView model) {
		User user = new User();
		model.addObject("user", user);
		model.setViewName("LoginForm");
		
		return model;
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)	
	public ModelAndView loginSubmit(@ModelAttribute User user, BindingResult result) {
		
		String userName = user.getUserName();
		String password = user.getPassword();
		
		ModelAndView model = null;
		
		
		User authUser = userDAO.login(userName, password);
		if (authUser == null) {
			model = new ModelAndView("LoginForm");
			model.addObject("user", user);
			result.rejectValue(null,"error.invalidUserNamePassword", "Invalid userid and/or password");
			
		} else {
			model = new ModelAndView("SiteForm");
			model.addObject("user", authUser);
		}
		
		return model;
	}
	
	
}
