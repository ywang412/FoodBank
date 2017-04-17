package edu.gatech.csedbs.team073.web;

import java.sql.*;
import edu.gatech.csedbs.team073.model.*;
import edu.gatech.csedbs.team073.service.SiteInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.gatech.csedbs.team073.dao.ClientDAO;
import edu.gatech.csedbs.team073.dao.ItemDAO;
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
public class ItemController {

    private final Logger logger = LoggerFactory.getLogger(ShelterController.class);
	@Autowired
	private ItemDAO itemDAO;

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


    @RequestMapping(value="/ItemList", method = RequestMethod.GET)

    public ModelAndView ItemList(@RequestParam(value="username") String username) {

        List<Item> items;
        ModelAndView model = null;
        model = new ModelAndView("ItemList");
        items = siteInfoService.GetItemTableWith(username);
        model.addObject("lists", items);
        //query all of the shelters in shelters list
        return model;
    }





    @RequestMapping(value="/ItemEditForm", method = RequestMethod.GET)
    public ModelAndView itemEdit(ModelAndView model) {
            Item item = (Item)model.getModel().get("item");



            if (null == item) {
                    item = new Item();
            }


            model.addObject("item", item);

            model.setViewName("ItemEditForm");

            List <ItemFoodCategory> food_item_catagory_enum = siteInfoService.GetAllFoodCategories();

            model.addObject("lists", food_item_catagory_enum);



            List <ItemType> item_type_enum = siteInfoService.GetAllItemTypes();

            model.addObject("item_type_lists", item_type_enum);

            List <ItemStorageType> item_storage_type_enum = siteInfoService.GetAllStorageTypes();

            model.addObject("item_storage_type_lists", item_storage_type_enum);

            List <ItemSupplyCategory> item_supply_category_enum = siteInfoService.GetAllSupplyCategories();

            model.addObject("item_supply_categories", item_supply_category_enum);


            return model;
    }

    @RequestMapping(value="/ItemEditSubmit", method = RequestMethod.POST)
    public ModelAndView clientEditSubmit(@ModelAttribute Item item, BindingResult result, final RedirectAttributes redirectAttributes) {

            ModelAndView model = null;

    try{
            itemDAO.addItem(item);

    }
    catch(Exception e){}
    model=new ModelAndView("ItemList");
                    redirectAttributes.addFlashAttribute("msg","Item added successfully");
                    model.setViewName("ItemList");
    List<Item> items;
    items = siteInfoService.GetItemTable();
    model.addObject("lists", items);


            return model;
    }


}



