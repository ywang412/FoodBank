package edu.gatech.csedbs.team073.web;

import edu.gatech.csedbs.team073.model.*;
import edu.gatech.csedbs.team073.service.SiteInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Phil on 4/13/2017.
 */

@Controller
@SessionAttributes({"serviceObj"})
public class ShelterController {

    private final Logger logger = LoggerFactory.getLogger(ShelterController.class);

    private SiteInfoService siteInfoService;

    @Autowired
    public void setSiteInfoService(SiteInfoService offersService) {
        this.siteInfoService = offersService;
    }



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

        int UnoccupiedMaleBunks = 0;
        int UnoccupiedFemaleBunks = 0;
        int UnoccupiedMixedBunks = 0;

        int OccupiedMaleBunks = 0;
        int OccupiedFemaleBunks = 0;
        int OccupiedMixedBunks = 0;

        int UnoccupiedRooms = 0;
        int OccupiedRooms = 0;

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


                            //get # of unoccupied male bunks for shelter
                            UnoccupiedMaleBunks =  siteInfoService.getBunkCountByShelterIdAndTypeAndOccupancy(shelterId,1,false);

                            //get # of unoccupied female bunks for shelter
                            UnoccupiedFemaleBunks =  siteInfoService.getBunkCountByShelterIdAndTypeAndOccupancy(shelterId,2,false);

                            //get # of unoccupied mixed bunks for shelter
                            UnoccupiedMixedBunks =  siteInfoService.getBunkCountByShelterIdAndTypeAndOccupancy(shelterId,3,false);



                            //get # of  occupied male bunks for shelter
                            OccupiedMaleBunks =  siteInfoService.getBunkCountByShelterIdAndTypeAndOccupancy(shelterId,1,true);

                            //get # of  occupied female bunks for shelter
                            OccupiedFemaleBunks =  siteInfoService.getBunkCountByShelterIdAndTypeAndOccupancy(shelterId,2,true);

                            //get # of  occupied mixed bunks for shelter
                            OccupiedMixedBunks =  siteInfoService.getBunkCountByShelterIdAndTypeAndOccupancy(shelterId,3,true);



                            //get # of  unoccupied rooms for shelter
                            UnoccupiedRooms =  siteInfoService.getRoomCountByShelterIdAndOccupancy(shelterId,false);


                            //get # of  occupied rooms for shelter
                            OccupiedRooms  =  siteInfoService.getRoomCountByShelterIdAndOccupancy(shelterId,true);







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


                //model.addObject("available_bunks", shelter.getAvailableBunks());

                //model.addObject("available_rooms", shelter.getAvailableRooms());

                //now set the bunk values in the view
                model.addObject("unoccupied_bunks", (UnoccupiedMaleBunks + UnoccupiedFemaleBunks + UnoccupiedMixedBunks));
                model.addObject("unoccupied_bunks_male", UnoccupiedMaleBunks);
                model.addObject("unoccupied_bunks_female",UnoccupiedFemaleBunks );
                model.addObject("unoccupied_bunks_mixed",  UnoccupiedMixedBunks);



                model.addObject("occupied_bunks", (OccupiedMaleBunks + OccupiedFemaleBunks + OccupiedMixedBunks));
                model.addObject("occupied_bunks_male", OccupiedMaleBunks);
                model.addObject("occupied_bunks_female",OccupiedFemaleBunks );
                model.addObject("occupied_bunks_mixed",  OccupiedMixedBunks);

                //now set the room values in the view
                model.addObject("unoccupied_rooms",UnoccupiedRooms );
                model.addObject("occupied_rooms",  OccupiedRooms);



                //ungrey out check in client button, edit, and request items

                model.addObject("disabled", "false");

                model.addObject("username", user.getUserName());
                model.addObject("siteId", siteId);
                model.addObject("shelterId", shelterId);

