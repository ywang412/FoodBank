package edu.gatech.csedbs.team073.web;

/**
 * Created by Phil on 4/7/2017.
 */

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.gatech.csedbs.team073.dao.UserDAO;
import edu.gatech.csedbs.team073.model.User;
import edu.gatech.csedbs.team073.dao.SiteDAO;
import edu.gatech.csedbs.team073.model.Site;


public class FoodPantryController {


    @Autowired
    private SiteDAO SiteDAO;


    @RequestMapping(value="/FoodPantryForm", method = RequestMethod.POST)

    public ModelAndView foodPantry(@RequestParam(value="username") String username, @RequestParam(value="siteId") Integer siteId) {

        Site site = SiteDAO.getSite(siteId);

        ModelAndView model = null;

        model = new ModelAndView("FoodPantryForm");
        model.addObject("site", site);


        return model;
    }

}
