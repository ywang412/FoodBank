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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import edu.gatech.csedbs.team073.dao.UserDAO;
import edu.gatech.csedbs.team073.dao.SiteDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@SessionAttributes({"serviceObj"})

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

        ServiceInfo serviceInfo = new ServiceInfo(siteId);

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

                            serviceInfo.setFood_pantry(true);
                            serviceInfo.setServiceId(foodPantryId);
                            serviceInfo.setDescription(foodPantry.getDescriptionString());



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

                model.addObject("username", user.getUserName());
                model.addObject("siteId", siteId);
                model.addObject("foodPantryId", foodPantryId);

            }
            else {
                model.addObject("descriptionString", "N/A");
                model.addObject("conditionsForUse", "N/A");
                model.addObject("hours", "N/A");
                model.addObject("disabled", "true");



            }

            serviceInfo.setFood_pantry(true);
            model.addObject("serviceObj", serviceInfo);

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

    @GetMapping(value="/foodpantryedit")
    public ModelAndView FoodPantryEdit(@RequestParam(value="username") String username, @RequestParam(value="siteId") Integer siteId, @RequestParam(value="food_pantry_id") Integer foodPantryId) {
        SiteInfo siteInfo;
        User user;
        ModelAndView model = null;
        FoodPantry foodPantry;


        siteInfo = siteInfoService.getSiteInfoDAO(siteId);
        user = siteInfoService.getUserDAO(username);

        model = new ModelAndView("FoodPantryEdit");

        model.addObject("shortName", siteInfo.getShortName());

        if (foodPantryId == null) {
            foodPantryId = 0;
        }

        //if the food bank is present
        if (foodPantryId > 0) {

            foodPantry = siteInfoService.getFoodPantryDAO(foodPantryId);


            model.addObject("descriptionString", foodPantry.getDescriptionString());
            model.addObject("conditionsForUse", foodPantry.getConditionsForUse());
            model.addObject("hours", foodPantry.getHours());


            model.addObject("foodPantryId", foodPantry.getFoodPantryId());

            model.addObject("foodPantry", new FoodPantry());

            model.addObject("missing", "false");
        }
        else {

            model.addObject("missing", "true");
        }


        //ungrey out check in client button, edit, and request items

        model.addObject("disabled", "false");

        model.addObject("username", username);

        model.addObject("siteId", siteId);



        return model;
    }

    //post then reload with the changes
    @PostMapping(value="/foodpantryedit")
    public ModelAndView FoodPantryEdit(@RequestParam(value="username") String username, @RequestParam(value="siteId") Integer siteId,
                                       @ModelAttribute FoodPantry foodPantry) {

        User user;
        ModelAndView model = null;
        FoodPantry newfoodpantry = null;
        Provide provides= null;

        provides = siteInfoService.getProvideDAO(siteId);

        model = new ModelAndView("FoodPantryEdit");


        //must be an 'add'
        if  (provides.getFood_pantry_id() == 0) {


            //push new values to the database
            //adds a new entry in soup kitchen and then updates the provides
            int  newid =siteInfoService.addFoodPantry(siteId,  foodPantry.getDescriptionString(),foodPantry.getHours(),foodPantry.getConditionsForUse());


            //query the new database entry if it took
            newfoodpantry = siteInfoService.getFoodPantryDAO(newid);
        }
        else {
            //must be an update
            //push new values to the database
            siteInfoService.updateFoodPantry(foodPantry.getFoodPantryId(),  foodPantry.getDescriptionString(),foodPantry.getHours(),foodPantry.getConditionsForUse() );
            //query the new database entry if it took
            newfoodpantry = siteInfoService.getFoodPantryDAO(foodPantry.getFoodPantryId());
        }



        model.addObject("foodPantry", newfoodpantry);


        model.addObject("descriptionString", newfoodpantry.getDescriptionString());
        model.addObject("conditionsForUse", newfoodpantry.getConditionsForUse());
        model.addObject("hours", newfoodpantry.getHours());

        //ungrey out check in client button, edit, and request items

        model.addObject("disabled", "false");

        model.addObject("foodPantryId", newfoodpantry.getFoodPantryId());

        //find the site that goes with this


         model.addObject("username", username);

         model.addObject("siteId", siteId);

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


    //post then redirect to site
    @PostMapping(value="/foodpantryremove")
    public String FoodPantryRemove(@RequestParam(value="username") String username, @RequestParam(value="siteId") Integer siteId,
                                   @ModelAttribute FoodPantry foodPantry) {

         siteInfoService.removeFoodPantry(siteId,foodPantry.getFoodPantryId() );

        return "redirect:/SiteInfo";
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
