package edu.gatech.csedbs.team073.web;

/**
 * Created by swengineer on 4/6/17.
 */


import javax.servlet.http.HttpServletRequest;

import edu.gatech.csedbs.team073.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import edu.gatech.csedbs.team073.dao.UserDAO;

import java.util.List;

import edu.gatech.csedbs.team073.service.SiteInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@SessionAttributes({"user"})

public class SiteInfoController {

    private SiteInfoService siteInfoService;

    @Autowired
    public void setSiteInfoService(SiteInfoService offersService) {
        this.siteInfoService = offersService;
    }

//    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    public String showTest(Model model, @RequestParam("id") String id) {
//        System.out.println("Id is: " + id);
//        return "home";
//    }

    @RequestMapping("/SiteInfo")
    public String showSiteInfo(@ModelAttribute("user") User user, Model model) {

        SiteInfo siteInfo = siteInfoService.getSiteInfoDAO(user.getSiteId());
        FoodBank foodBank = siteInfoService.getFoodBankDAO(user.getSiteId());
        FoodPantry foodPantry = siteInfoService.getFoodPantryDAObysiteID(user.getSiteId());
        SoupKitchen soupKitchen = siteInfoService.getSoupKitchenDAObysiteID(user.getSiteId());
        Shelter shelter = siteInfoService.getShelterDAObysiteID(user.getSiteId());

        model.addAttribute("siteInfo", siteInfo);
        model.addAttribute("foodBank", foodBank);
        model.addAttribute("foodPantry", foodPantry);
        model.addAttribute("soupKitchen", soupKitchen);
        model.addAttribute("shelter", shelter);


        return "SiteInfoForm";
    }



}
