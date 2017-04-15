package edu.gatech.csedbs.team073.web;


import edu.gatech.csedbs.team073.model.*;
import edu.gatech.csedbs.team073.model.Provide;
import edu.gatech.csedbs.team073.model.SiteInfo;
import edu.gatech.csedbs.team073.model.SoupKitchen;
import edu.gatech.csedbs.team073.model.User;
import edu.gatech.csedbs.team073.service.SiteInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/**
 * Created by Phil on 4/13/2017.
 */
@Controller
public class SoupKitchenController {
    private final Logger logger = LoggerFactory.getLogger(SoupKitchenController.class);

    private SiteInfoService siteInfoService;

    @Autowired
    public void setSiteInfoService(SiteInfoService offersService) {
        this.siteInfoService = offersService;
    }


    @RequestMapping(value="/soupkitchenform", method = RequestMethod.GET)
    public ModelAndView soupKitchen(@RequestParam(value="username") String username, @RequestParam(value="siteId") Integer siteId) {

        SiteInfo siteInfo;
        Provide provides;
        User user;
        ModelAndView model = null;
        int skitchenId = 0;
        SoupKitchen skitchen;
        boolean userAuthorized = false;
        boolean sitefound = false;
        boolean skitchenfound = false;

        siteInfo = null;
        provides = null;
        skitchen = null;
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
                    skitchenId = provides.getSoup_kitchen_id();

                    if (skitchenId != 0) {
                        skitchen = siteInfoService.getSoupKitchenDAO(skitchenId);

                        if (skitchen != null) {
                            skitchenfound = true;
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


            model = new ModelAndView("SoupKitchenForm");


            //add items to the view
            model.addObject("shortName", siteInfo.getShortName());
            model.addObject("StreetAddress", siteInfo.getStreetAddress());
            model.addObject("City", siteInfo.getCity());
            model.addObject("State", siteInfo.getState());
            model.addObject("zipcode", siteInfo.getZip());
            model.addObject("contactNumber", siteInfo.getContactNumber());

            if ((skitchenfound == true) && (skitchen != null) ) {
                model.addObject("descriptionString", skitchen.getDescriptionString());
                model.addObject("conditionsForUse", skitchen.getConditionsForUse());
                model.addObject("hours", skitchen.getHours());
                model.addObject("available_seats", skitchen.getAvailableSeats());

                //ungrey out check in client button, edit, and request items

                model.addObject("disabled", "false");

                model.addObject("username", user.getUserName());
                model.addObject("siteId", siteId);
                model.addObject("soup_kitchen_id", skitchenId);
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

    @RequestMapping(value="/soupkitchenedit", method = RequestMethod.GET)
    public ModelAndView SoupKitchenEdit(@RequestParam(value="username") String username, @RequestParam(value="siteId") Integer siteId, @RequestParam(value="soup_kitchen_id") Integer SoupKitchenId) {
        SiteInfo siteInfo;
        User user;
        ModelAndView model = null;
        SoupKitchen skitchen;

        skitchen = siteInfoService.getSoupKitchenDAO(SoupKitchenId);
        siteInfo = siteInfoService.getSiteInfoDAO(siteId);
        user = siteInfoService.getUserDAO(username);

        model = new ModelAndView("SoupKitchenEdit");

        model.addObject("shortName", siteInfo.getShortName());

        model.addObject("descriptionString", skitchen.getDescriptionString());
        model.addObject("conditionsForUse", skitchen.getConditionsForUse());
        model.addObject("hours", skitchen.getHours());
        model.addObject("available_seats", skitchen.getAvailableSeats());


        //ungrey out check in client button, edit, and request items

        model.addObject("disabled", "false");

        model.addObject("username", username);

        model.addObject("siteId", siteId);

        model.addObject("soupKitchenId", skitchen.getSoupKitchenId());

        model.addObject("soupKitchen", new SoupKitchen());

        return model;
    }

    //post then reload with the changes
    @PostMapping(value="/soupkitchenedit")
    public ModelAndView SoupKitchenEdit(@RequestParam(value="username") String username, @RequestParam(value="siteId") Integer siteId,
                                        @RequestParam(value="available_seats") Integer seats,   @ModelAttribute SoupKitchen soupKitchen) {

        User user;
        ModelAndView model = null;
        SoupKitchen newsoupkitchen = null;

        model = new ModelAndView("SoupKitchenEdit");

        //push new values to the database
        siteInfoService.updateSoupKitchen(soupKitchen.getSoupKitchenId(),  soupKitchen.getDescriptionString(),soupKitchen.getHours(),soupKitchen.getConditionsForUse(),seats);

        //query the new database entry if it took
        newsoupkitchen = siteInfoService.getSoupKitchenDAO(soupKitchen.getSoupKitchenId());


        model.addObject("descriptionString", newsoupkitchen.getDescriptionString());
        model.addObject("conditionsForUse", newsoupkitchen.getConditionsForUse());
        model.addObject("hours", newsoupkitchen.getHours());
        model.addObject("available_seats", newsoupkitchen.getAvailableSeats());

        //ungrey out check in client button, edit, and request items

        model.addObject("disabled", "false");
        model.addObject("soupKitchenId", newsoupkitchen.getSoupKitchenId());


        //find the site that goes with this


        model.addObject("username", username);

        model.addObject("siteId", siteId);

        return model;
    }







    @RequestMapping(value="/soupkitchenlist", method = RequestMethod.GET)

    public ModelAndView SoupKitchenList() {

        //Site site = SiteDAO.getSite(siteId);
        List<SoupKitchen> soupkitchens;

        ModelAndView model = null;

        model = new ModelAndView("SoupKitchenList");





        int skcount = siteInfoService.soupKitchenCount();

        model.addObject("count", skcount);

        soupkitchens = siteInfoService.GetSoupKitchenTable();

        //model.addObject("", site);
        model.addObject("lists", soupkitchens);

        //query all of the food pantries in the food pantry list




        return model;
    }



}
