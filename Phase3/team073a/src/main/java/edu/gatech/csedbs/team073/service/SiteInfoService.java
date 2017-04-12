package edu.gatech.csedbs.team073.service;

/**
 * Created by swengineer on 4/8/17.
 */


import java.util.List;

import edu.gatech.csedbs.team073.dao.*;
import edu.gatech.csedbs.team073.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("siteInfoService")
public class SiteInfoService {

    private SiteInfoDAO siteInfoDAO;
    private FoodBankDAO foodBankDAO;
    private FoodPantryDAO foodPantryDAO;
    private SoupKitchenDAO soupKitchenDAO;
    private ShelterDAO shelterDAO;

    @Autowired
    public void setSiteInfoDAO(SiteInfoDAO siteInfoDAO) {
        this.siteInfoDAO = siteInfoDAO;
    }

    @Autowired
    public void setFoodBankDAO(FoodBankDAO foodBankDAO) {
        this.foodBankDAO = foodBankDAO;
    }

    @Autowired
    public void setFoodPantryDAO(FoodPantryDAO foodPantryDAO) {
        this.foodPantryDAO = foodPantryDAO;
    }

    @Autowired
    public void setSoupKitchenDAO(SoupKitchenDAO soupKitchenDAO) {
        this.soupKitchenDAO = soupKitchenDAO;
    }

    @Autowired
    public void setShelterDAO(ShelterDAO shelterDAO) {
        this.shelterDAO = shelterDAO;
    }


    public SiteInfo getSiteInfoDAO(int id) {
        return siteInfoDAO.getSiteInfo(101);
    }
    public FoodBank getFoodBankDAO(int id) {
        return foodBankDAO.getFoodBank(101);
    }
    public FoodPantry getFoodPantryDAO(int id) {
        return foodPantryDAO.getFoodPantry(101);
    }
    public SoupKitchen getSoupKitchenDAO(int id) {
        return soupKitchenDAO.getSoupKitchen(101);
    }
    public Shelter getShelterDAO(int id) {
        return shelterDAO.getShelter(101);
    }



}