package edu.gatech.csedbs.team073.service;

/**
 * Created by swengineer on 4/8/17.
 */


import java.util.List;

import edu.gatech.csedbs.team073.dao.SiteInfoDAO;
import edu.gatech.csedbs.team073.dao.SiteInfoDAOImpl;
import edu.gatech.csedbs.team073.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("siteInfoService")
public class SiteInfoService {

    private SiteInfoDAO siteInfoDAO;

    @Autowired
    public void setSiteInfoDAO(SiteInfoDAO siteInfoDAO) {
        this.siteInfoDAO = siteInfoDAO;
    }

    public SiteInfo getSiteInfoDAO(int id) {
        return siteInfoDAO.getSiteInfo(101);
    }

    public FoodBank getFoodBankDAO(int id) {
        return siteInfoDAO.getFoodBank(101);
    }
    public FoodPantry getFoodPantryDAO(int id) {
        return siteInfoDAO.getFoodPantry(101);
    }
    public SoupKitchen getSoupKitchenDAO(int id) {
        return siteInfoDAO.getSoupKitchen(101);
    }
    public Shelter getShelterDAO(int id) {
        return siteInfoDAO.getShelter(101);
    }



}