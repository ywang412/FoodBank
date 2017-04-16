package edu.gatech.csedbs.team073.web;

import edu.gatech.csedbs.team073.model.RoomBunkCount;
import edu.gatech.csedbs.team073.model.SiteInfo;
import edu.gatech.csedbs.team073.model.Waitlist;
import edu.gatech.csedbs.team073.service.SiteInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by swengineer on 4/14/17.
 */
@Controller
public class WaitlistController {


    private SiteInfoService siteInfoService;

    @Autowired
    public void setSiteInfoService(SiteInfoService offersService) {
        this.siteInfoService = offersService;
    }


    @RequestMapping(value = "/Waitlist", method = RequestMethod.GET)
    public String showWaitlist(Model model) {

        int site_id =1;

        List<Waitlist> allWaitlist = siteInfoService.getAllWaitlistDAO(site_id);

        String shelter_ds = allWaitlist.get(0).getDescription();

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

}
