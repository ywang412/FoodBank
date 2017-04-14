package edu.gatech.csedbs.team073.web;


import javax.servlet.http.HttpServletRequest;

import edu.gatech.csedbs.team073.model.*;
import edu.gatech.csedbs.team073.service.SiteInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import edu.gatech.csedbs.team073.dao.UserDAO;


/**
 * @author jgeorge
 *
 */
@Controller
@SessionAttributes({"user"})
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	private SiteInfoService siteInfoService;

	@Autowired
	public void setSiteInfoService(SiteInfoService offersService) {
		this.siteInfoService = offersService;
	}


	@Autowired
	private UserDAO userDAO;

	@RequestMapping(value="/loginForm", method = RequestMethod.GET)
	public ModelAndView login(ModelAndView model) {
		User user = new User();
		user.setSiteId(101);
		model.addObject("user", user);
		model.setViewName("LoginForm");

		return model;
	}

	@RequestMapping(value="/login", method = RequestMethod.POST)
	public ModelAndView loginSubmit(@ModelAttribute("user") User user, BindingResult result) {




		String userName = user.getUserName();
		String password = user.getPassword();

		ModelAndView model = null;


		User authUser = userDAO.login(userName, password);





		if (authUser == null) {
			model = new ModelAndView("LoginForm");
			model.addObject("user", user);
			result.rejectValue(null,"error.invalidUserNamePassword", "Invalid userid and/or password");

		} else {

			SiteInfo siteInfo = siteInfoService.getSiteInfoDAO(authUser.getSiteId());
			FoodBank foodBank = siteInfoService.getFoodBankDAO(authUser.getSiteId());
			FoodPantry foodPantry = siteInfoService.getFoodPantryDAO(authUser.getSiteId());
			SoupKitchen soupKitchen = siteInfoService.getSoupKitchenDAO(authUser.getSiteId());
			Shelter shelter = siteInfoService.getShelterDAO(authUser.getSiteId());


			model = new ModelAndView("UserDashboard");
			model.addObject("user", authUser);

			model.addObject("foodBank", foodBank);

			model.addObject("siteInfo", siteInfo);

			if (foodBank!=null)
				model.addObject("message2", "Yes");
			else
				model.addObject("message2", "No");
			if (foodPantry!=null)
				model.addObject("message3", "Yes");
			else
				model.addObject("message3", "No");

			if (soupKitchen!=null)
				model.addObject("message4", "Yes");
			else
				model.addObject("message4", "No");

			if (shelter!=null)
				model.addObject("message5", "Yes");
			else
				model.addObject("message5", "No");


		}

		return model;
	}


}