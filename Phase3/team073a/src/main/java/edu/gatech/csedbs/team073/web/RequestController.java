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
 * Created by Taylor on 4/13/2017.
 */

@Controller
@SessionAttributes({"serviceObj","user"})
public class RequestController {

    private final Logger logger = LoggerFactory.getLogger(ShelterController.class);

    private SiteInfoService siteInfoService;

    @Autowired
    public void setSiteInfoService(SiteInfoService offersService) {
        this.siteInfoService = offersService;
    }


/*
    @RequestMapping(value="/shelterform", method = RequestMethod.GET)
    public ModelAndView ShelterView(@RequestParam(value="username") String username, @RequestParam(value="siteId") Integer siteId) {

        SiteInfo siteInfo;
        Provide provides;
        User user;
        ModelAndView model = null;
        int shelterId = 0;
        Shelter shelter;
        boolean userAuthorized = false;
        boolean sitefound = false;
        boolean shelterfound = false;

        ServiceInfo serviceInfo = new ServiceInfo(siteId);

        siteInfo = null;
        provides = null;
        shelter = null;
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
                    shelterId = provides.getShelter_id();

                    if (shelterId != 0) {
                        shelter = siteInfoService.getShelterDAO(shelterId);

                        if (shelter != null) {
                            shelterfound = true;

                            serviceInfo.setShelter(true);
                            serviceInfo.setServiceId(shelterId);
                            serviceInfo.setDescription(shelter.getDescriptionString());
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


            model = new ModelAndView("ShelterForm");


            //add items to the view
            model.addObject("shortName", siteInfo.getShortName());
            model.addObject("StreetAddress", siteInfo.getStreetAddress());
            model.addObject("City", siteInfo.getCity());
            model.addObject("State", siteInfo.getState());
            model.addObject("zipcode", siteInfo.getZip());
            model.addObject("contactNumber", siteInfo.getContactNumber());

            if ((shelterfound == true) && (shelter != null) ) {
                model.addObject("descriptionString", shelter.getDescriptionString());
                model.addObject("conditionsForUse", shelter.getConditionsForUse());
                model.addObject("hours", shelter.getHours());


                model.addObject("available_bunks", shelter.getAvailableBunks());
                model.addObject("available_rooms", shelter.getAvailableRooms());

                //ungrey out check in client button, edit, and request items

                model.addObject("disabled", "false");

                model.addObject("username", user.getUserName());
                model.addObject("siteId", siteId);
                model.addObject("shelterId", shelterId);
            }
            else {
                model.addObject("descriptionString", "N/A");
                model.addObject("conditionsForUse", "N/A");
                model.addObject("hours", "N/A");
                model.addObject("disabled", "true");



            }

            serviceInfo.setShelter(true);
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

    @RequestMapping(value="/shelteredit", method = RequestMethod.GET)
    public ModelAndView ShelterEdit(@RequestParam(value="username") String username, @RequestParam(value="siteId") Integer siteId, @RequestParam(value="shelter_id") Integer ShelterId) {
        SiteInfo siteInfo;
        User user;
        ModelAndView model = null;
        Shelter shelter;

        shelter = siteInfoService.getShelterDAO(ShelterId);
        siteInfo = siteInfoService.getSiteInfoDAO(siteId);
        user = siteInfoService.getUserDAO(username);

        model = new ModelAndView("ShelterEdit");

        model.addObject("shortName", siteInfo.getShortName());

        model.addObject("descriptionString", shelter.getDescriptionString());
        model.addObject("conditionsForUse", shelter.getConditionsForUse());
        model.addObject("hours", shelter.getHours());

        model.addObject("available_rooms", shelter.getAvailableRooms());
        model.addObject("available_bunks", shelter.getAvailableBunks());

        //ungrey out check in client button, edit, and request items

        model.addObject("disabled", "false");

        model.addObject("username", username);

        model.addObject("siteId", siteId);

        model.addObject("shelterId", shelter.getShelterId());

        model.addObject("shelter", new Shelter());



        return model;
    }


    //post then reload with the changes
    @PostMapping(value="/shelteredit")
    public ModelAndView ShelterEdit(@RequestParam(value="username") String username, @RequestParam(value="siteId") Integer siteId,
                                        @RequestParam(value="available_bunks") Integer bunks,
                                    @RequestParam(value="available_rooms") Integer rooms,   @ModelAttribute Shelter shelter) {

        User user;
        ModelAndView model = null;
        Shelter newshelter = null;

        model = new ModelAndView("ShelterEdit");

        //push new values to the database
        siteInfoService.updateShelter(shelter.getShelterId(),  shelter.getDescriptionString(),shelter.getHours(),shelter.getConditionsForUse(),bunks,rooms);

        //query the new database entry if it took
        newshelter = siteInfoService.getShelterDAO(shelter.getShelterId());


        model.addObject("descriptionString", newshelter.getDescriptionString());
        model.addObject("conditionsForUse", newshelter.getConditionsForUse());
        model.addObject("hours", newshelter.getHours());
        model.addObject("available_bunks", newshelter.getAvailableBunks());
        model.addObject("available_rooms", newshelter.getAvailableRooms());

        //ungrey out check in client button, edit, and request items

        model.addObject("disabled", "false");
        model.addObject("shelterId", newshelter.getShelterId());


        //find the site that goes with this


        model.addObject("username", username);

        model.addObject("siteId", siteId);

        return model;
    }

*/


