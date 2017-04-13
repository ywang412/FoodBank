package edu.gatech.csedbs.team073.web;

/**
 * Created by Phil on 4/7/2017.
 */


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import edu.gatech.csedbs.team073.dao.FoodPantryDAO;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.gatech.csedbs.team073.dao.UserDAO;
import edu.gatech.csedbs.team073.dao.SiteDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class FoodPantryController {
    private final Logger logger = LoggerFactory.getLogger(FoodPantryController.class);

    private SiteInfoService siteInfoService;

  //  List<MyObjects> objects;

    @Autowired
    public void setSiteInfoService(SiteInfoService offersService) {
        this.siteInfoService = offersService;
    }


    @RequestMapping(value="/foodpantryform", method = RequestMethod.GET)
    public ModelAndView foodPantry(@RequestParam(value="username") String username, @RequestParam(value="siteId") Integer siteId) {

        SiteInfo siteInfo;
        Provide provides;
        User user;
        ModelAndView model = null;
        int foodPantryId = 0;
        FoodPantry foodPantry;
        boolean userAuthorized = false;
        boolean sitefound = false;
        boolean foodpantryfound = false;

        siteInfo = null;
        provides = null;
        foodPantry = null;
        user = null;

        if (siteId != null) {
            //get the site that corresponds to the site id
            siteInfo = siteInfoService.getSiteInfoDAO(siteId);

            if (siteInfo != null) {
                sitefound = true;
                //query the provides the 'Provides' associated with the site id
                provides = siteInfoService.getProvideDAO(siteId);

                if (provides != null) {
                    //now you have the food pantry ID - not sure what happens when this is NULL
                    foodPantryId = provides.getFood_pantry_id();

                    if (foodPantryId != 0) {
                        foodPantry = siteInfoService.getFoodPantryDAO(foodPantryId);

                        if (foodPantry != null) {
                            foodpantryfound = true;
                        }
                    }

                }
            }
        }




        if (username != null)
        {
            user = siteInfoService.getUserDAO(username);

            //check if this user belongs to this food pantry

            //if the site exists then check if the site id matches the site id the user is associated with
            if (siteInfo != null) {
                if (user.getSiteId() == siteId) {
                    userAuthorized = true;
                }
            }
        }




        if ((userAuthorized == true) && (sitefound == true)){


            model = new ModelAndView("FoodPantryForm");


            //add items to the view
            model.addObject("shortName", siteInfo.getShortName());
            model.addObject("StreetAddress", siteInfo.getStreetAddress());
            model.addObject("City", siteInfo.getCity());
            model.addObject("State", siteInfo.getState());
            model.addObject("zipcode", siteInfo.getZip());
            model.addObject("contactNumber", siteInfo.getContactNumber());

            if ((foodpantryfound == true) && (foodPantry != null) ) {
                model.addObject("descriptionString", foodPantry.getDescriptionString());
                model.addObject("conditionsForUse", foodPantry.getConditionsForUse());
                model.addObject("hours", foodPantry.getHours());

                //ungrey out check in client button, edit, and request items

                model.addObject("disabled", "false");

            }
            else {
                model.addObject("descriptionString", "N/A");
                model.addObject("conditionsForUse", "N/A");
                model.addObject("hours", "N/A");
                model.addObject("disabled", "true");
            }

        }
        else {


           if (sitefound == true) {
               model = new ModelAndView("NotAuthorized");
           }
           else {
               model = new ModelAndView("SiteNotFound");
           }

        }


        return model;

    }


    //allows editing of the food pantry informational data

    @RequestMapping(value="/foodpantryedit", method = RequestMethod.GET)
    public ModelAndView FoodPantryEdit(@RequestParam(value="username") String username, @RequestParam(value="siteId") Integer siteId, @RequestParam(value="food_pantry_id") Integer foodPantryId) {
        SiteInfo siteInfo;
        User user;
        ModelAndView model = null;
        FoodPantry foodPantry;

        foodPantry = siteInfoService.getFoodPantryDAO(foodPantryId);
        siteInfo = siteInfoService.getSiteInfoDAO(siteId);
        user = siteInfoService.getUserDAO(username);

        model = new ModelAndView("FoodPantryEdit");

        model.addObject("shortName", siteInfo.getShortName());

        model.addObject("descriptionString", foodPantry.getDescriptionString());
        model.addObject("conditionsForUse", foodPantry.getConditionsForUse());
        model.addObject("hours", foodPantry.getHours());

        //ungrey out check in client button, edit, and request items

        model.addObject("disabled", "false");


        return model;
    }


    @RequestMapping(value="/foodpantrylist", method = RequestMethod.GET)

    public ModelAndView foodPantryList() {

        //Site site = SiteDAO.getSite(siteId);
        List<FoodPantry> foodpantries;

        ModelAndView model = null;

        model = new ModelAndView("FoodPantryList");

        //do a select * for all food pantries to get the table
        FoodPantry foodPantry = siteInfoService.getFoodPantryDAO(1);



        int fpcount = siteInfoService.foodPantryCount();

        model.addObject("count", fpcount);

        foodpantries = siteInfoService.GetFoodPantryTable();

        //model.addObject("", site);
        model.addObject("lists", foodpantries);

        //query all of the food pantries in the food pantry list




        return model;
    }


    private List<String> getList() {

        List<String> list = new ArrayList<String>();
        list.add("List A");
        list.add("List B");
        list.add("List C");
        list.add("List D");
        list.add("List 1");
        list.add("List 2");
        list.add("List 3");

        return list;

    }

 /*
    @RequestMapping(value="/table")
    public ModelAndView renderTable() {
        ModelAndView mv = new ModelAndView("/table");
        mv.add("objects",objects);
        return mv;
    }
*/

    @RequestMapping(value = "/fptest", method = RequestMethod.GET)
    public String fptest(Map<String, Object> model) {

        logger.debug("test is executed!");

        model.put("title", "FP TEST");
        model.put("msg", "THIS IS A MESSAGE");

        return "fptest";
    }

}
