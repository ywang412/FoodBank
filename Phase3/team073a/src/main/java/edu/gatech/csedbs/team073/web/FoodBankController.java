package edu.gatech.csedbs.team073.web;

import edu.gatech.csedbs.team073.model.*;
import edu.gatech.csedbs.team073.service.SiteInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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




    @GetMapping(value="/foodbankedit")
    public ModelAndView FoodBankEdit(@RequestParam(value="username") String username, @RequestParam(value="siteId") Integer siteId, @RequestParam(value="food_bank_id") Integer foodBankId) {
        SiteInfo siteInfo;
        User user;
        ModelAndView model = null;
        FoodBank foodBank;
        Provide provides;

        siteInfo = siteInfoService.getSiteInfoDAO(siteId);
        user = siteInfoService.getUserDAO(username);


        model = new ModelAndView("FoodBankEdit");
        model.addObject("shortName", siteInfo.getShortName());
        model.addObject("username", username);
        model.addObject("siteId", siteId);

        if (foodBankId == null) {
            foodBankId = 0;
        }

        //if the food bank is present
        if (foodBankId > 0) {
            foodBank = siteInfoService.getFoodBankDAO(foodBankId);

            model.addObject("descriptionString", foodBank.getDescriptionString());

            model.addObject("foodBankId", foodBankId);

            model.addObject("foodBank", new FoodBank());

            model.addObject("missing", "false");


            //check how many services are present
            //if this is the last one then disable the remove button

            Integer servicescount=0;

            provides = siteInfoService.getProvideDAO(siteId);


            if (provides.getFood_bank_id() > 0)  servicescount++;
            if (provides.getFood_pantry_id() > 0)  servicescount++;
            if (provides.getShelter_id() > 0)  servicescount++;
            if (provides.getSoup_kitchen_id() > 0)  servicescount++;

            if (servicescount > 1) {
                model.addObject("lastone", "false");
            }
            else {
                model.addObject("lastone", "true");
            }


        }
        else {

            model.addObject("missing", "true");
        }



            //ungrey out check in client button, edit, and request items

            model.addObject("disabled", "false");




        return model;
    }



    //post then reload with the changes
    @PostMapping(value="/foodbankedit")
    public ModelAndView FoodBankEdit(@RequestParam(value="username") String username, @RequestParam(value="siteId") Integer siteId,
                                       @ModelAttribute FoodBank foodBank) {

        User user;
        ModelAndView model = null;
        FoodBank newfoodbank = null;
        Provide provides= null;

        provides = siteInfoService.getProvideDAO(siteId);
        model = new ModelAndView("FoodBankEdit");


        //must be an 'add'
        if  (provides.getFood_bank_id()== 0) {

            //push new values to the database
            //adds a new entry in soup kitchen and then updates the provides
            int  newid = siteInfoService.addFoodBank(siteId,  foodBank.getDescriptionString());
            //query the new database entry if it took
            newfoodbank = siteInfoService.getFoodBankDAO(newid);
        }
        else {
            //must be an update
            //push new values to the database
            siteInfoService.updateFoodBank(foodBank.getFoodBankId(),  foodBank.getDescriptionString() );

            newfoodbank = siteInfoService.getFoodBankDAO(foodBank.getFoodBankId());
        }





        model.addObject("foodBank", newfoodbank);


        model.addObject("descriptionString", newfoodbank.getDescriptionString());


        //ungrey out check in client button, edit, and request items

        model.addObject("disabled", "false");

        model.addObject("foodBankId", newfoodbank.getFoodBankId());


        //check how many services are present
        //if this is the last one then disable the remove button
        Integer servicescount=0;

        provides = siteInfoService.getProvideDAO(siteId);


        if (provides.getFood_bank_id() > 0)  servicescount++;
        if (provides.getFood_pantry_id() > 0)  servicescount++;
        if (provides.getShelter_id() > 0)  servicescount++;
        if (provides.getSoup_kitchen_id() > 0)  servicescount++;

        if (servicescount > 1) {
            model.addObject("lastone", "false");
        }
        else {
            model.addObject("lastone", "true");
        }



        //find the site that goes with this


        model.addObject("username", username);

        model.addObject("siteId", siteId);

        return model;
    }



    //post then redirect to site
    @PostMapping(value="/foodbankremove")
    public String FoodBankRemove(@RequestParam(value="username") String username, @RequestParam(value="siteId") Integer siteId,
                                       @ModelAttribute FoodBank foodBank) {


        siteInfoService.removeFoodBank(siteId,foodBank.getFoodBankId() );
        return "redirect:/SiteInfo";
    }
}
