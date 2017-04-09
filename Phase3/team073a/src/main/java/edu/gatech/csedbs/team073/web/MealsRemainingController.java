package edu.gatech.csedbs.team073.web;

/**
 * Created by Taylor on 4/8/2017.
 */

import javax.servlet.http.HttpServletRequest;

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

import edu.gatech.csedbs.team073.dao.ItemDAO;

@Controller
public class MealsRemainingController {


    @Autowired
    private ItemDAO ItemDAO;


    @RequestMapping(value="/MealsRemaining")
    public void MealsRemaining(Model model) {

        int count = ItemDAO.getMealCount();


//      model = new ModelAndView("MealsRemaining");
//        model.setViewName("meals");
        model.addAttribute("remaining", count);
        model.addAttribute("needs", ItemDAO.getCategoryWithCount(count));


//        return model;
    }

}