                //disable or enable buttons based on whether the count is 0 or not
                if (OccupiedMaleBunks == 0) {
                    model.addObject("not_release_bunk_male", "true");
                }
                if (OccupiedFemaleBunks == 0) {
                    model.addObject("not_release_bunk_female", "true");
                }
                if (OccupiedMixedBunks == 0) {
                    model.addObject("not_release_bunk_mixed", "true");
                }
                if (UnoccupiedMaleBunks == 0) {
                    model.addObject("not_available_bunk_male", "true");
                }
                if (UnoccupiedFemaleBunks == 0) {
                    model.addObject("not_available_bunk_female", "true");
                }
                if (UnoccupiedMixedBunks == 0) {
                    model.addObject("not_available_bunk_mixed", "true");
                }

                //deal with disabling room buttons
                if (OccupiedRooms  == 0) {
                    model.addObject("not_release_room", "true");
                }
                if (UnoccupiedRooms == 0) {
                    model.addObject("not_available_room", "true");
                }

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


        siteInfo = siteInfoService.getSiteInfoDAO(siteId);
        user = siteInfoService.getUserDAO(username);

        model = new ModelAndView("ShelterEdit");

        model.addObject("shortName", siteInfo.getShortName());

        model.addObject("username", username);

        model.addObject("siteId", siteId);


        if (ShelterId == null) {
            ShelterId = 0;
        }


        //if the shelter is present
        if (ShelterId > 0) {
            shelter = siteInfoService.getShelterDAO(ShelterId);

            model.addObject("descriptionString", shelter.getDescriptionString());
            model.addObject("conditionsForUse", shelter.getConditionsForUse());
            model.addObject("hours", shelter.getHours());

            model.addObject("shelterId", shelter.getShelterId());

            model.addObject("shelter", new Shelter());

            model.addObject("missing", "false");
        }
        else {

            model.addObject("missing", "true");
        }


        //ungrey out check in client button, edit, and request items

        model.addObject("disabled", "false");


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
        Provide provides = null;



        provides = siteInfoService.getProvideDAO(siteId);

        model = new ModelAndView("ShelterEdit");


        //must be an 'add'
        if  (provides.getShelter_id() == 0) {


            //push new values to the database
            //adds a new entry in soup kitchen and then updates the provides
            int  newid = siteInfoService.addShelter(siteId, shelter.getDescriptionString(),shelter.getHours(),shelter.getConditionsForUse(),bunks,rooms);


            //query the new database entry if it took
            newshelter = siteInfoService.getShelterDAO(newid);
        }
        else {
            //must be an update
            //push new values to the database
            siteInfoService.updateShelter(shelter.getShelterId(),  shelter.getDescriptionString(),shelter.getHours(),shelter.getConditionsForUse(),bunks,rooms);

            //query the new database entry if it took
            newshelter = siteInfoService.getShelterDAO(shelter.getShelterId());

        }


        model.addObject("shelter", newshelter);




        model.addObject("descriptionString", newshelter.getDescriptionString());
        model.addObject("conditionsForUse", newshelter.getConditionsForUse());
        model.addObject("hours", newshelter.getHours());
        //model.addObject("available_bunks", newshelter.getAvailableBunks());
        //model.addObject("available_rooms", newshelter.getAvailableRooms());

        //ungrey out check in client button, edit, and request items

        model.addObject("disabled", "false");
        model.addObject("shelterId", newshelter.getShelterId());


        //find the site that goes with this


        model.addObject("username", username);

        model.addObject("siteId", siteId);