    @RequestMapping(value="/BankRequestList", method = RequestMethod.GET)
    public ModelAndView RequestList(@RequestParam(value="username") String username,@RequestParam(value="food_bank_id") String foodBankID) {
        List<Request> requests;
        ModelAndView model = null;
        model = new ModelAndView("BankRequestList");
        requests = siteInfoService.GetRequestTable(Integer.parseInt(foodBankID));
        model.addObject("lists", requests);
        model.addObject("username", username);
        model.addObject("foodBank", foodBankID);
        //query all of the shelters in shelters list
        return model;
    }


    @RequestMapping(value="/ApproveRequest", method = RequestMethod.GET)
    public ModelAndView ShelterEdit(@RequestParam(value="username") String username, @RequestParam(value="itemName") String itemName,@RequestParam(value="unitsRequested") String unitsRequested, @RequestParam(value="requestDate") String requestDate, @RequestParam(value="count") String count, @RequestParam(value="foodBank") String foodBankID) {

        String username_=username.split(",")[0];
	String[] itemName_=itemName.split(",");
	String[] unitsRequested_=unitsRequested.split(",");
	String[] requestDate_=requestDate.split(",");
        String[] counts=count.split(",");
        for(int i=0; i<counts.length; i++){
            if(Integer.parseInt(counts[i])!=0)
            siteInfoService.approveRequest(username_, itemName_[i], unitsRequested_[i], requestDate_[i], Integer.parseInt(counts[i]));
        }

        ModelAndView model = null;
        //siteInfoService.addRequest("emp1", "test", "site1", 1);
        model = new ModelAndView("BankRequestList");
        List<Request> requests;
        requests = siteInfoService.GetRequestTable(Integer.parseInt(foodBankID));
        model.addObject("lists", requests);
        model.addObject("username", username);
        //query all of the shelters in shelters list
        return model;

    }
    @RequestMapping(value="/requestList", method = RequestMethod.GET)
    public ModelAndView RequestList(@RequestParam(value="username") String username) {
        List<Request> requests;
        ModelAndView model = null;
        model = new ModelAndView("RequestList");
        requests = siteInfoService.GetRequestTable(username);
        model.addObject("lists", requests);
        model.addObject("username", username);
        //query all of the shelters in shelters list
        return model;
    }


    @RequestMapping(value="/AddRequest", method = RequestMethod.GET)
    public ModelAndView ShelterEdit(@RequestParam(value="username") String username, @RequestParam(value="itemName") String itemName,@RequestParam(value="foodBank") String foodBank, @RequestParam(value="count") String count) {

        String username_=username.split(",")[0];
	String[] itemName_=itemName.split(",");
        String[] foodBank_=foodBank.split(",");
        String[] counts=count.split(",");
        for(int i=0; i<counts.length; i++){
            if(Integer.parseInt(counts[i])!=0)
            siteInfoService.addRequest(username_, itemName_[i], foodBank_[i], Integer.parseInt(counts[i]));
        }

        ModelAndView model = null;
        //siteInfoService.addRequest("emp1", "test", "site1", 1);

        model = new ModelAndView("RequestList");
        List<Request> requests;
        requests = siteInfoService.GetRequestTable(username_);
        model.addObject("lists", requests);
        model.addObject("username", username);
        //query all of the shelters in shelters list

        return model;
    }

    @RequestMapping(value="/AddRequest", method = RequestMethod.POST)
    public ModelAndView ItemAddRequestPost(@ModelAttribute("user") User user, @RequestParam(value="itemName") String itemName,@RequestParam(value="foodBank") String foodBank, @RequestParam(value="count") String count) {

       // String[] username_=username.split(",");
        String[] itemName_=itemName.split(",");
        String[] foodBank_=foodBank.split(",");
        String[] counts=count.split(",");
        for(int i=0; i<counts.length; i++){
            if(Integer.parseInt(counts[i])!=0)
                siteInfoService.addRequest(user.getUserName(), itemName_[i], foodBank_[i], Integer.parseInt(counts[i]));
        }

        ModelAndView model = null;
        //siteInfoService.addRequest("emp1", "test", "site1", 1);

        model = new ModelAndView("RequestList");
        List<Request> requests;
        requests = siteInfoService.GetRequestTable(user.getUserName());
        model.addObject("lists", requests);
        model.addObject("username", user.getUserName());
        //query all of the shelters in shelters list

        return model;
    }


    @RequestMapping(value="/Request", method = RequestMethod.POST)
    public ModelAndView RequestPost(@RequestParam(value="username") String username) {

        List<Item> items;
        ModelAndView model = null;
        model = new ModelAndView("ItemList");
        items = siteInfoService.GetItemTableWith(username);
        model.addObject("lists", items);
        //query all of the shelters in shelters list
        return model;
    }

}




