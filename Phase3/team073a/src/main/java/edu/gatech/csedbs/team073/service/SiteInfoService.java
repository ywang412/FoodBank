package edu.gatech.csedbs.team073.service;

/**
 * Created by swengineer on 4/8/17.
 */


import java.util.ArrayList;
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
    private UserDAO UserDAO;
    private ProvideDAO ProvideDAO;

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


    @Autowired
    public void setUserDAO(UserDAO UserDAO) {
        this.UserDAO = UserDAO;
    }

    @Autowired
    public void setProvideDAO(ProvideDAO ProvideDAO) {
        this.ProvideDAO = ProvideDAO;
    }

    public SiteInfo getSiteInfoDAO(int id) {
        return siteInfoDAO.getSiteInfo(id);
    }
    public FoodBank getFoodBankDAO(int id) {
        return foodBankDAO.getFoodBank(id);
    }
    public FoodPantry getFoodPantryDAO(int id) {
        return foodPantryDAO.getFoodPantry(id);
    }
    public FoodPantry getFoodPantryDAObysiteID(int id) {
        return foodPantryDAO.getFoodPantrybysiteID(id);
    }

    public SoupKitchen getSoupKitchenDAO(int id) {
        return soupKitchenDAO.getSoupKitchen(id);
    }
    public SoupKitchen getSoupKitchenDAObysiteID(int id) {
        return soupKitchenDAO.getSoupKitchenbysiteID(id);
    }


    public Shelter getShelterDAO(int id) {
        return shelterDAO.getShelter(id);
    }
    public Shelter getShelterDAObysiteID(int id) {
        return shelterDAO.getShelterbysiteID(id);
    }


    public User getUserDAO(String username) {
        return UserDAO.getUser(username);
    }
    public Provide getProvideDAO(int id) {
        return ProvideDAO.getProvide(id);
    }



    //simple get max count of tables - would help for display
    public int foodPantryCount() {  return foodPantryDAO.getFoodPantryCount();}
    public List GetFoodPantryTable() {  return foodPantryDAO.GetFoodPantryTable();}

    //simple get max count of tables - would help for display
    public int soupKitchenCount() {  return soupKitchenDAO.getSoupKitchenCount();}
    public List GetSoupKitchenTable() {  return soupKitchenDAO.GetSoupKitchenTable();}


    public int shelterCount() {  return shelterDAO.getShelterCount();}
    public List GetShelterTable() {  return shelterDAO.GetShelterTable();}


    public boolean updateFoodPantry(int id, String description_string, String hours, String conditions_for_use) {

        return foodPantryDAO.updateFoodPantry(id, description_string, hours, conditions_for_use);
    }

    public boolean updateSoupKitchen(int id, String description_string, String hours, String conditions_for_use, int available_seats, int seats_limit ) {

        return soupKitchenDAO.updateSoupKitchen(id, description_string, hours, conditions_for_use, available_seats, seats_limit);
    }

    public boolean updateShelter(int id, String description_string, String hours, String conditions_for_use, int available_bunks, int available_rooms) {

        return shelterDAO.updateShelter(id, description_string, hours, conditions_for_use, available_bunks,available_rooms);
    }


    public boolean decrementSoupKitchenSeats(int id)  {return soupKitchenDAO.decrementSoupKitchenSeats(id);}

    public boolean incrementSoupKitchenSeats(int id)  {return soupKitchenDAO.incrementSoupKitchenSeats(id);}

}