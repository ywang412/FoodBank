package edu.gatech.csedbs.team073.dao;

import edu.gatech.csedbs.team073.model.FoodBank;
import edu.gatech.csedbs.team073.model.FoodPantry;
import edu.gatech.csedbs.team073.model.SoupKitchen;

/**
 * Created by swengineer on 4/9/17.
 */
public interface FoodBankDAO {

    public FoodBank getFoodBank(int id);
}