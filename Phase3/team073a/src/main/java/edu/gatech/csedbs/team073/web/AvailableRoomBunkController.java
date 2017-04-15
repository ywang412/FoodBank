package edu.gatech.csedbs.team073.web;

import edu.gatech.csedbs.team073.model.*;
import edu.gatech.csedbs.team073.service.SiteInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by swengineer on 4/14/17.
 */
@Controller
public class AvailableRoomBunkController {


    private SiteInfoService siteInfoService;

    @Autowired
    public void setSiteInfoService(SiteInfoService offersService) {
        this.siteInfoService = offersService;
    }


    @RequestMapping(value = "/AvailableRoomBunk", method = RequestMethod.GET)
    public String showAvailableRoomBunk(Model model) {

        List<SiteInfo> allSiteInfo = siteInfoService.getAllSiteInfoDAO();
        List<RoomBunkCount> allRoomBunk = new LinkedList<>();
        RoomBunkCount maleBunkCount = new RoomBunkCount();
        RoomBunkCount femaleBunkCount = new RoomBunkCount();
        RoomBunkCount mixBunkCount = new RoomBunkCount();
        RoomBunkCount roomCount = new RoomBunkCount();

        for (SiteInfo siteinfo: allSiteInfo){


            maleBunkCount= siteInfoService.getBunkCountMalebySite(siteinfo.getSiteId());
            femaleBunkCount= siteInfoService.getBunkCountFemalebySite(siteinfo.getSiteId());
            mixBunkCount= siteInfoService.getBunkCountMixedbySite(siteinfo.getSiteId());
            roomCount= siteInfoService.getRoomCountbySite(siteinfo.getSiteId());

            if (maleBunkCount!=null) {maleBunkCount.setRoomBunkTypeString("Male"); allRoomBunk.add(maleBunkCount);}
            if (femaleBunkCount!=null) {femaleBunkCount.setRoomBunkTypeString("Female");allRoomBunk.add(femaleBunkCount);}
            if (mixBunkCount!=null) {mixBunkCount.setRoomBunkTypeString("Mixed");allRoomBunk.add(mixBunkCount);}
            if (roomCount!=null) {roomCount.setRoomBunkTypeString("Room");allRoomBunk.add(roomCount);}

        }

        model.addAttribute("allRoomBunk", allRoomBunk);

        return "AvailableRoomBunkForm";
    }

}
