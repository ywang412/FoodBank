package edu.gatech.csedbs.team073.web;

import edu.gatech.csedbs.team073.model.*;
import edu.gatech.csedbs.team073.service.SiteInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by swengineer on 4/14/17.
 */
@Controller
@SessionAttributes({"serviceObj", "user"})
public class WaitlistController {


    private SiteInfoService siteInfoService;

    @Autowired
    public void setSiteInfoService(SiteInfoService offersService) {
        this.siteInfoService = offersService;
    }


    @RequestMapping(value = "/Waitlist", method = RequestMethod.POST)
    public String showWaitlist(@ModelAttribute("serviceObj") ServiceInfo serviceInfo, @ModelAttribute("user") User user, Model model) {


        List<Waitlist> allWaitlist = siteInfoService.getAllWaitlistDAO(serviceInfo.getServiceId());

        int siteId =  serviceInfo.getSiteId();
        String userName =user.getUserName();
        String shelter_ds = allWaitlist.get(0).getDescription();

        model.addAttribute("userName", userName);
        model.addAttribute("siteId", siteId);
        model.addAttribute("shelter_ds", shelter_ds);
        model.addAttribute("allWaitlist", allWaitlist);


        return "WaitlistForm";

    }




    @RequestMapping(value = "/ClientWaitlist", method = RequestMethod.GET)
    public String showClientWaitlist(Model model) {

        int client_id =3;


        List<Waitlist> clientWaitlist = siteInfoService.getClientWaitlistDAO(client_id);

        String client_name = clientWaitlist.get(0).getFullName();

        model.addAttribute("client_name", client_name);

        model.addAttribute("clientWaitlist", clientWaitlist);

        return "ClientWaitlistForm";
    }


    @RequestMapping(value = "/shelterwaitlistremoveclient", method = RequestMethod.POST)
    public String UpdateWaitlist(@RequestParam(value="position") int position, @ModelAttribute("serviceObj") ServiceInfo serviceInfo, @ModelAttribute("user") User user, Model model) {


        List<Waitlist> allWaitlist = siteInfoService.getAllWaitlistDAO(serviceInfo.getServiceId());

        int siteId =  serviceInfo.getSiteId();
        String userName =user.getUserName();
        String shelter_ds = allWaitlist.get(0).getDescription();




        model.addAttribute("userName", userName);
        model.addAttribute("siteId", siteId);
        model.addAttribute("shelter_ds", shelter_ds);
        model.addAttribute("allWaitlist", allWaitlist);


        return "WaitlistForm";

    }


    @RequestMapping(value = "/shelterwaitlistmoveup", method = RequestMethod.POST)
    public String WaitlistMoveClientUp(@RequestParam(value="position") int position,@ModelAttribute("serviceObj") ServiceInfo serviceInfo, @ModelAttribute("user") User user, Model model) {


        List<Waitlist> allWaitlist = siteInfoService.getAllWaitlistDAO(serviceInfo.getServiceId());

        int siteId =  serviceInfo.getSiteId();
        String userName =user.getUserName();
        String shelter_ds = allWaitlist.get(0).getDescription();

        model.addAttribute("userName", userName);
        model.addAttribute("siteId", siteId);
        model.addAttribute("shelter_ds", shelter_ds);
        model.addAttribute("allWaitlist", allWaitlist);


        return "WaitlistForm";

    }

    @RequestMapping(value = "/shelterwaitlistmovedown", method = RequestMethod.POST)
    public String WaitlistMoveClientDown(@RequestParam(value="position") int position,@ModelAttribute("serviceObj") ServiceInfo serviceInfo, @ModelAttribute("user") User user, Model model) {


        List<Waitlist> allWaitlist = siteInfoService.getAllWaitlistDAO(serviceInfo.getServiceId());

        int siteId =  serviceInfo.getSiteId();
        String userName =user.getUserName();
        String shelter_ds = allWaitlist.get(0).getDescription();

        model.addAttribute("userName", userName);
        model.addAttribute("siteId", siteId);
        model.addAttribute("shelter_ds", shelter_ds);
        model.addAttribute("allWaitlist", allWaitlist);


        return "WaitlistForm";

    }
}
