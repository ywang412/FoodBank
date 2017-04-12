package edu.gatech.csedbs.team073.web;

/**
 * Created by Phil on 4/7/2017.
 */


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import edu.gatech.csedbs.team073.model.User;
import edu.gatech.csedbs.team073.dao.SiteDAO;
import edu.gatech.csedbs.team073.model.Site;
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


    @RequestMapping("/FoodPantryForm")
    public ModelAndView foodPantry(@RequestParam(value="username") String username, @RequestParam(value="siteId") Integer siteId) {



        ModelAndView model = null;

        model = new ModelAndView("FoodPantryForm");



        return model;
    }


    @RequestMapping(value="/foodpantrylist", method = RequestMethod.GET)

    public ModelAndView foodPantryList() {

        //Site site = SiteDAO.getSite(siteId);
        List<String> list = getList();

        ModelAndView model = null;

        model = new ModelAndView("FoodPantryList");
        //model.addObject("", site);
        model.addObject("lists", list);


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
