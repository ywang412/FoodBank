package edu.gatech.csedbs.team073.web;

import edu.gatech.csedbs.team073.model.*;
import edu.gatech.csedbs.team073.service.SiteInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Phil on 4/15/2017.
 */

@Controller
@SessionAttributes({"serviceObj"})
public class FoodBankController {

    private final Logger logger = LoggerFactory.getLogger(FoodBankController.class);

    private SiteInfoService siteInfoService;

    //  List<MyObjects> objects;

    @Autowired
    public void setSiteInfoService(SiteInfoService offersService) {
        this.siteInfoService = offersService;
    }



    @RequestMapping(value="/foodbankform", method = RequestMethod.GET)
    public ModelAndView foodBank(@RequestParam(value="username") String username, @RequestParam(value="siteId") Integer siteId) {
        SiteInfo siteInfo;
        Provide provides;
        User user;
        ModelAndView model = null;
        int foodBankId = 0;
        FoodBank foodBank;

        boolean userAuthorized = false;
        boolean sitefound = false;
        boolean foodbankfound = false;

        ServiceInfo serviceInfo = new ServiceInfo(siteId);
        siteInfo = null;
        provides = null;
        foodBank = null;
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
                    foodBankId = provides.getFood_bank_id();

                    if (foodBankId != 0) {
                        foodBank = siteInfoService.getFoodBankDAO(foodBankId);

                        if (foodBank != null) {
                            foodbankfound = true;

                            serviceInfo.setFood_bank(true);
                            serviceInfo.setServiceId(foodBankId);
                            serviceInfo.setDescription("");



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



        if ((userAuthorized == true) && (sitefound == true)) {

            model = new ModelAndView("FoodBankForm");

            //add items to the view
            model.addObject("shortName", siteInfo.getShortName());
            model.addObject("StreetAddress", siteInfo.getStreetAddress());
            model.addObject("City", siteInfo.getCity());
            model.addObject("State", siteInfo.getState());
            model.addObject("zipcode", siteInfo.getZip());
            model.addObject("contactNumber", siteInfo.getContactNumber());

            if ((foodbankfound == true) && (foodBank != null) ) {

                model.addObject("disabled", "false");

                //show the food bank items list

                List<Item> items;

                items = siteInfoService.GetItemsInFoodBank(foodBankId);
                model.addObject("lists", items);

                //add these hidden form inputs
                model.addObject("username", user.getUserName());
                model.addObject("siteId", siteId);
                model.addObject("foodBankId", foodBankId);

            }
            else {
                model.addObject("disabled", "true");

               //not a valid food bank. - shouldn be here

            }



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

}