        return model;
    }

    //post then redirect to site
    @PostMapping(value="/shelterremove")
    public String ShelterRemove(@RequestParam(value="username") String username, @RequestParam(value="siteId") Integer siteId,
                                   @ModelAttribute Shelter shelter) {


        siteInfoService.removeShelter(siteId,shelter.getShelterId() );

        return "redirect:/SiteInfo";
    }




    @RequestMapping(value="/shelterformbunk", method = RequestMethod.POST)
    public String shelterBunkCheckin(@RequestParam(value="username") String username,
                                     @RequestParam(value="siteId") Integer siteId,
                                     @RequestParam(value="shelter_id") Integer shelterId,
                                     @RequestParam(value="bunk_type") Integer bunkType) {

        ServiceInfo serviceInfo = new ServiceInfo(siteId);
        Shelter shelter = siteInfoService.getShelterDAO( shelterId);

        //decrement the available seats

        //return the bunk number if successfull otherwise it will be 0 which is an error
        Integer bunk_number = siteInfoService.claimNextAvailableBunk(shelterId, bunkType);


        //


        //this will go to a new view
        // POST/REDIRECT/GET

        //The client log can add which bunk # if we really want to add it in notes
        return "redirect:/ClientSearchForm";
    }



    @RequestMapping(value="/shelterbunkrelease", method = RequestMethod.POST)
    public String shelterBunkRelease(@RequestParam(value="username") String username,
                                     @RequestParam(value="siteId") Integer siteId,
                                     @RequestParam(value="shelter_id") Integer shelterId,
                                     @RequestParam(value="bunk_type") Integer bunkType) {

        ServiceInfo serviceInfo = new ServiceInfo(siteId);
        Shelter shelter = siteInfoService.getShelterDAO( shelterId);

        //decrement the available seats

        //return the bunk number if successfull otherwise it will be 0 which is an error
        Integer bunk_number = siteInfoService.releaseBunk(shelterId, bunkType);


        //


        //this will go to a new view
        // POST/REDIRECT/GET

        //The client log can add which bunk # if we really want to add it in notes
        String redirect = "redirect:/shelterform?username=" + username + "&siteId=" + siteId;
        return redirect;
    }



    @RequestMapping(value="/shelterclaimroom", method = RequestMethod.POST)
    public String shelterRoomClaim(@ModelAttribute("serviceObj") ServiceInfo serviceInfo, BindingResult result) {
        int claimed_room = 0;
        SiteInfo siteInfo = siteInfoService.getSiteInfoDAO(serviceInfo.getSiteId());

        Shelter shelter = siteInfoService.getShelterDAO( serviceInfo.getServiceId());

        //next_avail_room = siteInfoService.findNextAvailableRoom(serviceInfo.getServiceId());

        claimed_room = siteInfoService.claimNextAvailableRoom(serviceInfo.getServiceId());

        serviceInfo.setRelease_room(false);
        serviceInfo.setRoom_number(claimed_room);

        //find first available room for this site/shelter



        //this will go to a new view
        // POST/REDIRECT/GET

        //The client log can add which bunk # if we really want to add it in notes
        return "redirect:/ClientSearchForm";
    }



    @RequestMapping(value="/shelterreleaseRoom", method = RequestMethod.POST)
    public String shelterRoomRelease(@RequestParam(value="username") String username,
                                     @RequestParam(value="siteId") Integer siteId,
                                     @RequestParam(value="shelter_id") Integer shelterId) {

        ServiceInfo serviceInfo = new ServiceInfo(siteId);
        serviceInfo.setRelease_room(true);

        //this will go to a new view
        // POST/REDIRECT/GET
        Integer room_number = siteInfoService.releaseRoom(shelterId);
        serviceInfo.setRoom_number(room_number);

        //The client log can add which bunk # if we really want to add it in notes
        String redirect = "redirect:/shelterform?username=" + username + "&siteId=" + siteId;
        return redirect;
    }


    @RequestMapping(value="/shelterWaitlistRoom", method = RequestMethod.POST)
    public String shelterRoomWaitlist(@ModelAttribute("serviceObj") ServiceInfo serviceInfo, BindingResult result) {


        serviceInfo.setRelease_room(false);
        serviceInfo.setAdd_to_waitlist(true);
        //this will go to a new view
        // POST/REDIRECT/GET

        //The client log can add which bunk # if we really want to add it in notes
        return "redirect:/ClientSearchForm";
    }




    @RequestMapping(value="/shelterlist", method = RequestMethod.GET)

    public ModelAndView ShelterList() {


        List<Shelter> shelters;

        ModelAndView model = null;

        model = new ModelAndView("ShelterList");





        int sheltercount = siteInfoService.shelterCount();

        model.addObject("count", sheltercount);

        shelters = siteInfoService.GetShelterTable();


        model.addObject("lists", shelters);

        //query all of the shelters in shelters list




        return model;
    }






}



