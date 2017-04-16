package edu.gatech.csedbs.team073.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
//		user.setSiteId(101);
		model.addObject("user", user);
		model.setViewName("LoginForm");

		return model;
	}

	@RequestMapping(value="/invalidate", method=RequestMethod.POST)
	public String invalidate(HttpSession session, Model model) {
		session.invalidate();
		if(model.containsAttribute("user")) model.asMap().remove("user");
		return "redirect:/loginForm";
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


			FoodBank foodBank = null;
			FoodPantry foodPantry= null;
			SoupKitchen soupKitchen= null;
			Shelter shelter= null;
			Provide provides= null;

			SiteInfo siteInfo = siteInfoService.getSiteInfoDAO(authUser.getSiteId());




			//go to the provides - then get the id

			provides = siteInfoService.getProvideDAO(authUser.getSiteId());

			//get by food bank id from provides for this site
			//
			if (provides.getFood_bank_id() > 0 ) {
				foodBank = siteInfoService.getFoodBankDAO(provides.getFood_bank_id());
			}

			if (provides.getFood_pantry_id() > 0 ) {
				foodPantry = siteInfoService.getFoodPantryDAO(provides.getFood_pantry_id());
			}

			if (provides.getSoup_kitchen_id() > 0 ) {
				soupKitchen = siteInfoService.getSoupKitchenDAO(provides.getSoup_kitchen_id());
			}

			if (provides.getShelter_id() > 0 ) {
				shelter = siteInfoService.getShelterDAO(provides.getShelter_id());
			}

			/*
			FoodBank foodBank = siteInfoService.getFoodBankDAO(authUser.getSiteId());
			FoodPantry foodPantry = siteInfoService.getFoodPantryDAObysiteID(authUser.getSiteId());
			SoupKitchen soupKitchen = siteInfoService.getSoupKitchenDAObysiteID(authUser.getSiteId());
			Shelter shelter = siteInfoService.getShelterDAObysiteID(authUser.getSiteId());
			*/

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