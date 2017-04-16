package edu.gatech.csedbs.team073.dao;

import edu.gatech.csedbs.team073.model.FoodBank;
import edu.gatech.csedbs.team073.model.FoodPantry;
import edu.gatech.csedbs.team073.model.SoupKitchen;

import java.util.List;

/**
 * Created by swengineer on 4/9/17.
 */

public interface FoodBankDAO {

    public FoodBank getFoodBank(int food_bank_id);

    public FoodBank getFoodBankBySiteId(int id);

    public List GetItemsInFoodBank(int id);

    public boolean updateFoodBank(int id, String description_string);

    public int addFoodBank( int siteid, String description_string);

    public boolean removeFoodBank(int siteid, int fbid);
}
